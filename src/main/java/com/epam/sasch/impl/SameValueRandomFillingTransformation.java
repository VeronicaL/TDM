package com.epam.sasch.impl;

import com.epam.sasch.Transformer;
import com.epam.sasch.util.Spliterator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SameValueRandomFillingTransformation implements Transformer {
    private String delimiter;
    private List<String> indexesPercents;
    private String value;
    private Map<Integer, Double> percentages = new HashMap<>();

    public SameValueRandomFillingTransformation(String delimiter, List<String> indexesPercents, String value) {
        this.delimiter = delimiter;
        this.indexesPercents = indexesPercents;
        this.value = value;
        init();
    }

    private void init() {
        for (int i = 0; i < indexesPercents.size(); i += 2) {
            int index = Integer.valueOf(indexesPercents.get(i));
            double percent = Double.valueOf(indexesPercents.get(i + 1));
            percentages.putIfAbsent(index, percent);
        }
    }

    @Override
    public List<String> transform(List<String> rows) {
        Spliterator spliterator = new Spliterator(delimiter);
        final List<List<String>> split = spliterator.split(rows);
        List<String> result = new LinkedList<>();
        for (List<String> lst: split) {
            for (Integer index : percentages.keySet()) {
                if (Math.random() * 100 < percentages.get(index)) {
                    lst.set(index, value);
                }
            }
            result.add(String.join(delimiter, lst));
        }
        return result;
    }
}
