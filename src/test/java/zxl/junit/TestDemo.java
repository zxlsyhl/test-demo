package zxl.junit;

import org.junit.*;

/**
 * Created by 123456 on 2019/8/11.
 */
public class TestDemo {
    @BeforeClass
    public static void beforeClass(){

    }
    @AfterClass
    public static void afterClass(){}


    @Before
    public void setup(){

    }

    @After
    public void teardown(){

    }

//    @Test
    @Test(expected = Exception.class)
    public void testMethodName(){
        System.out.println("1231231");
    }

    @Test
    public void testBB(){
        System.out.println("3232");
    }




}
