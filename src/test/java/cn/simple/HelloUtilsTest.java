package cn.simple;


import org.junit.Test;
import org.springframework.util.Assert;

public class HelloUtilsTest {



    @Test
    public void plus() {
        Assert.isTrue(HelloUtils.plus(1,2) == 3);
    }

    @Test
    public void minus() {
        Assert.isTrue(HelloUtils.minus(1,2) == -1);
    }
}