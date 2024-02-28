package com.example.counties.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public record ResponseDto(int totalElements ,Collection<? extends Serializable> elements)  implements  Serializable {
    public ResponseDto(int totalElements ,Collection<? extends Serializable> elements) {
        this.totalElements = totalElements;
        this.elements =  (elements ==null)? Collections.emptyList(): new ArrayList<>(elements);
    }

    public Collection<? extends Serializable> elements() {
        return new ArrayList<>(elements);
    }
}
