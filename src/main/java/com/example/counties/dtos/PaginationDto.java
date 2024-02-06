package com.example.counties.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public record PaginationDto(int currentPage, int totalPages, int pageSize, int totalElements, Collection<? extends Serializable> elements)  implements  Serializable{
    public PaginationDto(int currentPage, int totalPages, int pageSize, int totalElements, Collection<? extends Serializable> elements) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.elements = new ArrayList<>(elements);
    }

    public Collection<? extends Serializable> elements() {
        return new ArrayList<>(elements);
    }
}
