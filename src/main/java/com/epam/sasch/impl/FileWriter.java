package com.epam.sasch.impl;

import com.epam.sasch.Writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter implements Writer {

    private final String folderPath;
    private String fileName;

    public FileWriter(String folderPath) {
        this.folderPath = folderPath;
    }

    public FileWriter(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
    }

    @Override
    public void write(List<String> rows) throws IOException {
        String outputFileName = getTargetFileName();
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(outputFileName))) {
            rows.forEach(str -> {
                try {
                    writer.write(str);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private String getTargetFileName() {
        File folder = new File(folderPath);
        String outputFolder = folder.getParent() + "\\output";
        File theDir = new File(outputFolder);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        String fName = folder.getName();
        if (fileName != null) {
            fName = fileName;
        }
        String fullFilePathWithName = outputFolder + "\\" + fName + ".txt";
        return fullFilePathWithName;
    }
}
