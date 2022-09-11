package com.epam.sasch.command.impl;

import com.epam.sasch.command.Command;
import com.epam.sasch.impl.FileReader;
import com.epam.sasch.impl.LookupTransformation;

public class LookupValueCommand implements Command {
    @Override
    public void process(String[] args) {
        if (args[0].equals("lookup") && args.length != 6) {
            System.out.println("Parameters: lookup <changeColumnIndex> <lookupColumnIndex> \"delimiter\" \"change_column_full_FOLDER_path\" \"lookup_column_FILE_path\"");
            throw new IllegalArgumentException("The arguments list is wrong see above");
        }
        int changeColumnIndex = Integer.parseInt(args[1]);
        int lookupColumnIndex = Integer.parseInt(args[2]);
        String delimiter = args[3];
        String changeFolderPath = args[4];
        String lookupFilePath = args[5];

        new Worker().process(changeFolderPath,
                new LookupTransformation(new FileReader(lookupFilePath)
                        , changeColumnIndex
                        , lookupColumnIndex
                        , delimiter));
    }
}

