package com.rnaomix.itemmanagement.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {

    @NotNull
    @Size(min = 1, max = 50)
    private String catId;

    @NotNull
    @Size(min = 1, max = 400)
    private String itemName;

    @NotNull
    @Min(1)
    @Max(10000000)
    private long price;

}
