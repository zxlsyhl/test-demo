package zxl.junit.enums;

import org.junit.Test;

import java.util.EnumSet;

public enum TestEnum {
    BB("测试1",1),CC("测试2",2),DD("测试3",3),EE("测试4",4);
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    TestEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static TestEnum getTestEnum(String name){
        return valueOf(name);
    }
    public static TestEnum getTestEnum (int val) {
        for (TestEnum type : TestEnum .values()) {
            if (type.getId() == val) {
                return type;
            }
        }
        return null;
    }



    public static void main(String[] args) {

//        TestEnum testEnum = getTestEnum("1");
//        System.out.println(testEnum.id);
        TestEnum testEnum1 = getTestEnum(1);
        System.out.println(testEnum1.id);

        System.out.println(TestEnum.BB.getId());


    }
}
