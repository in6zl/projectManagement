package com.nuist.projectManagement.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class FileUtil {

    public static void downloadExcel(String fileName, HttpServletResponse response) {
        response.setHeader("content-type","application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        try (  InputStream is = Thread.currentThread().getClass().getResourceAsStream("/file/"+fileName)){
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int i;
            while((i = is.read(bytes))!=-1){
                outputStream.write(bytes,0,i);
                outputStream.flush();
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }
}
