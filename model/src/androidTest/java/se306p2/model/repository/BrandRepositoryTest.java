package se306p2.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import androidx.test.core.app.ApplicationProvider;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IBrand;
import se306p2.domain.interfaces.repositories.IBrandRepository;

class BrandRepositoryTest {

    private static FirebaseFirestore firestore;
    private static IBrandRepository brandRepository;

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
            brandRepository = BrandRepository.getInstance();

            // Setup Data
            Map<String, Object> entry;
            entry = new HashMap<String, Object>() {{
                put("name", "Fragrance");
            }};
            Tasks.await(firestore.collection("category").document("hsLyZbFCvHVAQRrP55bF").set(entry));

            entry = new HashMap<String, Object>() {{
                put("name", "Cosmetica");
                put("imageURI", "https://picsum.photos/100?cosmetica");
            }};
            Tasks.await(firestore.collection("brand").document("kSthRsmDxL2j7ZVbGZCN").set(entry));

            entry = new HashMap<String, Object>() {{
                put("name", "Abeva");
                put("imageURI", "https://picsum.photos/100?abeva");
            }};
            Tasks.await(firestore.collection("brand").document("7WYBGBcygDqCBw7VsgQ8").set(entry));

            entry = new HashMap<String, Object>() {{
                put("name", "Lance");
                put("imageURI", "https://picsum.photos/100?lance");
            }};
            Tasks.await(firestore.collection("brand").document("uhX8zbznAPY65zUjajAA").set(entry));

            // Setup Products
            entry = new HashMap<String, Object>() {{
                put("brand", firestore.collection("brand").document("uhX8zbznAPY65zUjajAA"));
                put("category", firestore.collection("category").document("hsLyZbFCvHVAQRrP55bF"));
            }};
            Tasks.await(firestore.collection("product").document("t3WZ88FWdbqkNEJHrLcw").set(entry));

            entry = new HashMap<String, Object>() {{
                put("brand", firestore.collection("brand").document("7WYBGBcygDqCBw7VsgQ8"));
                put("category", firestore.collection("category").document("hsLyZbFCvHVAQRrP55bF"));
            }};
            Tasks.await(firestore.collection("product").document("ubLpqKgBWmerw3dQtg4P").set(entry));

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @AfterAll
    static void tearDown() {
        try {
            // Delete documents
            Tasks.await(firestore.collection("category").document("hsLyZbFCvHVAQRrP55bF").delete());

            Tasks.await(firestore.collection("brand").document("kSthRsmDxL2j7ZVbGZCN").delete());
            Tasks.await(firestore.collection("brand").document("7WYBGBcygDqCBw7VsgQ8").delete());
            Tasks.await(firestore.collection("brand").document("uhX8zbznAPY65zUjajAA").delete());

            Tasks.await(firestore.collection("product").document("t3WZ88FWdbqkNEJHrLcw").delete());
            Tasks.await(firestore.collection("product").document("ubLpqKgBWmerw3dQtg4P").delete());

            FirebaseApp.clearInstancesForTest();
        } catch (ExecutionException | InterruptedException exception) {
            fail(exception);
        }
    }

    @Nested
    @DisplayName("getBrands Test")
    class getBrandsTests {

        @Test
        void testGetBrands() {
            assertNotNull(brandRepository.getBrands());
        }

        @Test
        void testGetBrandsSize() {
            List<IBrand> categories = brandRepository.getBrands();
            assertEquals(3, categories.size());
        }

        @Test
        void testGetBrandsItems() {
            List<IBrand> brands = brandRepository.getBrands();

            IBrand brand_0 = brands.get(0);
            assertEquals("7WYBGBcygDqCBw7VsgQ8", brand_0.getId());
            assertEquals("Abeva", brand_0.getName());
            assertEquals("https://picsum.photos/100?abeva", brand_0.getImageURI());

            IBrand brand_1 = brands.get(1);
            assertEquals("kSthRsmDxL2j7ZVbGZCN", brand_1.getId());
            assertEquals("Cosmetica", brand_1.getName());
            assertEquals("https://picsum.photos/100?cosmetica", brand_1.getImageURI());

            IBrand brand_2 = brands.get(2);
            assertEquals("uhX8zbznAPY65zUjajAA", brand_2.getId());
            assertEquals("Lance", brand_2.getName());
            assertEquals("https://picsum.photos/100?lance", brand_2.getImageURI());
        }
    }

    @Nested
    @DisplayName("getBrands by category Test")
    class getBrandsByCategoryTests {

        @Test
        void testGetBrandsEmpty() {
            assertEquals(new ArrayList<>(), brandRepository.getBrands("AAAAAAAAAAAAAAAAAAAA"));
        }

        @Test
        void testGetBrands() {
            assertNotNull(brandRepository.getBrands("hsLyZbFCvHVAQRrP55bF"));
        }

        @Test
        void testGetBrandsSize() {
            List<IBrand> brands = brandRepository.getBrands("hsLyZbFCvHVAQRrP55bF");
            assertEquals(2, brands.size());
        }

        @Test
        void testGetBrandsItems() {
            List<IBrand> brands = brandRepository.getBrands("hsLyZbFCvHVAQRrP55bF");

            IBrand brand_0 = brands.get(0);
            assertEquals("uhX8zbznAPY65zUjajAA", brand_0.getId());
            assertEquals("Lance", brand_0.getName());
            assertEquals("https://picsum.photos/100?lance", brand_0.getImageURI());

            IBrand brand_1 = brands.get(1);
            assertEquals("7WYBGBcygDqCBw7VsgQ8", brand_1.getId());
            assertEquals("Abeva", brand_1.getName());
            assertEquals("https://picsum.photos/100?abeva", brand_1.getImageURI());
        }
    }
}
