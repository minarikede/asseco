package com.application.asseco.service;

import com.application.asseco.dto.input.InputDTO;
import com.application.asseco.dto.output.OutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<ODTO extends OutputDTO, IDTO extends InputDTO, CRITERIA> {

    ODTO create(IDTO idto);

    ODTO update(Long id, IDTO idto);

    ODTO getOne(Long id);

    Page<ODTO> findAll(CRITERIA criteria, Pageable pageable);

    Void delete(Long id);
}
