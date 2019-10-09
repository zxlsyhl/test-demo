package zxl.junit.innerclass;

public class TT {
    private static String a = "sss";

    public static void main(String[] args) {
        System.out.println(TT.a);
        System.out.println(new StaticInnerClassTest.StaticClass().field1);
    }

}
