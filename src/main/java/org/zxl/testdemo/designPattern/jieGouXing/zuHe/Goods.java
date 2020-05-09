package org.zxl.testdemo.designPattern.jieGouXing.zuHe;

/**
 * 树叶构件： 商品
 */
public class Goods implements Articles {
    private String name;
    private int quantity;
    private float unitPrice;

    public Goods(String name, int quantity, float unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public float calculation() {
        return quantity*unitPrice;
    }

    @Override
    public void show() {
        System.out.println(name+"(数量："+quantity+",单价："+unitPrice+"元）");
    }
}
