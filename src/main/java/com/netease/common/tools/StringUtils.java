package com.netease.common.tools;


public class StringUtils {
    public static String buildString(String ... stringArg){
        if(stringArg==null){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        for(String s:stringArg){
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        String s[]={};
        System.out.println(buildString(s).getBytes());
    }
}
