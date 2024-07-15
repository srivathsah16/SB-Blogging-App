package com.srivath.blog.app.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(min = 10,message = "Descrition length should be min of 10 ")
    private String description;
}
