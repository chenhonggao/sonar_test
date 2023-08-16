package cn.more;


import org.junit.Test;
import org.springframework.util.Assert;

public class CUtilsTest {

    @Test
    public void plus() {
        CUtils.plus(1,2);
    }

    @Test
    public void minus() {
        CUtils.minus(1,2);
    }
}