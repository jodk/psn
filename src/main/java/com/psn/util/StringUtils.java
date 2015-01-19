package com.psn.util;

/**
 * Created by zhangdekun on 15-1-19-上午11:40.
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        boolean isEmpty = false;
        if(str == null){
            isEmpty = true;
        }else {
            if(str.trim().length()==0){
                isEmpty = true;
            }
        }
        return isEmpty;
    }
}
