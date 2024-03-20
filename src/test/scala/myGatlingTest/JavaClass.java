package com.myGatlingTest;

import java.util.ArrayList;
import java.util.List;

public class JavaClass {

    public  String encode(String paramsToEncode) {
//        return paramsToEncode;
        return "534354163142,MALE";
    }


    public static  String getStrings() {
        System.out.println("demo");
        return "Working . . .";
    }

    public   List<String> getListData(String paramsToEncode) {
        List<String> data = new ArrayList<>();
        data.add("aadhaar1");
        data.add("aadhaar2");
        data.add("aadhaar3");
        data.add("aadhaar4");
        data.add("aadhaar5");
        return data;
    }

}
