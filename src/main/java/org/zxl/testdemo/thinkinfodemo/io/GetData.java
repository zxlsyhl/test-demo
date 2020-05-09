package org.zxl.testdemo.thinkinfodemo.io;

import java.nio.ByteBuffer;

public class GetData {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(10);
        bf.asCharBuffer().put("张三");
        char c;
        while ((c=bf.getChar())!=0){
            System.out.printf(c+ " ");
        }

        Byte[] b = new Byte[]{1,2,'s'};
    }
}
