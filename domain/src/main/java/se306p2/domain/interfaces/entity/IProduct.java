package se306p2.domain.interfaces.entity;

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

}
