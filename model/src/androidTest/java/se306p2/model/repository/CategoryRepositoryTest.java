package se306p2.model.repository;

import static org.junit.jupiter.api.Assertions.fail;

import androidx.test.core.app.ApplicationProvider;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            firestore.useEmulator("localhost", 8080);
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
                put("categoryName", "Fragrance");
                put("imageURI", "https://picsum.photos/100?fragrance");
            }};
            firestore.collection("category").document("aYTMjcwRhVYqc6t3GuXy").set(entry);

            entry = new HashMap<String, Object>() {{
                put("categoryName", "Skin Care");
                put("imageURI", "https://picsum.photos/100?skin");
            }};
            firestore.collection("category").document("B66rJmzEFRKzHzDwJBPM").set(entry);

        } catch (NoSuchFieldException | IllegalAccessException exception) {
            fail(exception);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("getCategories Test")
    class getCategoriesTests{

        @Test
        void testGetCategories() {
            categoryRepository.getCategories();
        }

        @Test
        void testGetCategoiresValues() {
            List<ICategory> categories = categoryRepository.getCategories();
            fail(categories.get(0).getCategoryName());
        }


    }

    @Test
    void getCategoryByID() {
    }

    @Test
    void getMaxPrice() {
    }

    @Test
    void getMinPrice() {
    }
}
