package com.application.asseco.service.mapper;

import com.application.asseco.dto.input.InputDTO;
import com.application.asseco.dto.output.OutputDTO;

import java.util.List;

public interface IOEntityMapper<IDTO extends InputDTO, ODTO extends OutputDTO, E> {

    E toEntityForUpdate(Long id, IDTO iDTO);

    E toEntityForCreate(IDTO iDTO);

    ODTO toOutputDto(E entity);

    List<ODTO> toOutputDto(List<E> entityList);

}
