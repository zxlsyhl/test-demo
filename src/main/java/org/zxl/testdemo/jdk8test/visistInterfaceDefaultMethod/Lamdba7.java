package org.zxl.testdemo.jdk8test.visistInterfaceDefaultMethod;

import org.junit.Test;
import org.zxl.testdemo.jdk8test.methodAndConstructor.Person;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 */
public class Lamdba7 {
    /**
     * Predicate接口只有一个参数，返回boolean类型。
     */
    @Test
    public void predicateTest(){
        //判断字符串长度是否大于0
        Predicate<String> predicate = s -> s.length()>0;
        predicate.test("foo");
        predicate.negate().test("foo");

        //判断布尔型字段是否为空
        Predicate<Boolean> nonNull = Objects::nonNull;
        nonNull.test(true);

        //判断字符串是否为空
        Predicate<String> isNull = Objects::isNull;
        System.out.println(isNull.test("a"));

        //判断数字是否大于10
        Predicate<Integer> isBiggerTen = num -> num>10;
        System.out.println(isBiggerTen.test(12));

    }

    /**
     * Function 接口有一个参数并且返回一个结果，并附带了一些可以和其他函数组合的默认方法（compose, andThen）：
     */
    @Test
    public void testFunction(){
        Function<String, Integer> boInteger = Integer::valueOf;
        Function<String, String> backToString = boInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));
    }

    /**
     * Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
     */
    @Test
    public void supplierTest(){
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();
    }

    /**
     * Consumer 接口表示执行在单个参数上的操作。
     */
    @Test
    public void consumerTest(){
        Consumer<Person> greeter = person -> System.out.println(person.firstName);
        greeter.accept(new Person("Jack","Jonels"));
    }

    /**
     * Comparator 是老Java中的经典接口， Java 8在此之上添加了多种默认方法：
     */
    @Test
    public void ComparatorTest(){
        Comparator<Person> comparator = (p1,p2) -> p1.lastName.compareTo(p2.lastName);
        Person p1 = new Person("Jonsd", "DDD");
        Person p2 = new Person("Alicl", "Dlsld");
        comparator.compare(p1,p2);
        comparator.reversed().compare(p1,p2);
    }

    /**
     * Optional 不是函数是接口，这是个用来防止NullPointerException异常的辅助类型，
     * 这是下一届中将要用到的重要概念，现在先简单的看看这个接口能干什么：
     *
     * Optional 被定义为一个简单的容器，其值可能是null或者不是null。
     * 在Java 8之前一般某个函数应该返回非空对象但是偶尔却可能返回了null，
     * 而在Java 8中，不推荐你返回null而是返回Optional。
     *
     */
    @Test
    public void OptionalTest(){
        Optional<String> optional = Optional.of("bam");
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("fallback"));

        optional.ifPresent(s -> System.out.println(s.charAt(0)));


    }

    /**
     * java.util.Stream 表示能应用在一组元素上一次执行的操作序列。
     * Stream 操作分为中间操作或者最终操作两种，最终操作返回一特定类型的计算结果，
     * 而中间操作返回Stream本身，这样你就可以将多个操作依次串起来。
     * Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，
     * List或者Set， Map不支持。Stream的操作可以串行执行或者并行执行。
     *
     */
    @Test
    public void StreamTest(){
        List<String> strings = new ArrayList<>();
        strings.add("ddd2");
        strings.add("aaa2");
        strings.add("ddd1");
        strings.add("ddd3");
        strings.add("ddd4");
        strings.add("ddd6");
        strings.add("aaa6");

        //filter过滤
        System.out.println("filter过滤");
        strings.stream().filter(s -> s.startsWith("d")).forEach(System.out::println);

        //sort排序
        System.out.println("sort排序");
        strings.stream().sorted((a,b)-> b.compareTo(a)).forEach(System.out::println);

        //Map映射
        System.out.println("map映射");
        strings.stream().map(String::toUpperCase).forEach(System.out::println);

        strings.stream().map(s ->  { return s.toUpperCase()+"IND";}).forEach(System.out::println);

        //Match匹配
        System.out.println("Match匹配");
        boolean boo1 = strings.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(boo1);

        boolean boo2 = strings.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(boo2);

        boolean boo3 = strings.stream().noneMatch(s -> s.startsWith("a"));
        System.out.println(boo3);

        //Count计数
        System.out.println("Count计数");
        long startWithB = strings.stream().filter(s -> s.startsWith("d")).count();
        System.out.println(startWithB);

        //Reduce规约 这是一个最终操作，允许通过指定的函数来讲stream中的多个元素规约为一个元素，规越后的结果是通过Optional接口表示的：
        System.out.println("Reduce规约");
        Optional<String> reduced = strings.stream().reduce((s1,s2)-> s1+"#"+s2);
        reduced.ifPresent(System.out::println);

        //并行Streams 前面提到过Stream有串行和并行两种，串行Stream上的操作是在一个线程中依次完成，而并行Stream则是在多个线程上同时执行。
        System.out.println("并行Streams");
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        long t2 = System.nanoTime();

        long count2 = values.parallelStream().sorted().count();
        System.out.println(count2);

        long t3 = System.nanoTime();

        long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort took: %d ms", millis2));

    }

    @Test
    public void StreamTest2(){
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("b11","bbb");
        Person p2 = new Person("a11","bbb");
        Person p3 = new Person("e11","bbb");
        Person p4 = new Person("f11","bbb");
        Person p5 = new Person("d11","bbb");
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);

        personList.stream().sorted(Comparator.comparing(Person::getFirstName)).forEach(obj-> System.out.println(obj.firstName));


    }

    /**
     * Map
     *
     * 前面提到过，Map类型不支持stream，不过Map提供了一些新的有用的方法来处理一些日常任务。
     */
    @Test
    public void MapTest(){
        Map<Integer, String> map = new HashMap<>();
        //putIfAbsent 不需要我们做额外的存在性检查，而forEach则接收一个Consumer接口来对map里的每一个键值对进行操作。
        System.out.println("putIfAbsent用法");
        for (int i=0;i<10;i++){
            map.putIfAbsent(i, "val"+i);
        }
        map.forEach((id,val)-> System.out.println(val));

        map.computeIfPresent(3, (num, val)-> val+num);
        System.out.println("map.get(3):"+map.get(3));

        map.computeIfPresent(9, (num ,val)-> null);
        System.out.println("map.get(9):"+map.get(9));

        map.computeIfAbsent(23, num -> "val"+num);
        System.out.println("map.get(23):"+map.get(23));

        map.computeIfAbsent(3, num->"bam");
        System.out.println("map.get(3):"+map.get(3));

        map.remove(3,"val3");
        System.out.println("map.get(3):"+map.get(3));
        map.remove(3,"val33");
        System.out.println("map.get(3):"+map.get(3));

        String val = map.getOrDefault(42,"not found");
        System.out.println("val:"+val);
        val = map.getOrDefault(42,"not found");
        System.out.println("val:"+val);

        //合并 Merge做的事情是如果键名不存在则插入，否则则对原键对应的值做合并操作并重新插入到map中。
        System.out.println("map.get(9):"+map.get(9));
        map.merge(9, "val9",(value, newValue)-> value.concat(newValue));
        System.out.println("map.get(9):"+map.get(9));
        map.merge(9, "val9",(value, newValue)-> value.concat(newValue));
        System.out.println("map.get(9):"+map.get(9));
    }

}
