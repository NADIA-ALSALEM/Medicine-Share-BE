package com.medicine.donate.medicine.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedResponse<T> {
    private int page;
    private int pageSize;
    private int total;
    private List<T> data;
}
