package zxl.junit.atomicclass;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class TestOne {

    @Test
    public void test1(){
        AtomicLongArray atomicLongArray = new AtomicLongArray(2);
        atomicLongArray.get(0);
        AtomicReferenceArray<AtomicLongArray> array = new AtomicReferenceArray<>(2);
    }
}
