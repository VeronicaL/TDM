package com.epam.sasch;

import com.epam.sasch.impl.*;
import com.epam.sasch.impl.SameValueRandomFillingTransformation;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Total amount of parameters is " + args.length);
        System.out.println("Parameters are: " + Arrays.toString(args));
        if (args.length == 0) {
            System.out.println("There are no folder to process. Add needed folders(full paths) as parameters to programm.");
            System.out.println("Rerun the program");
            return;
        } else if (args[0].equals("rec") && args.length != 5) {
            System.out.println("Parameters: rec <emp_index> <manager_index> \"delimiter\" \"full_folder_path\"");
            System.out.println("Rerun the program");
            return;
        } else if (args[0].equals("lookup") && args.length != 6) {
            System.out.println("Parameters: lookup <changeColumnIndex> <lookupColumnIndex> \"delimiter\" \"change_column_full_FOLDER_path\" \"lookup_column_FILE_path\"");
            System.out.println("Rerun the program");
            return;
        } else if (args[0].equals("null") && args.length <5) {
            System.out.println("Parameters: null \"full_path_to_file\" \"delimiter\" [{<changeColumnIndex> <percent to make null>} - repeat n times if n columns needed] ");
            System.out.println("Rerun the program");
            return;
        } else if (args[0].equals("null") && args.length == 3) {
            System.out.println("Parameters: delete \"full_path_to_file\" \"percent_of_invalid_data\"");
            System.out.println("Rerun the program");
            return;
        }
        new Main().run(args);
    }

    private void run(String[] args) {
        if (args[0].equals("rec")) {
            try {
                int empIndex = Integer.parseInt(args[1]);
                int managerIndex = Integer.parseInt(args[2]);
                String delimiter = args[3];
                String folderPath = args[4];
                processFolder(folderPath,
                        new EmployeeRecursiveTransformer(empIndex, managerIndex, delimiter, 10));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equals("null")) {
            try {
                String filePath = args[1];
                String delimiter = args[2];
                final List<String> collect = Arrays.stream(args).skip(3).collect(Collectors.toList());
                processFolder(filePath,
                        new SameValueRandomFillingTransformation(
                                delimiter
                                , collect
                                , "NULL"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equals("lookup")){
            try {
                int changeColumnIndex = Integer.parseInt(args[1]);
                int lookupColumnIndex = Integer.parseInt(args[2]);
                String delimiter = args[3];
                String changeFolderPath = args[4];
                String lookupFilePath = args[5];

                processFolder(changeFolderPath,
                        new LookupTransformation(new FileReader(lookupFilePath)
                                , changeColumnIndex
                                , lookupColumnIndex
                                , delimiter));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args[0].equals("delete")) {
            try {
                String changeFilePath  = args[1];
                int percent = Integer.parseInt(args[2]);

                processFolder(changeFilePath,
                        new DeleteRowsTrasformer(percent));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            for (String folder : args) {
                processFolder(folder, new NoActionTransformer());
            }
        }
    }

    private void processFolder(String folder, Transformer transformer) {
        File file = new File(folder);
        if (!file.exists()) {
            System.out.println("The folder: " + "\"" + folder + "\"");
            System.out.println("is not exists.");
            return;
        } else if (file.isFile()) {
            System.out.println("...Processing the file: " + "\"" + folder + "\"");
            Reader reader = new FileReader(folder);
            String outputFileName = file.getName().split("\\.")[0] + "_output";
            Writer writer = new FileWriter(file.getParent(), outputFileName);
            try {
                List<String> read = reader.read();
                writer.write(transformer.transform(read));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("...Processing the folder: " + "\"" + folder + "\"");
            Reader reader = new FolderReader(folder);
            Writer writer = new FileWriter(folder);
            try {
                List<String> read = reader.read();
                writer.write(transformer.transform(read));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
