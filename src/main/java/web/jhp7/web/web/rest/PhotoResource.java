package web.jhp7.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import web.jhp7.web.service.PhotoService;
import web.jhp7.web.web.rest.errors.BadRequestAlertException;
import web.jhp7.web.web.rest.util.HeaderUtil;
import web.jhp7.web.web.rest.util.PaginationUtil;
import web.jhp7.web.service.dto.PhotoDTO;
import web.jhp7.web.service.dto.PhotoCriteria;
import web.jhp7.web.service.PhotoQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Photo.
 */
@RestController
@RequestMapping("/api")
public class PhotoResource {

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    private static final String ENTITY_NAME = "photo";

    private final PhotoService photoService;

    private final PhotoQueryService photoQueryService;

    public PhotoResource(PhotoService photoService, PhotoQueryService photoQueryService) {
        this.photoService = photoService;
        this.photoQueryService = photoQueryService;
    }

    /**
     * POST  /photos : Create a new photo.
     *
     * @param photoDTO the photoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new photoDTO, or with status 400 (Bad Request) if the photo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/photos")
    @Timed
    public ResponseEntity<PhotoDTO> createPhoto(@Valid @RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to save Photo : {}", photoDTO);
        if (photoDTO.getId() != null) {
            throw new BadRequestAlertException("A new photo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhotoDTO result = photoService.save(photoDTO);
        return ResponseEntity.created(new URI("/api/photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /photos : Updates an existing photo.
     *
     * @param photoDTO the photoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated photoDTO,
     * or with status 400 (Bad Request) if the photoDTO is not valid,
     * or with status 500 (Internal Server Error) if the photoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/photos")
    @Timed
    public ResponseEntity<PhotoDTO> updatePhoto(@Valid @RequestBody PhotoDTO photoDTO) throws URISyntaxException {
        log.debug("REST request to update Photo : {}", photoDTO);
        if (photoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhotoDTO result = photoService.save(photoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, photoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /photos : get all the photos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of photos in body
     */
    @GetMapping("/photos")
    @Timed
    public ResponseEntity<List<PhotoDTO>> getAllPhotos(PhotoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Photos by criteria: {}", criteria);
        Page<PhotoDTO> page = photoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/photos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /photos/:id : get the "id" photo.
     *
     * @param id the id of the photoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the photoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/photos/{id}")
    @Timed
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable Long id) {
        log.debug("REST request to get Photo : {}", id);
        Optional<PhotoDTO> photoDTO = photoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(photoDTO);
    }

    /**
     * DELETE  /photos/:id : delete the "id" photo.
     *
     * @param id the id of the photoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/photos/{id}")
    @Timed
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        log.debug("REST request to delete Photo : {}", id);
        photoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}