package com.epam.sasch.impl;

import com.epam.sasch.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader {
    private String filePath;

    public FileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> read() throws IOException {
        File file = new File(filePath);
        if (!file.exists()){
            throw new RuntimeException("There is no file to read. The path is: " + file);
        }

        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
