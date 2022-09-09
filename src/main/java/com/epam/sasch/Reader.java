package com.epam.sasch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Reader {
    List<String> read() throws IOException;
}
