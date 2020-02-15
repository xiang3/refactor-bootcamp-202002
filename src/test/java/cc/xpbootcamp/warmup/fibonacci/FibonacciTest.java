package cc.xpbootcamp.warmup.fibonacci;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciTest {
    @Test
    public void should_return_1_when_calculate_given_position_is_1() {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals(1, fibonacci.get(1));
    }

    @Test
    public void should_return_1_when_calculate_given_position_is_2() {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals(1, fibonacci.get(2));
    }

    @Test
    public void should_return_2_when_calculate_given_position_is_3() {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals(2, fibonacci.get(3));
    }

    @Test
    public void should_return_3_when_calculate_given_position_is_4() {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals(3, fibonacci.get(4));
    }

    @Test
    public void should_return_5_when_calculate_given_position_is_5() {
        Fibonacci fibonacci = new Fibonacci();
        Assert.assertEquals(5, fibonacci.get(5));
    }
}

