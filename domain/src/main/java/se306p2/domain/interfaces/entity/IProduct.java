package se306p2.domain.interfaces.entity;

import java.math.BigDecimal;
import java.util.List;

public interface IProduct {


    enum Form {
        BALM,
        CREAM,
        FOAM,
        GEL,
        LIQUID,
        LOTION,
        MOUSSE,
        OIL,
        SCRUB
    }

    String getProductID();
    String getName();
    String getSlogan();
    String getDetails();
    String getUsage();
    List<String> getIngredients();
    String getLink();
    Form getForm();
    String getCategoryID();
    String getBrandID();
    String getBrandName();
    BigDecimal getPrice();
    Double getNumericRating();
    Number getNumReviews();
    String getDefaultImageURI();

}
