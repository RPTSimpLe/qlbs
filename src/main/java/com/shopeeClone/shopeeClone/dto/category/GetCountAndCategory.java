package com.shopeeClone.shopeeClone.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class GetCountAndCategory {
    private List<CategoryDTO> dtos = new ArrayList<>();
    private List<Integer> count = new ArrayList<>();

    public GetCountAndCategory(List<CategoryDTO> dtos, List<Integer> count) {
        this.dtos = dtos;
        this.count = count;
    }
}
