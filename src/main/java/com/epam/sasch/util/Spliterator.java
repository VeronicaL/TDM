package com.epam.sasch.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Spliterator {
    private String delimiter;

    public Spliterator(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<List<String>> split(List<String> rows) {
        return rows.stream()
                .map(str-> Arrays.stream(str.split(delimiter)).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
