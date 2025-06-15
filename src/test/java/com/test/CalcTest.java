package com.test;

import org.testng.annotations.Test;

import junit.framework.Assert;

 
public class CalcTest {
 
    @Test
    public void CanAddNumbers()
    {
    	int x=5;
    	Assert.assertEquals(x, 5);

    }
 
}