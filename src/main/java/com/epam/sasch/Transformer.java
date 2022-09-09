package com.epam.sasch;

import java.util.List;

public interface Transformer {
    List<String> transform(List<String> rows);
}
