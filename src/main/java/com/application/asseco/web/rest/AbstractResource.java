package com.application.asseco.web.rest;

import com.application.asseco.dto.OrderDTO;
import com.application.asseco.dto.PageableDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractResource {

    protected static Sort getSort(final List<OrderDTO> sort) {
        if (sort != null && !sort.isEmpty()) {
            return new Sort(sort.stream()
                    .filter(sortList -> !StringUtils.isEmpty(sortList.getName()))
                    .map(sortList ->
                            new Sort.Order(sortList.getDirection() != null
                                    ? Sort.Direction.fromString(sortList.getDirection().getUrlEncodedName())
                                    : null, sortList.getName().trim())
                    ).collect(Collectors.toList()));
        }
        return Sort.unsorted();
    }

    protected PageRequest createPageable(PageableDTO pageableDTO) {
        return new PageRequest(pageableDTO.getPage(), Math.min(pageableDTO.getSize(), 10), getSort(pageableDTO.getSort()));
    }
}
