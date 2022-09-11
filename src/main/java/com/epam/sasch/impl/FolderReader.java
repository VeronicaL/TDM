package com.epam.sasch.impl;

import com.epam.sasch.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FolderReader implements Reader {
    private final String folderPath;

    public FolderReader(String folderPath) {
        this.folderPath = folderPath;
    }

    private List<String> readFilePaths() {
        File f = new File(folderPath);
        String[] pathnames = f.list();

        return Arrays.stream(pathnames).map(name -> folderPath + "\\" + name).collect(Collectors.toList());
    }

    @Override
    public List<String> read() {
        List<String> filePaths = readFilePaths();
        List<String> result = new ArrayList<>();
        for (String filePath : filePaths) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
