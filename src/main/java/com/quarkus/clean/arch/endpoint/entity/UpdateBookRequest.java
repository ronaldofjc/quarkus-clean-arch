package com.quarkus.clean.arch.endpoint.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class UpdateBookRequest implements Serializable {
    @NotNull(message = "Field id is mandatory")
    private Long id;
    @NotBlank(message = "Field title is mandatory")
    private String title;
    @NotBlank(message = "Field author is mandatory")
    private String author;
    @Min(message = "Field pages must be greater than 1", value = 1)
    private int pages;
    @AssertTrue(message = "Field active is mandatory and true")
    private boolean active;
}
