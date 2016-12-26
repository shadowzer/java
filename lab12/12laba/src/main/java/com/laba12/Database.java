package com.laba12;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    static public Map<String,List<Object>> tables= new HashMap<String, List<Object>>();

    static boolean isExist(String  table) {
        return tables.containsKey(table);
    }
}
