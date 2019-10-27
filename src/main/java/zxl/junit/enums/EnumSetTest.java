package zxl.junit.enums;

import java.util.EnumSet;

public class EnumSetTest {
    public static void main(String[] args) {
        System.out.println("enumSet");
        EnumSet<TestEnum> enumSet = EnumSet.allOf(TestEnum.class);
        System.out.println(enumSet);
        System.out.println(enumSet.size());

        System.out.println("enumSet1");
        EnumSet<TestEnum> enumSet1 = EnumSet.noneOf(TestEnum.class);
        System.out.println(enumSet1);
        enumSet1.add(TestEnum.BB);
        System.out.println(enumSet1);

        System.out.println("enumSet2");
        EnumSet<TestEnum> enumSet2 = EnumSet.of(TestEnum.BB,TestEnum.CC,TestEnum.DD);
        System.out.println(enumSet2);

        System.out.println("enumSet3");
        EnumSet<TestEnum> enumSet3 = EnumSet.range(TestEnum.CC,TestEnum.EE);
        System.out.println(enumSet3);

        //6.创建一个其元素类型与指定EnumSet里元素类型相同的EnumSet集合，
        //  新EnumSet集合包含原EnumSet集合所不包含的枚举值
        System.out.println("enumSet4");
        EnumSet enumSet4 = EnumSet.complementOf(enumSet3);
        System.out.println(enumSet4);//[WINTER]

    }
}
