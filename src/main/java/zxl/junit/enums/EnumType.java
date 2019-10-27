package zxl.junit.enums;


import org.junit.Test;

/**
 * 枚举类
 *
 * @Author zjl
 * @Date 2018/06/27
 */
public enum EnumType {

    All_TYPE("所有类型", 0),
    TYPE_ONE("类型一", 1),
    TYPE_TOW("类型二", 2);


    private final String typeName;
    private final int typeVal;

    EnumType (String typeName, int typeVal) {
        this.typeName = typeName;
        this.typeVal = typeVal;
    }

    /**
     * 通过 typeVal 的数值获取枚举实例
     *
     * @param val
     * @return
     */
    public static EnumType getEnumType (int val) {
        for (EnumType type : EnumType .values()) {
            if (type.getTypeVal() == val) {
                return type;
            }
        }
        return null;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTypeVal() {
        return typeVal;
    }




    public static void main(String[] args) {
        EnumType enumType = getEnumType(1);
        System.out.println(enumType.getTypeVal()+";"+enumType.getTypeName());
    }
}