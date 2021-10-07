package se306p2.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import androidx.test.core.app.ApplicationProvider;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IProduct;
import se306p2.model.entities.Benefit;


public class ProductRepositoryTest {
    private static FirebaseFirestore firestore;
    private static ProductRepository productRepository;

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
            productRepository = new ProductRepository();
            Field privateFirestore = ProductRepository.class.getDeclaredField("db");
            privateFirestore.setAccessible(true);
            privateFirestore.set(productRepository, firestore);

            // Setup Data
            Map<String, Object> entry;
            entry = new HashMap<String, Object>() {{
                put("name", "Fragrance");
                put("imageURI", "https://picsum.photos/100?fragrance");
            }};
            Tasks.await(firestore.collection("category").document("pbsd3cBrG47WZg9Caj6H").set(entry));

            // Set up Brand
            entry = new HashMap<String, Object>() {{
                put("name", "Byredo");
                put("imageURI", "https://picsum.photos/100?byredo");
            }};
            Tasks.await(firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN").set(entry));

            // Set up Benefit
            entry = new HashMap<String, Object>() {{
                put("name", "Floral");
                put("imageURI", "https://picsum.photos/100?floral");
            }};
            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").collection("benefits").document("E9RW7WJh7w8GVWbPTyGs").set(entry));

            List<String> imageURIList = new ArrayList<>();
            imageURIList.add("https://picsum.photos/100?version1");
            imageURIList.add("https://picsum.photos/100?version2");
            // Set up productVersion
            entry = new HashMap<String, Object>() {{
                put("name","version 1");
                put("hexColor","#023d8a");
                put("imageURI", imageURIList);
                put("order",1);
            }};
            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").collection("productVersion").document("7QccHbnF6wLg8wvzpRdA").set(entry));

            // Setup Products
            entry = new HashMap<String, Object>() {{
                put("category", firestore.collection("category").document("pbsd3cBrG47WZg9Caj6H"));
                put("name", "Mojave Ghost EDP");
                put("brand", firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN"));
                put("slogan", "An elegant, warm floral with a woody twist.");
                put("details", "Inspired by the incredibly rare Mojave Ghost flower that blossoms in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just like the flower. It combines woody and floral tones to create a light and graceful yet warm scent that lingers on the skin. Explore a warm floral bouquet but not as you know it, this spiced twist makes for a distinctive signature scent.");
                put("usage", "Store in a cool, dry place and out of direct sunlight. Use on clean dry skin, gently spray onto pulse points avoiding rubbing wrists together.");
                put("link", "https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1");
                put("form", IProduct.Form.LIQUID);
                put("price",361);

            }};
            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").set(entry));


        } catch (NoSuchFieldException | IllegalAccessException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e);
        }
    }

//    @AfterAll
//    static void tearDown() {
//        try {
//            // Delete documents
//            Tasks.await(firestore.collection("category").document("aYTMjcwRhVYqc6t3GuXy").delete());
//
//
//
//        } catch (ExecutionException | InterruptedException exception) {
//            fail(exception);
//        }
//    }

    @Nested
    @DisplayName("getProducts Test")
    class getProductsTests{

        @Test
        void testGetProducts() {
            productRepository.getProducts();
        }

        @Test
        void testGetProductsSize() {
            List<IProduct> products = productRepository.getProducts();
            assertEquals(1, products.size());
        }

//        @Test
//        void testGetProductsItems() {
//            List<IProduct> products = productRepository.getProducts();
//
//            IProduct product_0 = categories.get(0);
//            assertEquals("B66rJmzEFRKzHzDwJBPM", product_0.getId());
//            assertEquals("Skin Care", product_0.getName());
//            assertEquals("https://picsum.photos/100?skin", product_0.getImageURI());
//
//            IProduct product_1 = categories.get(1);
//            assertEquals("aYTMjcwRhVYqc6t3GuXy", product_1.getId());
//            assertEquals("Fragrance", product_1.getName());
//            assertEquals("https://picsum.photos/100?fragrance", product_1.getImageURI());
//        }
    }

//    @Nested
//    @DisplayName("getproductById Test")
//    class getproductByIdTests{
//
//        @Test
//        void testGetproductNotExist() {
//            IProduct product = productRepository.getproductById("AAAAAAAAAAAAAAAAAAAA");
//            assertNull(product);
//        }
//
//        @Test
//        void testGetproductById() {
//            IProduct product = productRepository.getproductById("B66rJmzEFRKzHzDwJBPM");
//            assertNotNull(product);
//        }
//
//        @Test
//        void testGetproductValues() {
//            IProduct product = productRepository.getproductById("aYTMjcwRhVYqc6t3GuXy");
//            assertEquals("aYTMjcwRhVYqc6t3GuXy", product.getId());
//            assertEquals("Fragrance", product.getName());
//            assertEquals("https://picsum.photos/100?fragrance", product.getImageURI());
//        }
//    }


}
