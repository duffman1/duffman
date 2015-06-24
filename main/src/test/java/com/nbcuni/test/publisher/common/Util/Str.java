package com.nbcuni.test.publisher.common.Util;

/**
 * Created by kiryl_zayets on 6/24/15.
 */
public class Str {

    public static String getImageNameFromPath(String path){
        return path.split("/")[1].split("\\.")[0];
    }
}
