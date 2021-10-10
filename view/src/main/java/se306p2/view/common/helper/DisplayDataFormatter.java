package se306p2.view.common.helper;

import java.math.BigDecimal;

public class DisplayDataFormatter {

    private DisplayDataFormatter() {};

    /**
     * In the views, the dollars and cents are displayed separately, each as a string.
     * This function converts the BigDecimal price object into an array of 2 strings, which are
     * the dollars and the cents.
     * The function pads the cents accordingly (i.e. if cents is 0, 00 is returned rather than 0)
     * @param dataIn
     * @return
     */
    public static String[] formatPriceData(BigDecimal dataIn) {

        int priceInCents = dataIn.movePointRight(2).intValue();

        String dollar = Integer.toString(priceInCents/100);
        String cent = Integer.toString(priceInCents%100);
        if (cent.length() < 2) {
            cent += "0";
        }

        return new String[]{dollar, cent};
    }
}
