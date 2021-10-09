package se306p2.view.common.placeholders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import se306p2.domain.interfaces.entity.IBenefit;
import se306p2.domain.interfaces.entity.ICategory;
import se306p2.domain.interfaces.entity.IProduct;
import se306p2.view.common.placeholders.placeholderEntities.PlaceholderBenefit;
import se306p2.view.common.placeholders.placeholderEntities.PlaceholderCategory;
import se306p2.view.common.placeholders.placeholderEntities.PlaceholderProduct;

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
     * @return The entire existing pool of placeholder products, shuffled.
     */
    public static  List<IProduct> getProducts() {
        List<IProduct> placeholderProductsCopy = new ArrayList<>(placeholderProducts);
        Collections.shuffle(placeholderProductsCopy);
        return placeholderProductsCopy;
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

    public static List<ICategory> getCategories() {
        List<ICategory> placeholderCategoriesCopy = new ArrayList<>(placeholderCategories);
        Collections.shuffle(placeholderCategoriesCopy);
        return placeholderCategoriesCopy;
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

    public static List<IBenefit> getBenefits() {
        List<IBenefit> placeholderBenefitsCopy = new ArrayList<>(placeholderBenefits);
        Collections.shuffle(placeholderBenefitsCopy);
        return placeholderBenefitsCopy;
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
                            "The MECCA view:\n" +
                                    "NARS debuts a breakthrough oil-free matte foundation with this high-performing, full-coverage formula. Crafted to leave you with a skin-like finish that not only mimics a healthy-looking complexion but delivers it too thanks to NARS’ Anti-Oxidation Complex and the Hydramatte Balancing Complex. The Anti-Oxidation Complex helps prevent colour shifting while protecting skin from pollution and blue-light damage while the Hydramatte Balancing Complex balances excess sebum and keeps skin hydrated with a combination of micro-algae and bio hyaluronic acid. Coupled with superior oil absorbing powders the formula serves up 16-hour wear that creates a smooth, mattified, second-skin look that’s transfer- and shine-proof.\n" +
                                    "\n" +
                                    "Key ingredients:\n" +
                                    "Superior oil absorbing powders: help absorb the appearance of excess sebum for a lasting mattifying effect so complexion stays fresh.\n" +
                                    "Optimal diffusing powder: maximizes light diffusion with spherical surface powders designed to absorb and refract light for an instant blurring effect that creates a smooth, second-skin appearance.\n" +
                                    "Micro-algae extract: helps balance the appearance of excess sebum so skin looks renewed and perfected.\n" +
                                    "Bio hyaluronic acid: helps hydrate skin and maintain its moisture balance, so complexion looks bright and smooth.",
                            "Dispense a small amount on to the back of your hand. One drop delivers full coverage with fingertip application. PAT product with fingertips. Start at the center of the face and gently PRESS the foundation on skin. Use a stippling motion to BLEND outward. Work in small sections, focusing on one area at a time. After each use, wipe off excess product from nozzle and close cap tightly.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "The MECCA view:\n" +
                                    "Reach for this intensive nourishing mask formulated to hydrate and replenish while improving elasticity, shine and health when hair needs TLC. Enriched with Mirsalehi Honey sustainably-sourced from the Mirsalehi Bee Garden, the mask combats dryness and serves up shine and manageability without weighing down your locks. A natural humectant, honey restores hair's natural moisture balance, for healthier, softer and stronger locks with a silky shine. \n" +
                                    " \n" +
                                    "Key ingredients\n" +
                                    "Mirsalehi Honey: rich in vitamins, minerals, amino acids and antioxidants, honey deeply nourishes and moisturises to repair and restore dry, damaged locks. \n" +
                                    " \n" +
                                    "Made without:\n" +
                                    "Silicones, sulfates, parabens, phthalates and animal testing.",
                            "Post-shampooing treatment\n" +
                                    "Apply a generous amount of hair mask onto towel-dry hair from root to tip. Leave in for 5-7 minutes, then rinse thoroughly. Follow-up with a conditioner to lock in the nutrients. For best results, use 1-2 times per week. \n" +
                                    " \n" +
                                    "Mix with Gisou Honey Infused Hair Oil\n" +
                                    "Incorporate a few drops of Gisou Honey Infused Hair Oil into the mask for an extra boost of moisture. Mix well and apply a generous amount of the mask onto towel-dry hair from root to tip. Leave in for 5-7 minutes, then rinse thoroughly. For best results, use 1-2 times a week.\n" +
                                    " \n" +
                                    "Deep treatment\n" +
                                    "Distribute a generous amount of the mask onto slightly damp hair from root to tip. For even distribution, detangle your hair with a comb first. Massage the mask into your scalp and cover your hair with a warm towel for approximately 15 minutes. The moisture from the warm towel allows the mask to be absorbed into the scalp more fully. For best results, use 1-2 times a week. If your hair is extremely dry, leave it in as an overnight treatment. Rinse the mask out in the morning.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "The MECCA view:\n" +
                                    "Douse cheeks and lips in longwearing and buildable colour that melts into skin for a smooth complexion that glows. This pigment-rich formula contains a spectrum of skincare ingredients that improve skin health with each use: hydrating squalane, protective vitamin E and barrier-strengthening ceramides work together to support skin while cheeks are washed in a radiant flush of colour. \n" +
                                    " \n" +
                                    "Key ingredients:\n" +
                                    "Squalane: pure, sustainable and 100% plant-derived moisturiser that adds weightless hydration.\n" +
                                    "Vitamin E: a powerful antioxidant that nourishes and protects skin.\n" +
                                    "Sodium hyaluronate: smooths imperfections.\n" +
                                    "Ceramides: strengthens skin’s natural protective barrier.\n" +
                                    " \n" +
                                    "Made without:\n" +
                                    "Parabens, formaldehydes, formaldehyde-releasing agents, phthalates, mineral oil, retinyl palmitate, oxybenzone, coal tar, hydroquinone, sulfates, sls, sles, synthetic fragrance (less than 1%), triclocarban, triclosan, animal products and testing.",
                            "Using fingertips or swirling colour with a brush, apply to cheeks and blend upward. Add more to build colour and intensity if desired. Can also be applied to lips.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "The MECCA view:\n" +
                                    "A luxuriously bounceable, breathable sleeping mask that smooths and perfects skin overnight. Infused with watermelon, AHA's and hyaluronic acid, this pillow-proof formula gently exfoliates and refines pores whilst intensely hydrating the skin. The next morning, your complexion will look brighter, smoother and more radiant. \n" +
                                    "\n" +
                                    "Want glowing skin overnight? These overnight beauty products are what dreams are made of. Read it now.\n" +
                                    "\n" +
                                    "Key ingredients:\n" +
                                    "Watermelon: intensely hydrates, soothes and fights free radical damage to ward off fine lines, wrinkles and dark spots. \n" +
                                    "Hyaluronic acid: a powerful humectant that maintains the skin’s moisture levels to prevent dehydration.\n" +
                                    "AHA: buffs away dead skin cells, smooths fine lines, softens texture, diminishes dark spots and clarifies pores.\n" +
                                    "\n" +
                                    "Made without:\n" +
                                    "Animal products, parabens, mineral oil, sulphates, phthalates and synthetic dyes. ",
                            "To Use As A Sleeping Mask:\n" +
                                    "Use two scoops or a generous layer on skin as the final step of the evening routine. For drier skin types, layer an additional dime-sized amount onto the skin. Be sure to gently pat, not rub, mask into skin until absorbed and wash thoroughly in the morning.\n" +
                                    "\n" +
                                    "\n" +
                                    "To Use As A Wash Off Mask:\n" +
                                    "Use the spatula to scoop the face mask and spread a thick layer gently across the face from forehead to chin. Rinse with lukewarm water after 10 minutes.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "Set value:\n" +
                                    "Set valued at $264 AUD/$291 NZD\n" +
                                    "\n" +
                                    "The MECCA view:\n" +
                                    "A complete skincare routine featuring Sunday Riley favourites. Designed to be used in the morning, this set includes cleanser, essence, treatment serums and eye cream. It's the perfect skincare discovery set and gift for a loved one or for yourself. \n" +
                                    "\n" +
                                    "Includes:\n" +
                                    "Ceramic Slip Cleanser 30 ml\n" +
                                    "Pink Drink Essence 25 ml\n" +
                                    "C.E.O 15% Vitamin C Brightening Serum 15ml\n" +
                                    "C.E.O. Glow Vitamin C + Turmeric Oil 10ml\n" +
                                    "Good Genes Lactic Acid Treatment 15ml\n" +
                                    "Auto Correct Brightening and De-Puffing Eye Contour Cream 8ml\n" +
                                    "\n" +
                                    "Key ingredients:\n" +
                                    "Ceramic Slip Cleanser\n" +
                                    "Green Clay, bentonite and white kaolin: cleans pores without stripping the skin of its essential moisture.\n" +
                                    "Rice and olive oil esters: balances and softens sensitive skin for a healthier looking complexion.\n" +
                                    "\n" +
                                    "Pink Drink Essence\n" +
                                    "Ceramides: helps skin's natural moisture barrier.\n" +
                                    "Pink yeast filtrate: rich in minerals, amino acids, beta-glucan and vitamins that soothe and moisturise.\n" +
                                    "\n" +
                                    "C.E.O 15% Vitamin C Brightening Serum\n" +
                                    "Phytosterols complex: derived from soybeans helps soothe and reduce inflammation and sensitivity while encouraging the production of collagen.\n" +
                                    "Glycolic acid: gently lifts away dulling dead skin cells and boosts cellular turnover.\n" +
                                    "Hyaluronic acid: plumps skin and hold 1000 times its own weight in water for smoother, softer and healthier-looking skin.\n" +
                                    "\n" +
                                    "C.E.O. Glow Vitamin C + Turmeric Oil\n" +
                                    "Vitamin C: antioxidant protection, brightens and stimulates collagen production\n" +
                                    "Turmeric: delivers anti-inflammatory and antibacterial properties.\n" +
                                    "Red raspberry: moisturises the skin and improves skin elasticity.\n" +
                                    "Cranberry: rich in antioxidants and slows the appearance of premature ageing.\n" +
                                    "\n" +
                                    "Good Genes Lactic Acid Treatment:\n" +
                                    "Purified lactic acid: refines and clarifies the skin by gently buffing away dead skin cells off the surface layer.\n" +
                                    "Liquorice: naturally brightens age spots, discolouration and adds radiance.\n" +
                                    "Arnica: soothes the skin and boosts circulation to remove toxin build up within the skin.\n" +
                                    "Auto Correct Brightening and De-Puffing Eye Contour Cream\n" +
                                    "Caffeine: reduces the appearance of dark circles and puffiness for a more refined, brighter-looking eye area.\n" +
                                    "Brazilian ginseng root extract: boosts circulated to diminish the appearance of eye puffiness.\n" +
                                    "Horse chestnut extract: provides a lifted look to the eye area, smoothing the appearance of crow’s feet.",
                            "1. Cleanse skin with Ceramic Slip Clay Cleanser.\n" +
                                    "2. After patting your skin dry, mist Pink Drink Firming + Resurfacing Essence onto your face. Massage into your skin and let dry.\n" +
                                    "3. Next apply a few drops of C.E.O. Glow Oil to your face and neck, followed by 2 pumps of C.E.O. Vitamin C Serum (or mix them together and apply as a skin cocktail).\n" +
                                    "4. Follow with 2 pumps of Good Genes to face and neck.\n" +
                                    "5. Pat Auto Correct Brightening + Depuffing Eye Cream around the eye area, starting under the eye and continue to the outer corners (in the crow’s feet area). Then apply under the brow bone, for a lifting effect.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "The MECCA view:\n" +
                                    "Care for your locks and reap the rewards of Gisou’s signature fragrance—a concoction of Mirsalehi Honey and floral notes—when you reach for this hair perfume. This delicately scented spritz is formulated to refresh, repair and nourish dry or damaged lengths while leaving a delicious trail of honey behind.\n" +
                                    " \n" +
                                    "Key ingredients:\n" +
                                    "Mirsalehi Honey: rich in vitamins, minerals, amino acids and antioxidants, honey deeply nourishes and moisturises to repair and restore dry, damaged locks. As a natural humectant, honey helps maintain the hair's natural moisture balance for strong, healthy and shiny hair.\n" +
                                    " \n" +
                                    "Made without:\n" +
                                    "Silicones, sulfates, parabens, phthalates and animal testing.\n" +
                                    " ",
                            "Divide hair into sections. Spray the Honey Infused Hair Perfume evenly from an arm’s length throughout dry hair. Give your hair a flip to emit the fresh Gisou scent.\n" +
                                    "\n" +
                                    "CAUTION\n" +
                                    "FLAMMABLE. KEEP AWAY  FROM FLAME OR HEAT.  FOR EXTERNAL USE ONLY.  AVOID CONTACT WITH  EYES. IN CASE OF CONTACT WITH EYES RINSE IMMEDIATELY WITH PLENTY OF WATER. DISCONTINUE USE IF IRRITATION OCCURS. KEEP OUT OF REACH OF CHILDREN.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "The MECCA View:\n" +
                                    "Harnessing the centuries-old Japanese practice of using rice as an essential ingredient in daily diet and beauty rituals (resulting in clear, bright and dewy skin), this pillow-soft cream cleanser helps to soften, smooth and hydrate the face while clearing away impurities. Like many of Tatcha's bestsellers, the cream cleanser is formulated with Hadasei-3, a fermented antioxidant-rich essence of Japanese superfoods, which floods the skin with moisture, replenishing the skin barrier for a softer, smoother complexion.\n" +
                                    "\n" +
                                    "Key ingredients:\n" +
                                    "Japanese algae and hyaluronic acid blend: helps restore a healthy, protective skin barrier to retain essential moisture.\n" +
                                    "Rice powder: revives the complexion for a natural glow.\n" +
                                    "Hadasei-3: a twice-fermented blend of akita rice, kyoto green tea and okinawa algae to flood skin with moisture and reveal youthful, healthy-looking skin.\n" +
                                    "\n" +
                                    "Made without:\n" +
                                    "Parabens, synthetic fragrances, mineral oil, sulfates, phthalates, urea, DEA and TEA.",
                            "Begin with wet hands and wet face. Squeeze a small amount into palms and rub together to create a creamy foam.  Gently massage the face avoiding the eye area and rinse thoroughly. Can be used daily.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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
                            "The MECCA view:\n" +
                                    "Armfuls of pink peonies blooming in a bottle. A candle infused to fill the room with the scent of just-bloomed peonies, with a splash of crisp Sicilian lemon, fresh guava, and pink cotton candy.\n" +
                                    "\n" +
                                    "Fragrance notes:\n" +
                                    "Patchouli, black pepper and Sicilian lemon.\n" +
                                    "\n" +
                                    "Made without:\n" +
                                    "Animal products, parabens, sulphates, silicones and phthalates.",
                            "Burn for no more than four hours at a time. Keep wick trimmed. Up to 40 hours burn time.",
                            "link",
                            Arrays.asList("Ricinus communis (castor) seed oil, pentaerythrityl tetraisostearate, caprylic/capric triglyceride, copernicia cerifera (carnauba) wax, oryza sativa (rice) bran wax, hydrogenated vegetable oil, synthetic fluorphlogopite, silica, isononyl isononanoate, lauroyl lysine, euphorbia cerifera (candelilla) wax, squalane, sodium hyaluronate, ceramide ng, rosmarinus officinalis (rosemary) leaf extract, helianthus annuus (sunflower) seed oil, tocopherol, caprylyl glycol, hydrogenated castor oil, sorbitan tristearate, 1,2-hexanediol, tropolone, aluminum hydroxide, tin oxide, [+/-: mica, titanium dioxide (ci 77891), iron oxides (ci 77491, ci 77492), red 7 lake (ci 15850), red 6 (ci 15850), red 28 lake (ci 45410)]".split(", ")),
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

    private static List<IBenefit> placeholderBenefits = new ArrayList<>(
            Arrays.asList(
                    new PlaceholderBenefit("0", "0","Cruelty-free", "https://res.cloudinary.com/mecca/image/upload/v1584937486/pdp/facets/CRUELTY-FREE_iewjcx.svg"),
                    new PlaceholderBenefit("0", "0","Vegan", "https://res.cloudinary.com/mecca/image/upload/v1584937487/pdp/facets/VEGAN_odiypj.svg"),
                    new PlaceholderBenefit("0", "0","Matte finish", "https://res.cloudinary.com/mecca/image/upload/v1584937497/pdp/facets/MATTE_FINISH_tt9bg9.svg"),
                    new PlaceholderBenefit("0", "0","Natural finish", "https://res.cloudinary.com/mecca/image/upload/v1584937477/pdp/facets/NATURAL_FINISH_mhz5sd.svg"),
                    new PlaceholderBenefit("0", "0","Sheer coverage", "\thttps://res.cloudinary.com/mecca/image/upload/v1593997623/pdp/facets/SHEER_COVERAGE_new_zkhnca.svg")
                    )
    );



}
