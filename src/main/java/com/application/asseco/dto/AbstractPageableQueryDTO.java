package com.application.asseco.dto;

import com.application.asseco.dto.input.InputDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public abstract class AbstractPageableQueryDTO<CRITERIA> implements InputDTO {

    @NotNull
    private CRITERIA criteria;

    @NotNull
    private PageableDTO pageableDTO;
}
