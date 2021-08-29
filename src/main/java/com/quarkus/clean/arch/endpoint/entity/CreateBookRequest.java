package com.quarkus.clean.arch.endpoint.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class CreateBookRequest implements Serializable {
    @NotBlank(message = "Field title is mandatory")
    private String title;
    @NotBlank(message = "Field author is mandatory")
    private String author;
    @Min(value = 1, message = "Field pages is mandatory and must be greater than 0")
    private int pages;
}