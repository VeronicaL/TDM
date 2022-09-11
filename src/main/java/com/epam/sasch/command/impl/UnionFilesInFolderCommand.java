package com.epam.sasch.command.impl;

import com.epam.sasch.command.Command;
import com.epam.sasch.impl.NoActionTransformer;

import java.util.Arrays;

public class UnionFilesInFolderCommand implements Command {
    @Override
    public void process(String[] args) {
        if (args.length < 2) {
            System.out.println("Parameters: null \"full_path_to_file\" \"delimiter\" [{<changeColumnIndex> <percent to make null>} - repeat n times if n columns needed] ");
            throw new IllegalArgumentException("The arguments list is wrong see above");
        }
        Worker worker = new Worker();
        Arrays.stream(args).skip(1).forEach(file -> worker.process(file, new NoActionTransformer()));
    }
}