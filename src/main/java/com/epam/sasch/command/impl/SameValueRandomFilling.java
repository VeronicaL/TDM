package com.epam.sasch.command.impl;

import com.epam.sasch.command.Command;
import com.epam.sasch.impl.SameValueRandomFillingTransformation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SameValueRandomFilling implements Command {
    @Override
    public void process(String[] args) {
        if (args.length <5) {
            System.out.println("Parameters: null \"full_path_to_file\" \"delimiter\" [{<changeColumnIndex> <percent to make null>} - repeat n times if n columns needed] ");
            throw new IllegalArgumentException("The arguments list is wrong see above");
        }
        String filePath = args[1];
        String delimiter = args[2];
        final List<String> collect = Arrays.stream(args).skip(3).collect(Collectors.toList());
        new Worker().process(filePath,
                new SameValueRandomFillingTransformation(
                        delimiter
                        , collect
                        , "NULL"));
    }
}
