package com.dachang.daka.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dachang.daka.domain.Entry;

import com.dachang.daka.repository.EntryRepository;
import com.dachang.daka.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Entry.
 */
@RestController
@RequestMapping("/api")
public class EntryResource {

    private final Logger log = LoggerFactory.getLogger(EntryResource.class);

    private static final String ENTITY_NAME = "entry";

    private final EntryRepository entryRepository;

    public EntryResource(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    /**
     * POST  /entries : Create a new entry.
     *
     * @param entry the entry to create
     * @return the ResponseEntity with status 201 (Created) and with body the new entry, or with status 400 (Bad Request) if the entry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/entries")
    @Timed
    public ResponseEntity<Entry> createEntry(@Valid @RequestBody Entry entry) throws URISyntaxException {
        log.debug("REST request to save Entry : {}", entry);
        if (entry.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new entry cannot already have an ID")).body(null);
        }
        Entry result = entryRepository.save(entry);
        return ResponseEntity.created(new URI("/api/entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /entries : Updates an existing entry.
     *
     * @param entry the entry to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated entry,
     * or with status 400 (Bad Request) if the entry is not valid,
     * or with status 500 (Internal Server Error) if the entry couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/entries")
    @Timed
    public ResponseEntity<Entry> updateEntry(@Valid @RequestBody Entry entry) throws URISyntaxException {
        log.debug("REST request to update Entry : {}", entry);
        if (entry.getId() == null) {
            return createEntry(entry);
        }
        Entry result = entryRepository.save(entry);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, entry.getId().toString()))
            .body(result);
    }

    /**
     * GET  /entries : get all the entries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of entries in body
     */
    @GetMapping("/entries")
    @Timed
    public List<Entry> getAllEntries() {
        log.debug("REST request to get all Entries");
        return entryRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /entries/:id : get the "id" entry.
     *
     * @param id the id of the entry to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the entry, or with status 404 (Not Found)
     */
    @GetMapping("/entries/{id}")
    @Timed
    public ResponseEntity<Entry> getEntry(@PathVariable Long id) {
        log.debug("REST request to get Entry : {}", id);
        Entry entry = entryRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(entry));
    }

    /**
     * DELETE  /entries/:id : delete the "id" entry.
     *
     * @param id the id of the entry to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/entries/{id}")
    @Timed
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        log.debug("REST request to delete Entry : {}", id);
        entryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
