package org.zxl.testdemo.jdk8test.methodAndConstructor;

import org.junit.Test;

/**
 * 使用构造函数引用来将对象工厂接口实现
 */
public class ConstructorTest {


    @Test
    public void test(){
        PersonFactory<Person> personPersonFactory = Person::new;
        Person person = personPersonFactory.create("Peter", "Parker");
        System.out.println(person.firstName+";;"+person.lastName);

    }
}
