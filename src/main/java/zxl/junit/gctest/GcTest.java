package zxl.junit.gctest;

import org.junit.Test;

public class GcTest {
    @Test
    public void test1(){
        String b = new String("123");
        System.gc();
        System.out.println(b);
    }

    /**
     * finalize() 在对象被垃圾回收之前调用。
     * @throws Throwable
     */
    @Test
    public void test2() throws Throwable{
        Garbage garbage = new Garbage();
        garbage.setId(100);
//        garbage.finalize();
        garbage = null;
        System.gc();
//        System.out.println(garbage.getId());
    }

    class Garbage{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("Garbage id="+id+" is disposed");
        }
    }
}
