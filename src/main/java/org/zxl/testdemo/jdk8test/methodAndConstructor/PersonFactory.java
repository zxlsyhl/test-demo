package org.zxl.testdemo.jdk8test.methodAndConstructor;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
