package com.epam.sasch.impl;

import com.epam.sasch.Transformer;

import java.util.List;

public class NoActionTransformer implements Transformer {
    @Override
    public List<String> transform(List<String> rows) {
        return rows;
    }
}
