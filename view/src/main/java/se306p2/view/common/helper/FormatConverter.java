package se306p2.view.common.helper;

import java.util.List;

import se306p2.domain.interfaces.entity.IBrand;

public class FormatConverter {

    public static String[] ConvertBrandsToStringArr(List<IBrand> list){
        String[] arr = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i).getName();
        }

        return arr;
    }
}
