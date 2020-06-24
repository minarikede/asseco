package com.application.asseco.web.rest;

import com.application.asseco.criteria.UserCriteria;
import com.application.asseco.dto.PageableDTO;
import com.application.asseco.dto.input.UserInputDTO;
import com.application.asseco.dto.output.UserOutputDTO;
import com.application.asseco.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(UserResource.ENTITY_URL)
public class UserResource extends AbstractCRUDController <UserOutputDTO, UserInputDTO, UserCriteria> {

    final static String ENTITY_NAME = "User";

    final static String ENTITY_URL = "/users";

    public UserResource(CrudService<UserOutputDTO, UserInputDTO, UserCriteria> crudService) {
        super(crudService, ENTITY_NAME, ENTITY_URL);
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> register(@Valid @RequestBody UserInputDTO inputDTO) {
        return super.create(inputDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> update(@PathVariable Long id, @Valid @RequestBody UserInputDTO inputDTO) {
        return super.update(id, inputDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserOutputDTO> get(@PathVariable Long id) {
        return super.get(id);
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAll(UserCriteria userCriteria, PageableDTO pageable) {
        return super.getAll(userCriteria, createPageable(pageable));
    }
}
