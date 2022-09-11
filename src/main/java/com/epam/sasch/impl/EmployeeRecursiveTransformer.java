package com.epam.sasch.impl;

import com.epam.sasch.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;

public class EmployeeRecursiveTransformer implements Transformer {

    private final String DELIMITER;
    private final int EMP_INDEX;
    private final int MANAGER_INDEX;
    private final int SUBORDINATES_LESS_THEN;

    public EmployeeRecursiveTransformer(int empIndex, int managerIndex, String delimiter, int subordinatesLessThen) {
        this.EMP_INDEX = empIndex;
        this.MANAGER_INDEX = managerIndex;
        this.DELIMITER = delimiter;
        this.SUBORDINATES_LESS_THEN = subordinatesLessThen;
    }

    @Override
    public List<String> transform(List<String> rows) {
        List<List<String>> copy = new ArrayList<>();
        rows.forEach(row -> copy.add(new ArrayList<>(Arrays.stream(row.split(",").clone()).collect(Collectors.toList()))));
        copy.get(0).set(MANAGER_INDEX, "NULL");
        Queue<List<String>> que = new LinkedList<>();
        que.offer(copy.get(0));
        Random rand = new Random();
        int i = 1;
        while (i < copy.size()) {
            int subordinatesAmount = rand.nextInt(10) + 1;
            String empIndex = que.poll().get(0);
            int j = i;
            while (i < j + subordinatesAmount && i < copy.size()) {
                copy.get(i).set(MANAGER_INDEX, empIndex);
                que.offer(copy.get(i));
                i++;
            }
        }

        return copy.stream()
                .map(lst -> lst.stream().collect(Collectors.joining(DELIMITER)))
                .collect(Collectors.toList());
    }
}
