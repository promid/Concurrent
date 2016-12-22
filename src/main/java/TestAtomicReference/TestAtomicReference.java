package TestAtomicReference;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by DBQ on 2016/12/20.
 */
public class TestAtomicReference {

    @Test
    public void test1(){
        String initialReference = "the initially referenced string";
        AtomicReference<String> atomicReference = new AtomicReference<>(initialReference);
    }
}
