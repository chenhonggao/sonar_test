package cn.more;


import org.junit.Test;
import org.springframework.util.Assert;

public class BUtilsTest {

    @Test
    public void plus() {
        BUtils.plus(1,2);
    }

    @Test
    public void minus() {
        BUtils.minus(1,2);
    }
}