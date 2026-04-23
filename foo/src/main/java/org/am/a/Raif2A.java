package org.am.a;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Raif2A {

    public static void main(String[] args) {

        Map<String, String> map = List.of("1", "2", "3", "4", "5", "6", "7", "8", "8")
                .stream()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> i,
                        (a, b) -> a
                ));

        System.out.println(map);

    }
}
