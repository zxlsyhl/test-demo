package org.zxl.testdemo.jdk8test.visitObjectOrStaticVariables;

/**
 * 函数式接口，我们可以将lambda表达式当作任意只包含一个抽象方法的接口类型，
 * 确保你的接口一定达到这个要求，你只需要给你的接口添加 @FunctionalInterface 注解
 * @param <F>
 * @param <T>
 */
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);

}
