package com.epam.sasch.impl;

import com.epam.sasch.Reader;
import com.epam.sasch.Transformer;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LookupTransformation implements Transformer {

    private final Reader reader;
    private final int CHANGE_COLUMN_INDEX;
    private final int LOOKUP_COLUMN_INDEX;
    private final String DELIMITER;

    public LookupTransformation(Reader reader, int changeColumnIndex, int lookupColumnIndex, String delimiter) {
        this.reader = reader;
        CHANGE_COLUMN_INDEX = changeColumnIndex;
        LOOKUP_COLUMN_INDEX = lookupColumnIndex;
        DELIMITER = delimiter;
    }

    @Override
    public List<String> transform(List<String> rows) {
        final List<List<String>> lookup;
        try {
            lookup = reader.read().stream()
                    .map(str -> Arrays.stream(str.split(DELIMITER))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("There were problems during reading lookup table");
        }
        Random rand = new Random();
        int randomRange = lookup.size();
        List<String> result = new LinkedList<>();
        for (int i = 0; i < rows.size(); i++) {
            String[] tmp = rows.get(i).split(DELIMITER);
            int randomProductIndex = rand.nextInt(randomRange);
            tmp[CHANGE_COLUMN_INDEX] = lookup.get(randomProductIndex).get(LOOKUP_COLUMN_INDEX);
            result.add(Arrays.stream(tmp).collect(Collectors.joining(DELIMITER)));
        }
        return result;
    }
}
