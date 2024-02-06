package com.example.counties.dtos;

import java.io.Serializable;
import java.util.Collection;

public class PaginatorDtoBuilder {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private int totalElements;
    private Collection<? extends Serializable> elements;

    public PaginatorDtoBuilder setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public PaginatorDtoBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PaginatorDtoBuilder setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PaginatorDtoBuilder setTotalElements(int totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PaginatorDtoBuilder setElements(Collection<? extends Serializable> elements) {
        this.elements = elements;
        return this;
    }

    public PaginationDto createPaginatorDto() {
        return new PaginationDto(currentPage, totalPages, pageSize, totalElements, elements);
    }
}