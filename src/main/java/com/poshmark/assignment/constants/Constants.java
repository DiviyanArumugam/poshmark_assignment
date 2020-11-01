package com.poshmark.assignment.constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static String SERVER_LARGE = "large";
    public static String SERVER_XLARGE = "xlarge";
    public static String SERVER_2XLARGE = "2xlarge";
    public static String SERVER_4XLARGE = "4xlarge";
    public static String SERVER_8XLARGE = "8xlarge";
    public static String SERVER_10XLARGE = "10xlarge";

    public static Map<String, Integer> serverToCpuMap = new HashMap<String, Integer>() {{
        put(SERVER_LARGE, 1);
        put(SERVER_XLARGE, 2);
        put(SERVER_2XLARGE, 4);
        put(SERVER_4XLARGE, 8);
        put(SERVER_8XLARGE, 16);
        put(SERVER_10XLARGE,32);
    }};



}
