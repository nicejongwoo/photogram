package com.example.photogram.util;

public class Script {

    public static String back(String message) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<script>");
        stringBuffer.append("alert('"+message+"');");
        stringBuffer.append("history.back();");
        stringBuffer.append("</script>");
        return stringBuffer.toString();
    }

}
