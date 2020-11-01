package com.poshmark.assignment;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ResourceAllocatorTest {

    @Test
    public void testResourceAllocator() throws IOException {
        String expectedResult = "[{\"region\":\"asia\",\"total_cost\":\"$4.8\",\"servers\":[\"(\\\"xlarge\\\", 2)\",\"(\\\"xlarge\\\", 2)(\\\"8xlarge\\\", 48)\"]}]";
        String actualResult = new ResourceAllocator().process(24, 50, 152.4f);
        Assert.assertEquals(expectedResult, actualResult);

        expectedResult = "The price limit exceeds and so, unable to allocate servers !!!";
        actualResult = new ResourceAllocator().process(14, 200, 100f);
        Assert.assertEquals(expectedResult, actualResult);

        expectedResult = "[{\"region\":\"us-east\",\"total_cost\":\"$42.0\",\"servers\":[\"(\\\"8xlarge\\\", 16)\"]},{\"region\":\"us-west\",\"total_cost\":\"$89.1\",\"servers\":[\"(\\\"10xlarge\\\", 32)\"]},{\"region\":\"asia\",\"total_cost\":\"$20.1\",\"servers\":[\"(\\\"4xlarge\\\", 8)\"]}]";
        actualResult = new ResourceAllocator().process(30, null, 152.4f);
        Assert.assertEquals(expectedResult, actualResult);

        expectedResult = "[{\"region\":\"asia\",\"total_cost\":\"$4.8\",\"servers\":[\"(\\\"xlarge\\\", 2)\",\"(\\\"xlarge\\\", 2)(\\\"8xlarge\\\", 48)\"]}]";
        actualResult = new ResourceAllocator().process(24, 50, null);
        Assert.assertEquals(expectedResult, actualResult);

        expectedResult = "Invalid input !!!";
        actualResult = new ResourceAllocator().process(8, null, null);
        Assert.assertEquals(expectedResult, actualResult);

    }
}
