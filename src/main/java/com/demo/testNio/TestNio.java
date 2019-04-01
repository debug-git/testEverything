package com.demo.testNio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestNio {

    public static void main(String[] args) {
        InputStream in = null;
        byte[] resultByte = null;
        try{
            in = new BufferedInputStream(new FileInputStream("F:/workSpace/11月8日.txt"));
            byte[] buf = new byte[1024];
            //read()方法只读取一个字节,int read(byte b[], int off, int len) 方法读取len个字节,并放到b[]中,返回实际读取的字节数
            int bytesRead  = in.read(buf);
            while(bytesRead != -1){
                for(int i=0; i<bytesRead; i++) {
                    System.out.print((char)buf[i]);
                }
                bytesRead = in.read(buf);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (in != null){
                    in.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }




}
