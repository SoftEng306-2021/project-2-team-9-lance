package se306p2.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import androidx.test.core.app.ApplicationProvider;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.repositories.IUserRepository;

class UserRepositoryTest {

    private static FirebaseFirestore firestore;
    private static FirebaseAuth auth;
    private static IUserRepository userRepository;

    @BeforeAll
    static void setUp() {
        try {
            // Setup Firestore
            FirebaseApp.clearInstancesForTest();
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext(), new FirebaseOptions.Builder().
                    setApiKey("apiKey").
                    setApplicationId("applicationId").
                    setProjectId("se306-project-2-team-9").
                    build());
            firestore = FirebaseFirestore.getInstance();
            // Using local emulator, Read README.md for more info
            firestore.useEmulator("10.0.2.2", 8080);
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(false)
                    .build();
            firestore.setFirestoreSettings(settings);

            auth = FirebaseAuth.getInstance();
            auth.useEmulator("10.0.2.2", 9099);

            userRepository = UserRepository.getInstance();

            // Setup Data
            Map<String, Object> entry;
            entry = new HashMap<String, Object>() {{
                put("name", "product_1");
            }};
            Tasks.await(firestore.collection("product").document("vwyuy5Ft4UAU67WBNfjv").set(entry));

            entry = new HashMap<String, Object>() {{
                put("name", "product_2");
            }};
            Tasks.await(firestore.collection("product").document("JTSj6g2d2hLWDqKPXYTC").set(entry));

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @AfterAll
    static void tearDown() {
        try {
            // Delete documents
            Tasks.await(firestore.collection("product").document("vwyuy5Ft4UAU67WBNfjv").delete());
            Tasks.await(firestore.collection("product").document("JTSj6g2d2hLWDqKPXYTC").delete());

            FirebaseApp.clearInstancesForTest();
        } catch (ExecutionException | InterruptedException exception) {
            fail(exception);
        }
    }

    @Nested
    @DisplayName("firebase admin Test")
    class firebaseAdminTests {
        @Test
        void testGetCurrentUserIdEmpty() {
            String userId = userRepository.getCurrentUserId();
            assertNull(userId);
        }

        @Test
        void testSignInAnonymously() {
            String userId = userRepository.signInAnonymously();
            assertNotNull(userId);

            assertEquals(userId, userRepository.getCurrentUserId());
            assertEquals(userId, userRepository.signInAnonymously());
            auth.signOut();
        }
    }

    @Nested
    @DisplayName("favourites Test")
    class favouritesTests {
        @Test
        void testFavouritesUserNotExist() {
            Set<String> favourites = userRepository.favourites();
            assertNull(favourites);
        }

        @Test
        void testFavouritesEmpty() throws ExecutionException, InterruptedException {
            String userId = userRepository.signInAnonymously();
            assertNotNull(userId);

            Map<String, Object> entry;
            entry = new HashMap<String, Object>() {{
                put("favourites", new ArrayList<DocumentReference>());
            }};
            Tasks.await(firestore.collection("user").document(userId).set(entry));

            Set<String> favourites = userRepository.favourites();
            assertEquals(new HashSet<String>(), favourites);
            auth.signOut();
        }

        @Test
        void testAddFavourite() throws ExecutionException, InterruptedException {
            String userId = userRepository.signInAnonymously();
            assertNotNull(userId);

            Map<String, Object> entry;
            entry = new HashMap<String, Object>() {{
                put("favourites", new ArrayList<DocumentReference>() {{
                    add(firestore.collection("product").document("vwyuy5Ft4UAU67WBNfjv"));
                    add(firestore.collection("product").document("JTSj6g2d2hLWDqKPXYTC"));
                }});
            }};
            Tasks.await(firestore.collection("user").document(userId).set(entry));

            Set<String> favourites = userRepository.favourite("vwyuy5Ft4UAU67WBNfjv");

            assertEquals(2, favourites.size());

            assertTrue(favourites.remove("vwyuy5Ft4UAU67WBNfjv"));
            assertTrue(favourites.remove("JTSj6g2d2hLWDqKPXYTC"));
            auth.signOut();
        }
    }
}
