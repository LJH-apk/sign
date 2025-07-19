package com.example.signin.service;

import java.util.*;
import java.util.stream.Collectors;

public class AllocationToolService {
    public static <K,V> boolean check(Map<K,V> map){
        if(map.isEmpty()){
            return true;
        }
        Collection<V> values = map.values();

        Set<V> uniqueValues = new HashSet<>(values);

        return uniqueValues.size() == 1;
    }
    public static Map<String, Integer> filteredMap(Map<String, Integer> map){
        OptionalInt maxValueOpt = map
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .max();
        int maxValue = maxValueOpt.getAsInt();
        return  map
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() != maxValue)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }
}
