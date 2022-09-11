package com.epam.sasch.command.impl;

import com.epam.sasch.command.Command;
import com.epam.sasch.impl.EmployeeRecursiveTransformer;

public class RecursiveRelationshipCommand implements Command {

    @Override
    public void process(String[] args) {
        if (args[0].equals("rec") && args.length != 5) {
            System.out.println("Parameters: rec <emp_index> <manager_index> \"delimiter\" \"full_folder_path\"");
            throw new IllegalArgumentException("The arguments list is wrong see above");
        }
        int empIndex = Integer.parseInt(args[1]);
        int managerIndex = Integer.parseInt(args[2]);
        String delimiter = args[3];
        String folderPath = args[4];
        new Worker().process(folderPath,
                new EmployeeRecursiveTransformer(empIndex, managerIndex, delimiter, 10));
    }
}
