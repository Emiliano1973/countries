package com.example.counties.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public record ResponseDto(Collection<? extends Serializable> elements)  implements  Serializable {
    public Collection<? extends Serializable> elements() {
        return new ArrayList<>(elements);
    }
}