package com.epam.sasch.impl;

import com.epam.sasch.Reader;
import com.epam.sasch.Transformer;

import java.util.ArrayList;
import java.util.List;

public class DeleteRowsTrasformer implements Transformer {

    private int percent;

    public DeleteRowsTrasformer( int percent) {
        this.percent = percent;
    }

    @Override
    public List<String> transform(List<String> rows) {
        List<String> result = new ArrayList<>();
        System.out.println("Rows amount: " + rows.size());
        int size = rows.size();
        //defining required to cut amount of rows
        int amountToDelete = size * percent / 100;
        System.out.println("amountToDelete: "+ amountToDelete);
        int i = 0;
        while (i < size - amountToDelete) {
            result.add(rows.get(i));
            i++;
        }

        return result;
    }
}
