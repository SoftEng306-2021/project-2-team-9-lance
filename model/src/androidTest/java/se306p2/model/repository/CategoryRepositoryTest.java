package se306p2.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import androidx.test.core.app.ApplicationProvider;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.ICategory;

class CategoryRepositoryTest {

    private static FirebaseFirestore firestore;
    private static CategoryRepository categoryRepository;

    @BeforeAll
    static void setUp()  {
        try {
            // Setup Firestore
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext());
            firestore = FirebaseFirestore.getInstance();
            // Using local emulator, Read README.md for more info
            firestore.useEmulator("10.0.2.2", 8080);
            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(false)
                    .build();
            firestore.setFirestoreSettings(settings);

            // Use Reflection to inject local Firebase instance
            categoryRepository = new CategoryRepository();
            Field privateFirestore = CategoryRepository.class.getDeclaredField("db");
            privateFirestore.setAccessible(true);
            privateFirestore.set(categoryRepository, firestore);

            // Setup Data
            Map<String, Object> entry;
            entry = new HashMap<String, Object>() {{
                put("name", "Fragrance");
                put("imageURI", "https://picsum.photos/100?fragrance");
            }};
            Tasks.await(firestore.collection("category").document("aYTMjcwRhVYqc6t3GuXy").set(entry));

            entry = new HashMap<String, Object>() {{
                put("name", "Skin Care");
                put("imageURI", "https://picsum.photos/100?skin");
            }};
            Tasks.await(firestore.collection("category").document("B66rJmzEFRKzHzDwJBPM").set(entry));

            // Setup Products
            entry = new HashMap<String, Object>() {{
                put("price", 10.0);
                put("category", firestore.collection("category").document("B66rJmzEFRKzHzDwJBPM"));
            }};
            Tasks.await(firestore.collection("product").document("PfKs97PZ2nqrZrRXFunf").set(entry));

            entry = new HashMap<String, Object>() {{
                put("price", 7.5);
                put("category", firestore.collection("category").document("B66rJmzEFRKzHzDwJBPM"));
            }};
            Tasks.await(firestore.collection("product").document("5wZDeqcLMdRkjL2fkqB4").set(entry));

            entry = new HashMap<String, Object>() {{
                put("price", 5.0);
                put("category", firestore.collection("category").document("B66rJmzEFRKzHzDwJBPM"));
            }};
            Tasks.await(firestore.collection("product").document("gLkPfp93scBtM3FbGz7z").set(entry));

        } catch (NoSuchFieldException | IllegalAccessException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @AfterAll
    static void tearDown() {
        try {
            // Delete documents
            Tasks.await(firestore.collection("category").document("aYTMjcwRhVYqc6t3GuXy").delete());
            Tasks.await(firestore.collection("category").document("B66rJmzEFRKzHzDwJBPM").delete());

            Tasks.await(firestore.collection("product").document("PfKs97PZ2nqrZrRXFunf").delete());
            Tasks.await(firestore.collection("product").document("5wZDeqcLMdRkjL2fkqB4").delete());
            Tasks.await(firestore.collection("product").document("gLkPfp93scBtM3FbGz7z").delete());
        } catch (ExecutionException | InterruptedException exception) {
            fail(exception);
        }
    }

    @Nested
    @DisplayName("getCategories Test")
    class getCategoriesTests{

        @Test
        void testGetCategories() {
            categoryRepository.getCategories();
        }

        @Test
        void testGetCategoriesSize() {
            List<ICategory> categories = categoryRepository.getCategories();
            assertEquals(2, categories.size());
        }

        @Test
        void testGetCategoriesItems() {
            List<ICategory> categories = categoryRepository.getCategories();

            ICategory category_0 = categories.get(0);
            assertEquals("B66rJmzEFRKzHzDwJBPM", category_0.getId());
            assertEquals("Skin Care", category_0.getName());
            assertEquals("https://picsum.photos/100?skin", category_0.getImageURI());

            ICategory category_1 = categories.get(1);
            assertEquals("aYTMjcwRhVYqc6t3GuXy", category_1.getId());
            assertEquals("Fragrance", category_1.getName());
            assertEquals("https://picsum.photos/100?fragrance", category_1.getImageURI());
        }
    }

    @Nested
    @DisplayName("getCategoryById Test")
    class getCategoryByIdTests{

        @Test
        void testGetCategoryNotExist() {
            ICategory category = categoryRepository.getCategoryById("AAAAAAAAAAAAAAAAAAAA");
            assertNull(category);
        }

        @Test
        void testGetCategoryById() {
            categoryRepository.getCategoryById("B66rJmzEFRKzHzDwJBPM");
        }

        @Test
        void testGetCategoryValues() {
            ICategory category = categoryRepository.getCategoryById("aYTMjcwRhVYqc6t3GuXy");
            assertEquals("aYTMjcwRhVYqc6t3GuXy", category.getId());
            assertEquals("Fragrance", category.getName());
            assertEquals("https://picsum.photos/100?fragrance", category.getImageURI());
        }
    }

    @Nested
    @DisplayName("getMaxMinPrice Test")
    class getMaxMinPricesTests{

        @Test
        void testGetMinPrice() {
            assertEquals(new BigDecimal(5.00), categoryRepository.getMinPrice("B66rJmzEFRKzHzDwJBPM"));
        }

        @Test
        void testGetMaxPrice() {
            assertEquals(new BigDecimal(10.00), categoryRepository.getMaxPrice("B66rJmzEFRKzHzDwJBPM"));
        }
    }
}
