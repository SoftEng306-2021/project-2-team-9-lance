package se306p2.view.common.placeholders;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;

public class PlaceholderGenerator {

    private PlaceholderGenerator() {};


    /**
     * @return One random product from the pool of placeholder products
     */
    public static IProduct getProduct() {
        int randomPosition = ThreadLocalRandom.current().nextInt(0, placeholderProducts.size());
        return placeholderProducts.get(randomPosition);
    }

    /**
     * Retrieves the specified number of placeholder products in shuffled order
     * If specified number is larger than the pool, the pool is concatenated to itself
     * multiple times after it's shuffled, until the list is large enough.
     * @param numberRequired
     * @return numberRequired number of placeholder products
     */
    public static List<IProduct> getProducts(int numberRequired) {
        List<IProduct> placeholderProductsCopy = new ArrayList<>(placeholderProducts);
        Collections.shuffle(placeholderProductsCopy);

        List<IProduct> result = new ArrayList<>(placeholderProductsCopy);
        while (numberRequired > result.size()) {
            result.addAll(placeholderProductsCopy);
        }

        return result.subList(0, numberRequired);
    }

    public static ICategory getCategory() {
        int randomPosition = ThreadLocalRandom.current().nextInt(0, placeholderCategories.size());
        return placeholderCategories.get(randomPosition);
    }

    public static List<ICategory> getCategories(int numberRequired) {
        List<ICategory> placeholderCategoriesCopy = new ArrayList<>(placeholderCategories);
        Collections.shuffle(placeholderCategoriesCopy);

        List<ICategory> result = new ArrayList<>(placeholderCategoriesCopy);
        while (numberRequired > result.size()) {
            result.addAll(placeholderCategoriesCopy);
        }

        return result.subList(0, numberRequired);
    }

    private static List<IProduct> placeholderProducts = new ArrayList<>(
            Arrays.asList(
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Soft Matte Complete Foundation",
                            "0",
                            "Nars",
                            "16-hour wear matte foundation",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(57.00),
                            new Double(4.3),
                            59,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dw7d8246e0/images/homepage/2021/october/WK1/V-050364.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Honey Infused Hair Mask",
                            "0",
                            "Gisou",
                            "Nourishes, strengthens and tames ",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(88.00),
                            new Double(5.0),
                            5,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dweb603afb/images/homepage/2021/october/WK1/I-052582.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Blush Divine Radiant Lip & Cheek",
                            "0",
                            "Rose Inc",
                            "Cheek and lip colour",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(43.00),
                            new Double(5.0),
                            4,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dwec28e96d/images/homepage/2021/october/WK1/V-052679.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Watermelon Glow Sleeping Mask",
                            "0",
                            "Glow Recipe",
                            "Super hydrating watermelon mask",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(43.00),
                            new Double(5.0),
                            4,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dw54490723/images/homepage/2021/october/WK1/I-038857.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Wake Up With Me",
                            "0",
                            "Sunday Riley",
                            "A regime for the morning",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(135.00),
                            new Double(4.7),
                            49,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dwaef39392/images/homepage/2021/october/WK1/I-045749.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Honey Infused Hair Perfume",
                            "0",
                            "Gisou",
                            "Scents, repairs and restores damaged hair.",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(63.00),
                            new Double(3.0),
                            6,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dw1d825b4c/images/homepage/2021/october/WK1/V-052583.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "The Rice Wash",
                            "0",
                            "Tatcha",
                            "A gentle and hydrating creamy foaming cleanser.",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(49.00),
                            new Double(4.7),
                            259,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dwe1b6f270/images/homepage/2021/october/WK1/I-043839.jpg"
                    ),
                    new PlaceholderProduct(
                            "",
                            "0",
                            "Wonderland Bloom Candle",
                            "0",
                            "Floral Street",
                            "A gentle and hydrating creamy foaming cleanser.",
                            "details",
                            "usage",
                            "link",
                            null,
                            null,
                            new BigDecimal(67.00),
                            new Double(0),
                            0,
                            "https://www.mecca.com.au/on/demandware.static/-/Sites-MeccaAU-Library/default/dw5c1ded12/images/homepage/2021/october/WK1/I-052559.jpg"
                    )
            )
    );

    private static List<ICategory> placeholderCategories = new ArrayList<>(
            Arrays.asList(
                    new PlaceholderCategory(
                            "0",
                            "Makeup",
                            "https://user-images.githubusercontent.com/62003343/135978393-8217fb9c-115b-4d41-b1f6-520854341c44.png"
                    ),
                    new PlaceholderCategory(
                            "1",
                            "Skincare",
                            "https://user-images.githubusercontent.com/62003343/135978363-757a8152-7d95-4a27-9576-31880b94b659.png"
                    ),
                    new PlaceholderCategory(
                            "2",
                            "Fragrance",
                            "https://user-images.githubusercontent.com/62003343/135978373-342b58d4-0f62-4e0c-92d1-76f864759196.png"
                    ),
                    new PlaceholderCategory(
                            "3",
                            "Lotions",
                            "https://user-images.githubusercontent.com/62003343/135978379-c6ace055-00dd-464d-b4d2-4275641696c4.png"
                    ),
                    new PlaceholderCategory(
                            "4",
                            "Body",
                            "https://user-images.githubusercontent.com/62003343/135979276-4441ed61-0299-4e38-9c49-335036ed9d45.png"
                    )
            )
    );



}
