package se306p2.model.repository;



import static org.junit.jupiter.api.Assertions.fail;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.util.Assert;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class CategoryRepositoryTest {

    private CategoryRepository categoryRepository;

    @BeforeAll
    static void setUp()  {
        // 10.0.2.2 is the special IP address to connect to the 'localhost' of
        // the host computer from an Android emulator.

        try {
            FirebaseApp.initializeApp(ApplicationProvider);
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            firestore.useEmulator("localhost", 8080);

            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(false)
                    .build();
            firestore.setFirestoreSettings(settings);

            CategoryRepository categoryRepository = new CategoryRepository();
            Field privateFirestore = CategoryRepository.class.getDeclaredField("db");
            privateFirestore.setAccessible(true);
            privateFirestore.set(categoryRepository, firestore);

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
        void getCategories() {
            categoryRepository.getCategories();
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