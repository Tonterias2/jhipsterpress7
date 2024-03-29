package web.jhp7.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import web.jhp7.web.domain.Calbum;
import web.jhp7.web.domain.*; // for static metamodels
import web.jhp7.web.repository.CalbumRepository;
import web.jhp7.web.service.dto.CalbumCriteria;

import web.jhp7.web.service.dto.CalbumDTO;
import web.jhp7.web.service.mapper.CalbumMapper;

/**
 * Service for executing complex queries for Calbum entities in the database.
 * The main input is a {@link CalbumCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CalbumDTO} or a {@link Page} of {@link CalbumDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CalbumQueryService extends QueryService<Calbum> {

    private final Logger log = LoggerFactory.getLogger(CalbumQueryService.class);

    private final CalbumRepository calbumRepository;

    private final CalbumMapper calbumMapper;

    public CalbumQueryService(CalbumRepository calbumRepository, CalbumMapper calbumMapper) {
        this.calbumRepository = calbumRepository;
        this.calbumMapper = calbumMapper;
    }

    /**
     * Return a {@link List} of {@link CalbumDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CalbumDTO> findByCriteria(CalbumCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Calbum> specification = createSpecification(criteria);
        return calbumMapper.toDto(calbumRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CalbumDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CalbumDTO> findByCriteria(CalbumCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Calbum> specification = createSpecification(criteria);
        return calbumRepository.findAll(specification, page)
            .map(calbumMapper::toDto);
    }

    /**
     * Function to convert CalbumCriteria to a {@link Specification}
     */
    private Specification<Calbum> createSpecification(CalbumCriteria criteria) {
        Specification<Calbum> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Calbum_.id));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Calbum_.creationDate));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Calbum_.title));
            }
            if (criteria.getPhotoId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPhotoId(), Calbum_.photos, Photo_.id));
            }
            if (criteria.getCommunityId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCommunityId(), Calbum_.community, Community_.id));
            }
        }
        return specification;
    }

}
