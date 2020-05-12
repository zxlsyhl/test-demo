package org.zxl.testdemo.jdk8test.interfaceDefaultMethod;

public interface Tree {
    int yezi();
    default int bb(int b){
        return b+1;
    }
}
