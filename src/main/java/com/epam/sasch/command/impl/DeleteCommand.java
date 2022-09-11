package com.epam.sasch.command.impl;

import com.epam.sasch.command.Command;
import com.epam.sasch.impl.DeleteRowsTrasformer;

public class DeleteCommand implements Command {

    @Override
    public void process(String[] args) {
        if (args[0].equals("delete") && args.length != 3) {
            System.out.println("Parameters: delete \"full_path_to_file\" \"percent_of_invalid_data\"");
            throw new IllegalArgumentException("The arguments list is wrong see above");
        }
        String changeFilePath  = args[1];
        int percent = Integer.parseInt(args[2]);

        new Worker().process(changeFilePath, new DeleteRowsTrasformer(percent));
    }
}
