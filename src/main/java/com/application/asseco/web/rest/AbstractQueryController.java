package com.application.asseco.web.rest;


import com.application.asseco.dto.input.InputDTO;
import com.application.asseco.dto.output.OutputDTO;
import com.application.asseco.service.CrudService;
import com.application.asseco.web.rest.util.PaginationUtil;
import com.application.asseco.web.rest.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public abstract class AbstractQueryController<ODTO extends OutputDTO, IDTO extends InputDTO, CRITERIA>
        extends AbstractResource {

    protected final String entityUrl;

    protected final CrudService<ODTO, IDTO, CRITERIA> crudService;

    protected AbstractQueryController(CrudService<ODTO, IDTO, CRITERIA> crudService,
                                      String entityUrl) {
        this.entityUrl = entityUrl;
        this.crudService = crudService;
    }

    public ResponseEntity<List<ODTO>> getAll(CRITERIA criteria, Pageable pageable) {
        final Page<ODTO> page = crudService.findAll(criteria, pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(page, entityUrl);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    public ResponseEntity<ODTO> get(Long id) {
        final ODTO result = crudService.getOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }
}
