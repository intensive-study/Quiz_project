package com.example.demo.dto;

import com.example.demo.entity.CategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryNum;
    private String categoryName;

    public CategoryDto(CategoryEntity source){
        copyProperties(source, this);
    }
}
