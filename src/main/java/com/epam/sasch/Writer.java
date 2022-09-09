package com.epam.sasch;

import java.io.IOException;
import java.util.List;

public interface Writer {
    void write(List<String> rows) throws IOException;
}
