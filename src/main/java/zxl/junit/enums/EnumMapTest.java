package zxl.junit.enums;

import java.util.EnumMap;

public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap enumMap = new EnumMap(TestEnum.class);
        enumMap.put(TestEnum.BB,"bbbbbb");
        enumMap.put(TestEnum.BB,"ccc");
        enumMap.put(TestEnum.CC,"ccc");
        enumMap.put(TestEnum.DD,"ccc");
        System.out.println(enumMap);
    }
}
