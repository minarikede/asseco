package com.application.asseco.web.rest;

import com.application.asseco.dto.input.InputDTO;
import com.application.asseco.dto.output.OutputDTO;
import com.application.asseco.service.CrudService;
import com.application.asseco.web.rest.error.EntityCreationException;
import com.application.asseco.web.rest.util.HeaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public abstract class AbstractCRUDController<ODTO extends OutputDTO, IDTO extends InputDTO, CRITERIA> extends
        AbstractQueryController<ODTO, IDTO, CRITERIA> {


    private String entityName;

    public AbstractCRUDController(CrudService<ODTO, IDTO, CRITERIA> crudService, String entityName,
                                  String entityUrl) {
        super(crudService, entityUrl);
        this.entityName = entityName;
    }

    public ResponseEntity<ODTO> create(IDTO inputDTO) {

        log.debug("REST request to save {} : {}", entityName, inputDTO);

        final ODTO result = crudService.create(inputDTO);

        try {
            return ResponseEntity.created(new URI(entityUrl + "/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(entityName, result.getId().toString()))
                    .body(result);
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            throw new EntityCreationException();
        }
    }

    public ResponseEntity<ODTO> update(Long id, IDTO inputDTO) {

        log.debug("REST request to update {} : {} with id {}", entityName, inputDTO, id);

        final ODTO result = crudService.update(id, inputDTO);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(entityName, result.getId().toString()))
                .body(result);
    }

    public ResponseEntity<Void> delete(Long id) {

        log.debug("REST request to delete {} : {}", entityName, id);

        crudService.delete(id);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(entityName, id.toString())).build();
    }
}
