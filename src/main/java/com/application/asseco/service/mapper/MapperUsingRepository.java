package com.application.asseco.service.mapper;

import com.application.asseco.dto.input.InputDTO;
import com.application.asseco.dto.output.OutputDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class MapperUsingRepository<IDTO extends InputDTO, ODTO extends OutputDTO, E> implements
        IOEntityMapper<IDTO, ODTO, E> {

    private Class<E> type;

    public MapperUsingRepository() {
        Type type = getClass().getGenericSuperclass();

        while (!(type instanceof ParameterizedType)
                || ((ParameterizedType) type).getRawType() != MapperUsingRepository.class) {
            if (type instanceof ParameterizedType) {
                type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
            } else {
                type = ((Class<?>) type).getGenericSuperclass();
            }
        }

        this.type = (Class<E>) ((ParameterizedType) type).getActualTypeArguments()[1];
    }

    @BeforeMapping
    public void validateEntityId(Long id, IDTO idto, @MappingTarget E entity) {
        final boolean exists = existsById(id);
        if (!exists) {
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with id %s exists!", getEntitySimpleName(), id), 1);
        }
    }

    protected boolean existsById(Long id) {
        return getRepository().existsById(id);
    }

    public E fromId(Long id) {
        if (id == null) {
            return null;
        }

        final Optional<E> result = findById(id);

        if (!result.isPresent()) {
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with id %s exists!", getEntitySimpleName(), id), 1);
        }

        return result.get();
    }

    protected Optional<E> findById(Long id) {
        return getRepository().findById(id);
    }

    protected abstract JpaRepository<E, Long> getRepository();

    private String getEntitySimpleName() {
        if (this.type != null) {
            return this.type.getSimpleName();
        }
        return "";
    }
}
