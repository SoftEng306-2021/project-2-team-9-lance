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

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.domain.interfaces.entity.IProductVersion;
import se306p2.model.entities.Benefit;
import se306p2.model.entities.Category;
import se306p2.model.entities.Product;


public class ProductRepositoryTest {
    private static FirebaseFirestore firestore;
    private static ProductRepository productRepository;

    @BeforeAll
    static void setUp()  {
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

            // Use Reflection to inject local Firebase instance
            productRepository = new ProductRepository();
            Field privateFirestore = ProductRepository.class.getDeclaredField("db");
            privateFirestore.setAccessible(true);
            privateFirestore.set(productRepository, firestore);

            // Setup Data
            Map<String, Object> entry;

            // Set up Category 1
            entry = new HashMap<String, Object>() {{
                put("name", "Fragrance");
                put("imageURI", "https://picsum.photos/100?fragrance");
            }};
            Tasks.await(firestore.collection("category").document("pbsd3cBrG47WZg9Caj6H").set(entry));

            entry = new HashMap<String, Object>() {{
                put("name", "Skin Care");
                put("imageURI", "https://picsum.photos/100?skin-care");
            }};
            Tasks.await(firestore.collection("category").document("9TYYjC3J5AUzbw4xsyQq").set(entry));

            // Set up Brand
            entry = new HashMap<String, Object>() {{
                put("name", "Byredo");
                put("imageURI", "https://picsum.photos/100?byredo");
            }};
            Tasks.await(firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN").set(entry));

            // Set up Brand 2
            entry = new HashMap<String, Object>() {{
                put("name", "Chantecaille");
                put("imageURI", "https://picsum.photos/100?chantecaille");
            }};
            Tasks.await(firestore.collection("brand").document("zB8vZBerKRPJTSeVbyTm").set(entry));



            // Set up Benefit
            entry = new HashMap<String, Object>() {{
                put("name", "Floral");
                put("imageURI", "https://picsum.photos/100?floral");
            }};
            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").collection("benefits").document("E9RW7WJh7w8GVWbPTyGs").set(entry));

            List<String> imageURIList = new ArrayList<String>() {{
                add("https://picsum.photos/100?version1");
                add("https://picsum.photos/100?version2");
            }};

            // Set up productVersion
            entry = new HashMap<String, Object>() {{
                put("name","version 1");
                put("hexColor","#023d8a");
                put("imageURI", imageURIList);
                put("order",1);
            }};
            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").collection("productVersion").document("7QccHbnF6wLg8wvzpRdA").set(entry));

            // Setup Product 1
            entry = new HashMap<String, Object>() {{
                put("category", firestore.collection("category").document("pbsd3cBrG47WZg9Caj6H"));
                put("name", "Mojave Ghost EDP");
                put("brand", firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN"));
                put("slogan", "An elegant, warm floral with a woody twist.");
                put("details", "Inspired by the incredibly rare Mojave Ghost flower that blossoms in " +
                        "the arid Mojave Desert, this fragrance is exotic, alluring and delicate just like " +
                        "the flower. It combines woody and floral tones to create a light and graceful yet " +
                        "warm scent that lingers on the skin. Explore a warm floral bouquet but not as " +
                        "you know it, this spiced twist makes for a distinctive signature scent.");
                put("usage", "Store in a cool, dry place and out of direct sunlight. Use on clean dry " +
                        "skin, gently spray onto pulse points avoiding rubbing wrists together.");
                put("link", "https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1");
                put("defaultImageURI", "https://picsum.photos/100?mojave");
                put("form", IProduct.Form.LIQUID);
                put("price",361.0);

            }};
            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").set(entry));

            // Set up Benefit 2
            entry = new HashMap<String, Object>() {{
                put("name", "Floral");
                put("imageURI", "https://picsum.photos/100?floral");
            }};
            Tasks.await(firestore.collection("product").document("QNpwXLxL7SmpQWmfqUtg").collection("benefits").document("7qPjyB2TtYYcf54fhTYB").set(entry));

            List<String> imageURIList2 = new ArrayList<String>() {{
                add("https://picsum.photos/100?version1");
            }};

            // Set up productVersion 2
            entry = new HashMap<String, Object>() {{
                put("name","version 1");
                put("hexColor","#05527e");
                put("imageURI", imageURIList2);
                put("order",1);
            }};
            Tasks.await(firestore.collection("product").document("QNpwXLxL7SmpQWmfqUtg").collection("productVersion").document("2zCtLeM4wR7AUcPQh98T").set(entry));

            List<String> imageURIList3 = new ArrayList<String>() {{
                add("https://picsum.photos/100?version2");
            }};


            // Set up productVersion 3
            entry = new HashMap<String, Object>() {{
                put("name","version 2");
                put("hexColor","#d55487");
                put("imageURI", imageURIList3);
                put("order",2);
            }};
            Tasks.await(firestore.collection("product").document("QNpwXLxL7SmpQWmfqUtg").collection("productVersion").document("KVdN8a4xAvCxTB8CUp3x").set(entry));

            // Setup Products 2
            entry = new HashMap<String, Object>() {{
                put("category", firestore.collection("category").document("pbsd3cBrG47WZg9Caj6H"));
                put("name", "Blanche EDP");
                put("brand", firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN"));
                put("slogan", "A modern take on a classic floral aldehyde.");
                put("details", "A fresh laundered scent which has a delicious warmth at its core " +
                        "that sets it apart. The ephemeral sweetness of the violet is anchored with " +
                        "the delectable milky richness of the sandalwood. A clean, modern take on a " +
                        "classic floral aldehyde.");
                put("usage", "Spray onto pulse points as desired.");
                put("link", "https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1");
                put("defaultImageURI", "https://picsum.photos/100?blanche");
                put("form", IProduct.Form.LIQUID);
                put("price",361.0);
                put("numericRating",4.5);
                put("numReviews",18);

            }};
            Tasks.await(firestore.collection("product").document("QNpwXLxL7SmpQWmfqUtg").set(entry));

            // Set up Benefit 3
            entry = new HashMap<String, Object>() {{
                put("name", "Cruelty-free");
                put("imageURI", "https://picsum.photos/100?Cruelty-free");
            }};
            Tasks.await(firestore.collection("product").document("eX6dyFbyDYMV5ArNS6gx").collection("benefits").document("EPkt2Bx83jb4yd7xX8Zx").set(entry));

            // Set up Benefit 4
            entry = new HashMap<String, Object>() {{
                put("name", "Dewy finish");
                put("imageURI", "https://picsum.photos/100?dewy-finish");
            }};
            Tasks.await(firestore.collection("product").document("eX6dyFbyDYMV5ArNS6gx").collection("benefits").document("wJsVCcCfVVKdWWA5dGma").set(entry));

            List<String> imageURIList4 = new ArrayList<String>() {{
                add("https://picsum.photos/100?version1");
            }};


            // Set up productVersion 4
            entry = new HashMap<String, Object>() {{
                put("name","version 1");
                put("hexColor","#05527e");
                put("imageURI", imageURIList4);
                put("order",1);
            }};
            Tasks.await(firestore.collection("product").document("eX6dyFbyDYMV5ArNS6gx").collection("productVersion").document("qt3KMwbQqHdq68SH4T4D").set(entry));


            // Setup Products 3
            entry = new HashMap<String, Object>() {{
                put("category", firestore.collection("category").document("9TYYjC3J5AUzbw4xsyQq"));
                put("name", "Liquid Lumiere");
                put("brand", firestore.collection("brand").document("zB8vZBerKRPJTSeVbyTm"));
                put("slogan", "A lightweight highlighting fluid.");
                put("details", "Velvety-smooth and buildable, Liquid Lumière blends effortlessly as " +
                        "it highlights to leave skin feeling moisturised and supple. Micro-sized, " +
                        "light reflecting pigments blur imperfections to instantly illuminate the skin. " +
                        "Packaged for portability.");
                put("usage", "After foundation application, use fingertips to tap the formula onto " +
                        "cheekbones, or any other area of desired highlight. Mix a pea-sized amount " +
                        "into foundation or skincare for an all over glow.");
                put("link", "https://www.meccabeauty.co.nz/chantecaille/liquid-lumiere/V-018711.html?cgpath=brands-chante");
                put("defaultImageURI", "https://picsum.photos/100?liquid-lumiere");
                put("form", IProduct.Form.LIQUID);
                put("price",72.0);
                put("numericRating",3.74);
                put("numReviews",46);

            }};
            Tasks.await(firestore.collection("product").document("eX6dyFbyDYMV5ArNS6gx").set(entry));



        } catch (NoSuchFieldException | IllegalAccessException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            fail(e);
        }
    }

    @AfterAll
    static void tearDown() {
        try {
            // Delete documents
            Tasks.await(firestore.collection("category").document("pbsd3cBrG47WZg9Caj6H").delete());
            Tasks.await(firestore.collection("category").document("9TYYjC3J5AUzbw4xsyQq").delete());

            Tasks.await(firestore.collection("brand").document("bXWJLz3maMk9rtdWFRuN").delete());
            Tasks.await(firestore.collection("brand").document("zB8vZBerKRPJTSeVbyTm").delete());

            Tasks.await(firestore.collection("product").document("BUVdXxq9sEZfPurxGT5c").delete());
            Tasks.await(firestore.collection("product").document("QNpwXLxL7SmpQWmfqUtg").delete());
            Tasks.await(firestore.collection("product").document("eX6dyFbyDYMV5ArNS6gx").delete());

            FirebaseApp.clearInstancesForTest();
        } catch (ExecutionException | InterruptedException exception) {
            fail(exception);
        }
    }

    @Nested
    @DisplayName("getProducts Test")
    class getProductsTests {

        @Test
        void testGetProducts() {
            assertNotNull(productRepository.getProducts());
        }


        @Test
        void testGetProductsSize() {
            List<IProduct> products = productRepository.getProducts();
            assertEquals(3, products.size());
        }

        @Test
        void testGetProductsItems() {
            List<IProduct> products = productRepository.getProducts();

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());

            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

            IProduct product_2 = products.get(2);

            assertEquals("eX6dyFbyDYMV5ArNS6gx", product_2.getId());
            assertEquals("Liquid Lumiere", product_2.getName());
            assertEquals("A lightweight highlighting fluid.", product_2.getSlogan());
            assertEquals("Velvety-smooth and buildable, Liquid Lumière blends effortlessly " +
                    "as it highlights to leave skin feeling moisturised and supple. Micro-sized, " +
                    "light reflecting pigments blur imperfections to instantly illuminate the skin. " +
                    "Packaged for portability.",product_2.getDetails());
            assertEquals("After foundation application, use fingertips to tap the formula " +
                    "onto cheekbones, or any other area of desired highlight. Mix a pea-sized amount " +
                    "into foundation or skincare for an all over glow.",product_2.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/chantecaille/liquid-lumiere/V-018711.html?cgpath=brands-chante", product_2.getLink());
            assertEquals("https://picsum.photos/100?liquid-lumiere", product_2.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_2.getForm());
            assertEquals(new BigDecimal(72), product_2.getPrice());
            assertEquals(3.74, product_2.getNumericRating());
            assertEquals(46, product_2.getNumReviews());


        }


    }

    @Nested
    @DisplayName("getProductById Test")
    class getProductByIdTests {

        @Test
        void testGetProductNotExist() {
            IProduct product = productRepository.getProductById("AAAAAAAAAAAAAAAAAAAA");
            assertNull(product);
        }

        @Test
        void testGetProductById() {
            IProduct product = productRepository.getProductById("BUVdXxq9sEZfPurxGT5c");
            assertNotNull(product);
        }

        @Test
        void testGetProductValues() {
            IProduct product = productRepository.getProductById("eX6dyFbyDYMV5ArNS6gx");
            assertEquals("eX6dyFbyDYMV5ArNS6gx", product.getId());
            assertEquals("Liquid Lumiere", product.getName());
            assertEquals("A lightweight highlighting fluid.", product.getSlogan());
            assertEquals("Velvety-smooth and buildable, Liquid Lumière blends effortlessly " +
                    "as it highlights to leave skin feeling moisturised and supple. Micro-sized, " +
                    "light reflecting pigments blur imperfections to instantly illuminate the skin. " +
                    "Packaged for portability.",product.getDetails());
            assertEquals("After foundation application, use fingertips to tap the formula " +
                    "onto cheekbones, or any other area of desired highlight. Mix a pea-sized amount " +
                    "into foundation or skincare for an all over glow.",product.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/chantecaille/liquid-lumiere/V-018711.html?cgpath=brands-chante", product.getLink());
            assertEquals("https://picsum.photos/100?liquid-lumiere", product.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product.getForm());
            assertEquals(new BigDecimal(72), product.getPrice());
            assertEquals(3.74, product.getNumericRating());
            assertEquals(46, product.getNumReviews());
        }
    }

    @Nested
    @DisplayName("getProductByCategoryId Test")
    class getProductByCategoryIdTests {

        @Test
        void testGetProductByCategoryIdNotExist() {
            List<IProduct> emptyList = new ArrayList<>();
            List<IProduct> products = productRepository.getProductsByCategoryId("AAAAAAAAAAAAAAAAAAAA");

            assertNotNull(products);
            assertEquals(emptyList,products);
        }

        @Test
        void testGetProductByCategoryIdOneProduct() {

            List<IProduct> products = productRepository.getProductsByCategoryId("9TYYjC3J5AUzbw4xsyQq");

            assertEquals(1,products.size());

            IProduct product = products.get(0);

            assertEquals("eX6dyFbyDYMV5ArNS6gx", product.getId());
            assertEquals("Liquid Lumiere", product.getName());
            assertEquals("A lightweight highlighting fluid.", product.getSlogan());
            assertEquals("Velvety-smooth and buildable, Liquid Lumière blends effortlessly " +
                    "as it highlights to leave skin feeling moisturised and supple. Micro-sized, " +
                    "light reflecting pigments blur imperfections to instantly illuminate the skin. " +
                    "Packaged for portability.",product.getDetails());
            assertEquals("After foundation application, use fingertips to tap the formula " +
                    "onto cheekbones, or any other area of desired highlight. Mix a pea-sized amount " +
                    "into foundation or skincare for an all over glow.",product.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/chantecaille/liquid-lumiere/V-018711.html?cgpath=brands-chante", product.getLink());
            assertEquals("https://picsum.photos/100?liquid-lumiere", product.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product.getForm());
            assertEquals(new BigDecimal(72), product.getPrice());
            assertEquals(3.74, product.getNumericRating());
            assertEquals(46, product.getNumReviews());


        }

        @Test
        void testGetProductByCategoryIdMoreThanOneProduct() {

            List<IProduct> products = productRepository.getProductsByCategoryId("pbsd3cBrG47WZg9Caj6H");

            assertEquals(2,products.size());

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and " +
                    "graceful yet warm scent that lingers on the skin. Explore a warm floral bouquet " +
                    "but not as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());


            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

        }

    }

    @Nested
    @DisplayName("getProductByCategory Test")
    class getProductByCategoryTests {

        @Test
        void testGetProductByCategoryNotExist() {


            ICategory category = new Category("AAAAAAAAAAAAAAAAAAA","Fake Category","https://picsum.photos/100?fake-category");

            List<IProduct> emptyList = new ArrayList<>();
            List<IProduct> products = productRepository.getProductsByCategory(category);

            assertNotNull(products);
            assertEquals(emptyList,products);
        }

        @Test
        void testGetProductByCategoryOneProduct() {

            ICategory category = new Category("9TYYjC3J5AUzbw4xsyQq","Skin Care","https://picsum.photos/100?skin-care");

            List<IProduct> products = productRepository.getProductsByCategory(category);

            assertEquals(1,products.size());

            IProduct product = products.get(0);

            assertEquals("eX6dyFbyDYMV5ArNS6gx", product.getId());
            assertEquals("Liquid Lumiere", product.getName());
            assertEquals("A lightweight highlighting fluid.", product.getSlogan());
            assertEquals("Velvety-smooth and buildable, Liquid Lumière blends effortlessly " +
                    "as it highlights to leave skin feeling moisturised and supple. Micro-sized, light " +
                    "reflecting pigments blur imperfections to instantly illuminate the skin. " +
                    "Packaged for portability.",product.getDetails());
            assertEquals("After foundation application, use fingertips to tap the formula onto " +
                    "cheekbones, or any other area of desired highlight. Mix a pea-sized amount into " +
                    "foundation or skincare for an all over glow.",product.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/chantecaille/liquid-lumiere/V-018711.html?cgpath=brands-chante", product.getLink());
            assertEquals("https://picsum.photos/100?liquid-lumiere", product.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product.getForm());
            assertEquals(new BigDecimal(72), product.getPrice());
            assertEquals(3.74, product.getNumericRating());
            assertEquals(46, product.getNumReviews());


        }

        @Test
        void testGetProductByCategoryMoreThanOneProduct() {
            ICategory category = new Category("pbsd3cBrG47WZg9Caj6H","Fragrance","https://picsum.photos/100?fragrance");
            List<IProduct> products = productRepository.getProductsByCategory(category);

            assertEquals(2,products.size());

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());


            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

        }

    }

    @Nested
    @DisplayName("getProductVersion Test")
    class getProductVersionTests {

        @Test
        void testGetProductVersionsNotExist() {

            List<IProductVersion> emptyList = new ArrayList<>();
            List<IProductVersion> productVersions = productRepository.getProductVersions("fakeproductid");

            assertNotNull(productVersions);
            assertEquals(emptyList,productVersions);

        }

        @Test
        void testGetProductVersionsOneVersionAndTwoImageURI() {

            List<IProductVersion> productVersions = productRepository.getProductVersions("BUVdXxq9sEZfPurxGT5c");

            assertEquals(1,productVersions.size());

            IProductVersion productVersion = productVersions.get(0);

            assertEquals("7QccHbnF6wLg8wvzpRdA",productVersion.getId());
            assertEquals("version 1", productVersion.getName());
            assertEquals("#023d8a",productVersion.getHexColor());
            assertEquals("https://picsum.photos/100?version1",productVersion.getImageURI().get(0));
            assertEquals("https://picsum.photos/100?version2",productVersion.getImageURI().get(1));
            assertEquals(1,productVersion.getOrder());



        }

        @Test
        void testGetProductVersionsTwoVersionsAndOneImageURI() {
            List<IProductVersion> productVersions = productRepository.getProductVersions("QNpwXLxL7SmpQWmfqUtg");

            assertEquals(2,productVersions.size());

            IProductVersion productVersion = productVersions.get(0);

            assertEquals("2zCtLeM4wR7AUcPQh98T",productVersion.getId());
            assertEquals("version 1", productVersion.getName());
            assertEquals("#05527e",productVersion.getHexColor());
            assertEquals("https://picsum.photos/100?version1",productVersion.getImageURI().get(0));
            assertEquals(1,productVersion.getOrder());

            IProductVersion productVersion_1 = productVersions.get(1);

            assertEquals("KVdN8a4xAvCxTB8CUp3x",productVersion_1.getId());
            assertEquals("version 2", productVersion_1.getName());
            assertEquals("#d55487",productVersion_1.getHexColor());
            assertEquals("https://picsum.photos/100?version2",productVersion_1.getImageURI().get(0));
            assertEquals(2,productVersion_1.getOrder());


        }

    }

    @Nested
    @DisplayName("getBenefits Test")
    class getBenefitsTests {

        @Test
        void testGetBenefitsNotExist() {

            List<IBenefit> emptyList = new ArrayList<>();
            List<IBenefit> benefits = productRepository.getBenefits("fakeproductid");

            assertNotNull(benefits);
            assertEquals(emptyList,benefits);

        }

        @Test
        void testGetBenefitsOneBenefit() {

            List<IBenefit> benefits = productRepository.getBenefits("BUVdXxq9sEZfPurxGT5c");

            assertEquals(1,benefits.size());

            IBenefit benefit = benefits.get(0);

            assertEquals("E9RW7WJh7w8GVWbPTyGs",benefit.getId());
            assertEquals("Floral", benefit.getName());
            assertEquals("https://picsum.photos/100?floral",benefit.getImageURI());



        }

        @Test
        void testGetBenefitsTwoBenefits() {

            List<IBenefit> benefits = productRepository.getBenefits("eX6dyFbyDYMV5ArNS6gx");

            assertEquals(2,benefits.size());

            IBenefit benefit_0 = benefits.get(0);

            assertEquals("EPkt2Bx83jb4yd7xX8Zx",benefit_0.getId());
            assertEquals("Cruelty-free", benefit_0.getName());
            assertEquals("https://picsum.photos/100?Cruelty-free",benefit_0.getImageURI());

            IBenefit benefit_1 = benefits.get(1);

            assertEquals("wJsVCcCfVVKdWWA5dGma",benefit_1.getId());
            assertEquals("Dewy finish", benefit_1.getName());
            assertEquals("https://picsum.photos/100?dewy-finish",benefit_1.getImageURI());

        }

    }

    @Nested
    @DisplayName("getProductsByFilter Test")
    class getProductsByFilterTests {

        @Test
        void testGetProductsByFilterBothIdsNull() {
            try {
                productRepository.getProductsByFilter(null, null, new BigDecimal(5.00), null);
                fail();
            } catch (UnsupportedOperationException e) {
                assertEquals("category and brand cannot be both null",e.getMessage());
            }

        }

        @Test
        void testGetProductsByFilterCategoryOnly() {

            List<IProduct> products = productRepository.getProductsByFilter("pbsd3cBrG47WZg9Caj6H", null, null, null);

            assertEquals(2,products.size());

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and " +
                    "graceful yet warm scent that lingers on the skin. Explore a warm floral bouquet " +
                    "but not as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());


            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

        }

        @Test
        void testGetProductsByFilterBrandOnly() {

            List<IProduct> products = productRepository.getProductsByFilter(null, "bXWJLz3maMk9rtdWFRuN", null, null);

            assertEquals(2,products.size());

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());


            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

        }

        @Test
        void testGetProductsByFilterCategoryAndBrand() {

            List<IProduct> products = productRepository.getProductsByFilter("pbsd3cBrG47WZg9Caj6H", "bXWJLz3maMk9rtdWFRuN", null, null);

            assertEquals(2,products.size());

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());


            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

        }

        @Test
        void testGetProductsByFilterCategoryAndBrandWithMin() {

            List<IProduct> products = productRepository.getProductsByFilter("pbsd3cBrG47WZg9Caj6H", "bXWJLz3maMk9rtdWFRuN", new BigDecimal(361), null);

            assertEquals(2,products.size());

            IProduct product_0 = products.get(0);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_0.getId());
            assertEquals("Mojave Ghost EDP",product_0.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_0.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_0.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_0.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_0.getForm());
            assertEquals(new BigDecimal(361),product_0.getPrice());
            assertEquals(0.0,product_0.getNumericRating());
            assertEquals(0,product_0.getNumReviews());


            IProduct product_1 = products.get(1);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_1.getId());
            assertEquals("Blanche EDP", product_1.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_1.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_1.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_1.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_1.getForm());
            assertEquals(new BigDecimal(361), product_1.getPrice());
            assertEquals(4.5, product_1.getNumericRating());
            assertEquals(18, product_1.getNumReviews());

        }

        @Test
        void testGetProductsByFilterCategoryAndBrandWithMax() {

            List<IProduct> products = productRepository.getProductsByFilter("pbsd3cBrG47WZg9Caj6H", "bXWJLz3maMk9rtdWFRuN", null, new BigDecimal(361));

            assertEquals(2,products.size());

            IProduct product_1 = products.get(1);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_1.getId());
            assertEquals("Mojave Ghost EDP",product_1.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_1.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_1.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_1.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_1.getForm());
            assertEquals(new BigDecimal(361),product_1.getPrice());
            assertEquals(0.0,product_1.getNumericRating());
            assertEquals(0,product_1.getNumReviews());


            IProduct product_0 = products.get(0);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_0.getId());
            assertEquals("Blanche EDP", product_0.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_0.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_0.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_0.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_0.getForm());
            assertEquals(new BigDecimal(361), product_0.getPrice());
            assertEquals(4.5, product_0.getNumericRating());
            assertEquals(18, product_0.getNumReviews());

        }

        @Test
        void testGetProductsByFilterBothMinMaxNotNull() {

            List<IProduct> products = productRepository.getProductsByFilter("pbsd3cBrG47WZg9Caj6H", "bXWJLz3maMk9rtdWFRuN", new BigDecimal(361), new BigDecimal(361));
            assertEquals(2,products.size());

            IProduct product_1 = products.get(1);

            assertEquals("BUVdXxq9sEZfPurxGT5c",product_1.getId());
            assertEquals("Mojave Ghost EDP",product_1.getName());
            assertEquals("An elegant, warm floral with a woody twist.",product_1.getSlogan());
            assertEquals("Inspired by the incredibly rare Mojave Ghost flower that blossoms " +
                    "in the arid Mojave Desert, this fragrance is exotic, alluring and delicate just " +
                    "like the flower. It combines woody and floral tones to create a light and graceful " +
                    "yet warm scent that lingers on the skin. Explore a warm floral bouquet but not " +
                    "as you know it, this spiced twist makes for a distinctive signature scent.",product_1.getDetails());
            assertEquals("Store in a cool, dry place and out of direct sunlight. Use on clean " +
                    "dry skin, gently spray onto pulse points avoiding rubbing wrists together.",product_1.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/mojave-ghost-edp-100ml/I-019967.html#start=1",product_1.getLink());
            assertEquals("https://picsum.photos/100?mojave",product_1.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID,product_1.getForm());
            assertEquals(new BigDecimal(361),product_1.getPrice());
            assertEquals(0.0,product_1.getNumericRating());
            assertEquals(0,product_1.getNumReviews());


            IProduct product_0 = products.get(0);

            assertEquals("QNpwXLxL7SmpQWmfqUtg", product_0.getId());
            assertEquals("Blanche EDP", product_0.getName());
            assertEquals("A modern take on a classic floral aldehyde.", product_0.getSlogan());
            assertEquals("A fresh laundered scent which has a delicious warmth at its core " +
                    "that sets it apart. The ephemeral sweetness of the violet is anchored with the " +
                    "delectable milky richness of the sandalwood. A clean, modern take on a classic " +
                    "floral aldehyde.",product_0.getDetails());
            assertEquals("Spray onto pulse points as desired.",product_0.getUsage());
            assertEquals("https://www.meccabeauty.co.nz/byredo/blanche-edp-100ml/I-008256.html#start=1", product_0.getLink());
            assertEquals("https://picsum.photos/100?blanche", product_0.getDefaultImageURI());
            assertEquals(IProduct.Form.LIQUID, product_0.getForm());
            assertEquals(new BigDecimal(361), product_0.getPrice());
            assertEquals(4.5, product_0.getNumericRating());
            assertEquals(18, product_0.getNumReviews());

        }

    }

    @Nested
    @DisplayName("getProductsBySearch Test")
    class getProductsBySearchTerm {

        @Test
        void testGetProductsBySearch() {

            productRepository.getProductsBySearch("");

        }

        @Test
        void testGetProductsBySearchBlankString() {

            List<IProduct> products = productRepository.getProductsBySearch("");
            assertEquals(3,products.size());

        }

        @Test
        void testGetProductsBySearchNoMatchingString() {

            List<IProduct> products = productRepository.getProductsBySearch("nomatchingterm");
            assertEquals(0,products.size());

        }

        @Test
        void testGetProductsBySearchOneMatchingString() {

            List<IProduct> products = productRepository.getProductsBySearch("Liq");
            assertEquals(1,products.size());

            IProduct product_0 = products.get(0);
            assertEquals("Liquid Lumiere", product_0.getName());

        }

    }

}
