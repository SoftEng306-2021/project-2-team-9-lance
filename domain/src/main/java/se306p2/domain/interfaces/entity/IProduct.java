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
        SCRUB,
        PALETTE
    }

    String getId();
    String getName();
    String getSlogan();
    String getDetails();
    String getUsage();
    String getIngredients();
    String getLink();
    Form getForm();
    String getCategoryId();
    String getBrandId();
    String getBrandName();
    BigDecimal getPrice();
    Double getNumericRating();
    Number getNumReviews();
    String getDefaultImageURI();

}
