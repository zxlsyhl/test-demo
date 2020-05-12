六、访问局部变量

我们可以直接在lambda表达式中访问外层的局部变量：

final int num = 1;
Converter<Integer, String> stringConverter =
        (from) -> String.valueOf(from + num);

stringConverter.convert(2);     // 3
但是和匿名对象不同的是，这里的变量num可以不用声明为final，该代码同样正确：

int num = 1;
Converter<Integer, String> stringConverter =
        (from) -> String.valueOf(from + num);

stringConverter.convert(2);     // 3
不过这里的num必须不可被后面的代码修改（即隐性的具有final的语义），例如下面的就无法编译：

int num = 1;
Converter<Integer, String> stringConverter =
        (from) -> String.valueOf(from + num);
num = 3;
在lambda表达式中试图修改num同样是不允许的。
