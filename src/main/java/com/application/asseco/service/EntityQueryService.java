package com.application.asseco.service;

import com.application.asseco.utils.QueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntityQueryService<CRITERIA, ENTITY> {

    Page<ENTITY> find(CRITERIA criteria, Pageable pageable);
}
