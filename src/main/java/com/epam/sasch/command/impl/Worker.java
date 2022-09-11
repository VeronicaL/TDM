package com.epam.sasch.command.impl;

import com.epam.sasch.Reader;
import com.epam.sasch.Transformer;
import com.epam.sasch.Writer;
import com.epam.sasch.impl.FileReader;
import com.epam.sasch.impl.FileWriter;
import com.epam.sasch.impl.FolderReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

class Worker {
    void process(String folder, Transformer transformer) {
        File file = new File(folder);
        Reader reader = null;
        Writer writer = null;
        if (!file.exists()) {
            System.out.println("The folder: " + "\"" + folder + "\"");
            System.out.println("is not exists.");
            return;
        } else if (file.isFile()) {
            System.out.println("...Processing the file: " + "\"" + folder + "\"");
            reader = new FileReader(folder);
            String outputFileName = file.getName().split("\\.")[0] + "_output";
            writer = new FileWriter(file.getParent(), outputFileName);
        } else {
            System.out.println("...Processing the folder: " + "\"" + folder + "\"");
            reader = new FolderReader(folder);
            writer = new FileWriter(folder);
        }

        try {
            List<String> read = reader.read();
            writer.write(transformer.transform(read));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}