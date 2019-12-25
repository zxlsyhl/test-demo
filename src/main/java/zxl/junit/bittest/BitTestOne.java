package zxl.junit.bittest;

import org.junit.Test;

public class BitTestOne {
    /**
     * 位运算符
     */
    @Test
    public void test1(){
        byte a = 60;
        byte b = 10;
        System.out.println(a&b);
        System.out.println(a<<4);
        System.out.println(2^3);
        System.out.println(Math.pow(2,3));
        System.out.println( "A>>2=" + (a>>32) );
        System.out.println( "A>>>2=" + (a>>>32) );

    }

    /**
     * 十进制的int和long转换为2进制，8进制，16进制。
     *
     */
    @Test
    public void test2(){
        int a = 16;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toHexString(a));
        System.out.println(Integer.toOctalString(a));
        int b = 18;
        System.out.println(Long.toBinaryString(b));
        System.out.println(Long.toHexString(b));

    }

    /**
     * 字符串转为十进制数据，第二个参数可指定进制数。
     */
    @Test
    public void test3(){
        String c = "110101";
        System.out.println(Integer.parseInt(c,2));
        System.out.println(Integer.parseInt(c,3));
        System.out.println(Integer.parseInt(c,8));
        System.out.println(Integer.parseInt(c,16));
        System.out.println(Integer.parseInt(c,10));
    }

    @Test
    public void test4(){
        int a = 0xAD;
        System.out.println(a);

        int b = 077;
        System.out.println(b);

        int c = 0b1001;
        System.out.println(c);

        byte d = -0b1001;
        System.out.println(d);
    }

    @Test
    public void test5(){
        String c = "1111111";
        int a = Integer.parseInt(c,2);
        System.out.println(a);
        int b = a>>2;
        System.out.println("b:"+b+";转换为二进制："+Integer.toBinaryString(b));
        int d = a>>5;
        System.out.println("d:"+d+";转换为二进制："+Integer.toBinaryString(d));
    }

    @Test
    public void test6(){
        String a = "1111111";
        System.out.println(Byte.parseByte(a,2));

        System.out.println(Math.pow(2,8));
    }

    @Test
    public void test7(){
        System.out.println(0x2168);
    }

    @Test
    public void test8(){
        String a = "张";
        char b = a.charAt(0);
        byte c = a.getBytes()[0];
        System.out.println(b);
        System.out.println(c);
        char[] d = a.toCharArray();
        System.out.println("print d:");
        for (char dd:d
             ) {
            System.out.println(dd);
        }
        byte[] e = a.getBytes();
        System.out.println("print e:");
        for (byte ee: e){
            int eee = ee;
            System.out.println(ee + ";;;;"+ Integer.toBinaryString(eee));
        }
        System.out.println("二进制字符串转十进制：");
        int f = 0b11111111111111111111111111100101;
        System.out.println(f);

    }
}
