package com.application.asseco.service.impl;

import com.application.asseco.criteria.UserCriteria;
import com.application.asseco.dto.output.UserOutputDTO;
import com.application.asseco.model.domain.AppUser;
import com.application.asseco.model.domain.AppUser_;
import com.application.asseco.repository.UserRepository;
import com.application.asseco.service.EntityQueryService;
import com.application.asseco.service.mapper.UserMapper;
import com.application.asseco.utils.QueryService;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserQueryService extends QueryService<AppUser> implements EntityQueryService<UserCriteria, AppUser> {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserQueryService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Page<AppUser> find(UserCriteria criteria, Pageable pageable) {
        Page<AppUser> results;
        final Specification<AppUser> specification = createSpecification(criteria);
        if (specification != null) {
            results = userRepository.findAll(specification, pageable);
        } else {
            results = new PageImpl<>(Lists.newArrayList());
        }

        return results;
    }

    private Specification<AppUser> createSpecification(final UserCriteria criteria) {
        Specification<AppUser> specification = Specification.where(null);

        if (Objects.nonNull(criteria)) {
            if (Objects.nonNull(criteria.getId())) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AppUser_.id));
            }

            if (Objects.nonNull(criteria.getAddress())) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), AppUser_.address));
            }

            if (Objects.nonNull(criteria.getEmail())) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), AppUser_.email));
            }

            if (Objects.nonNull(criteria.getName())) {
                specification = specification.and(buildStringSpecification(criteria.getName(), AppUser_.name));
            }

            if (Objects.nonNull(criteria.getPhone())) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), AppUser_.phone));
            }

            if (Objects.nonNull(criteria.getUserName())) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), AppUser_.userName));
            }

        }

        return specification;
    }
}
