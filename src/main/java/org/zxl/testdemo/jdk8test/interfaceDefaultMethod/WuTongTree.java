package org.zxl.testdemo.jdk8test.interfaceDefaultMethod;


public class WuTongTree implements Tree {
    @Override
    public int yezi() {
        return 0;
    }

    public static void main(String[] args) {
        Tree tree = new WuTongTree();
        System.out.println(tree.yezi());
        System.out.println(tree.bb(1));
    }
}
