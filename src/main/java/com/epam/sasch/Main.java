package com.epam.sasch;

import com.epam.sasch.command.Command;
import com.epam.sasch.command.CommandEnum;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Total amount of parameters is " + args.length);
        System.out.println("Parameters are: " + Arrays.toString(args));
        if (args.length == 0) {
            System.out.println("There are no folder to process. Add needed folders(full paths) as parameters to programm.");
            System.out.println("Rerun the program");
            return;
        }
        final CommandEnum commandEnum = CommandEnum.valueOf(args[0].toUpperCase());
        try {
            final Command command = commandEnum.getCommand();
            command.process(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Rerun the program");
            e.printStackTrace();
        }
    }
}
