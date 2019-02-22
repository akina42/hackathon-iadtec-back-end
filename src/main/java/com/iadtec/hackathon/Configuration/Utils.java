package com.iadtec.hackathon.Configuration;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    public static <X> Type getListType(Class<X> class1) {
        return TypeToken.getParameterized(ArrayList.class, class1).getType();
    }

}
