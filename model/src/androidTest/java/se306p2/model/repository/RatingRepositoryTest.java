package se306p2.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import androidx.test.core.app.ApplicationProvider;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IRating;

public class RatingRepositoryTest {
    private static FirebaseFirestore firestore;
    private static RatingRepository ratingRepository;
    private static String dummyUserId = "ysjrdemq74zDG3HVpMDT";

    @BeforeAll
    static void setUp() {
        try {
            // Setup Firestore
            FirebaseApp.clearInstancesForTest();
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext(), new FirebaseOptions.Builder().
                    setApiKey("fakeApiKey").
                    setApplicationId("fakeApplicationId").
                    setProjectId("se306-project-2-team-9").
                    build());
            firestore = FirebaseFirestore.getInstance();
            // Using local emulator, Read README.md for more info
            firestore.useEmulator("10.0.2.2", 8080);
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(false)
                    .build();
            firestore.setFirestoreSettings(settings);

            // Use Reflection to inject local Firebase instance
            ratingRepository = new RatingRepository();
            Field privateFirestore = RatingRepository.class.getDeclaredField("db");
            privateFirestore.setAccessible(true);
            privateFirestore.set(ratingRepository, firestore);

            // Setup Data
            Map<String, Object> entry;

            // Set up Dummy User Instance
            entry = new HashMap<String, Object>() {{
            }};
            Tasks.await(firestore.collection("user").document(dummyUserId).set(entry));

            // Set up Brand
            entry = new HashMap<String, Object>() {{
                put("name", "Byredo");
                put("imageURI", "https://picsum.photos/100?byredo");
            }};
            Tasks.await(firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN").set(entry));

            entry = new HashMap<String, Object>() {{
                put("form", IProduct.Form.LIQUID);
                put("brand", firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN"));
                put("name", "product_1");
            }};
            Tasks.await(firestore.collection("product").document("vwyuy5Ft4UAU67WBNfjv").set(entry));

            entry = new HashMap<String, Object>() {{
                put("form", IProduct.Form.LIQUID);
                put("brand", firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN"));
                put("name", "product_2");
                put("numericRating", 3.0);
                put("numReviews", 1);

            }};
            Tasks.await(firestore.collection("product").document("JTSj6g2d2hLWDqKPXYTC").set(entry));


        } catch (NoSuchFieldException | IllegalAccessException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @AfterAll
    static void tearDown() {
        try {
            // Delete documents

            Tasks.await(firestore.collection("user").document("ysjrdemq74zDG3HVpMDT").delete());

            Tasks.await(firestore.collection("product").document("vwyuy5Ft4UAU67WBNfjv").delete());
            Tasks.await(firestore.collection("product").document("JTSj6g2d2hLWDqKPXYTC").delete());

            Tasks.await(firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN").delete());

            FirebaseApp.clearInstancesForTest();
        } catch (ExecutionException | InterruptedException exception) {
            fail(exception);
        }
    }

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @Nested
    @DisplayName("Ratings Test")
    class ratingsTests {
        @Test
        @Order(1)
        void testGetRatingNoRatingYet() {

            IRating rating = ratingRepository.getRating("vwyuy5Ft4UAU67WBNfjv");

            assertEquals(0.0, rating.getRating());
            assertEquals(0, rating.getNum());

        }

        @Test
        @Order(2)
        void testRemoveRatingUserNotRatedAnythingYet() {

            try {
                ratingRepository.removeRating("JTSj6g2d2hLWDqKPXYTC", dummyUserId);
                fail();
            } catch (UnsupportedOperationException e) {
                assertEquals(e.getMessage(), "The user has not rated any products yet");
            }

        }

        @Test
        @Order(3)
        void testGetRatingWithExistingRating() {

            IRating rating = ratingRepository.getRating("JTSj6g2d2hLWDqKPXYTC");

            assertEquals(3.0, rating.getRating());
            assertEquals(1, rating.getNum());

        }

        @Test
        @Order(4)
        void testAddRatingWithNoRateYet() {

            IRating rating = ratingRepository.addRating("vwyuy5Ft4UAU67WBNfjv", dummyUserId, 5);

            assertEquals(5.0, rating.getRating());
            assertEquals(1, rating.getNum());

        }

        @Test
        @Order(5)
        void testAddRatingWithExistingRating() {

            IRating rating = ratingRepository.addRating("JTSj6g2d2hLWDqKPXYTC", dummyUserId, 5);

            assertEquals(4.0, rating.getRating());
            assertEquals(2, rating.getNum());

        }

        @Test
        @Order(6)
        void testEditRatingWithExistingRatingToSameProduct() {

            IRating rating = ratingRepository.addRating("JTSj6g2d2hLWDqKPXYTC", dummyUserId, 5);

            assertEquals(4.0, rating.getRating());
            assertEquals(2, rating.getNum());

        }


        @Test
        @Order(7)
        void testRemoveRatingFromProductThatDoesntExist() {
            try {
                ratingRepository.removeRating("aaaaaaaaaaaaaaaaaaaa", dummyUserId);
                fail();
            } catch (UnsupportedOperationException e) {
                assertEquals("product does not exist", e.getMessage());
            }

        }

        @Test
        @Order(8)
        void testRemoveRatingFromProductUserDidRate() {
            IRating rating = ratingRepository.removeRating("JTSj6g2d2hLWDqKPXYTC", dummyUserId);

            assertEquals(3.0, rating.getRating());
            assertEquals(1, rating.getNum());

        }

        @Test
        @Order(9)
        void testRemoveRatingFromProductUserDidRateResultingInEmptyMapAndZeroRating() {
            IRating rating = ratingRepository.removeRating("vwyuy5Ft4UAU67WBNfjv", dummyUserId);

            assertEquals(0.0, rating.getRating());
            assertEquals(0, rating.getNum());

        }

        @Test
        @Order(10)
        void testAddRatingFromProductThatDoesntExist() {
            try {
                ratingRepository.addRating("aaaaaaaaaaaaaaaaaaaa", dummyUserId, 5);
                fail();
            } catch (UnsupportedOperationException e) {
                assertEquals("product does not exist", e.getMessage());
            }
        }

        @Test
        @Order(11)
        void testRemoveRatingFromProductButUserHasNotRatedIt() {
            try {
                ratingRepository.removeRating("vwyuy5Ft4UAU67WBNfjv", dummyUserId);
                fail();
            } catch (UnsupportedOperationException e) {
                assertEquals("This product has not been rated by the user yet", e.getMessage());
            }
        }

    }

}
