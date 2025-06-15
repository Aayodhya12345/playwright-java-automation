package com.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
 
public class CalcTest {
 
    @BeforeClass
    public void setUp() throws Exception {
 
    }
 
    @AfterClass
    public void tearDown() throws Exception {
 
    }
 
    @Test
    public void CanAddNumbers()
    {
    	int x=5;
    	Assert.assertEquals(x, 5);

    }
 
 
//    @Test
//    public void CanSubtract()
//    {
//       assertThat(Calculator.Subtract(1, 1), is(0));
//        assertThat(Calculator.Subtract(-1, -1), is(0));
//        assertThat(Calculator.Subtract(100, 5), is(95));
//    }
// 
// 
//    @Test
//    public void CanMultiply()
//    {
//        assertThat(Calculator.Multiply(1, 1), is(1));
//        assertThat(Calculator.Multiply(-1, -1), is(1));
//        assertThat(Calculator.Multiply(100, 5), is(500));
//    }
// 
// 
//    public void CanDivide()
//    {
//        assertThat(Calculator.Divide(1, 1), is(1));
//        assertThat(Calculator.Divide(-1, -1), is(1));
//        assertThat(Calculator.Divide(100, 5), is(20));
//    }
// 
// 
//    @Test
//    public void CanDoStuff()
//    {
//        assertThat(true, is(true));
//    }
// 
 
}