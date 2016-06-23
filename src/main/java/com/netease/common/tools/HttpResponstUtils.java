package com.netease.common.tools;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

public class HttpResponstUtils {

    public static void writeResponse(HttpServletResponse response,String contents)  {
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(contents);
        } catch (Exception e) {
            
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
