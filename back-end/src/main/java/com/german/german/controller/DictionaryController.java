package com.german.german.controller;

import com.german.german.dto.DictionaryGet;
import com.german.german.dto.DictionaryPatch;
import com.german.german.dto.DictionaryPost;
import com.german.german.dto.StandardQueryParamsWithFilter;
import com.german.german.entity.Dictionary;
import com.german.german.mappers.DictionaryMapper;
import com.german.german.repository.DictionaryRepository;
import com.german.german.services.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@Api(
        tags = {"Dictionary"},
        description = "Dictionary Resource"
)
@RestController
@RequestMapping(value = "/api/dictionary", produces = MediaType.APPLICATION_JSON_VALUE)
public class DictionaryController {

    @Autowired
    DictionaryMapper dictionaryMapper;
    @Autowired
    DictionaryRepository dictionaryRepository;
    @Autowired
    DictionaryService dictionaryService;

    @ApiOperation("Get all words")
    @GetMapping
    public ResponseEntity<DataPage<DictionaryGet>> findAll(@Valid StandardQueryParamsWithFilter standardQueryParams) {
        return ResponseEntity.ok(dictionaryService.findAll(standardQueryParams));
    }

    @ApiOperation("Create new word")
    @PostMapping()
    public ResponseEntity<DictionaryGet> save(
            @RequestBody @Valid DictionaryPost dictionaryPost,
            final BindingResult bindingResult) {
        return ResponseEntity.ok(dictionaryService.create(dictionaryPost));
    }

    @ApiOperation("Create one dictionary")
    @GetMapping("{dictionaryId}")
    public ResponseEntity<DictionaryGet> getOne(
            @PathVariable("dictionaryId") Long dictionaryId) {
        Dictionary dictionary = dictionaryService.findEntity(dictionaryId);
        return ResponseEntity.ok(dictionaryMapper.fromEntity(dictionary));
    }

    @PatchMapping(value = "/{dictionaryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update an existing dictionary")
    public ResponseEntity<DictionaryGet> update(@PathVariable("dictionaryId") Long dictionaryId, @RequestBody @Valid DictionaryPatch dto, final BindingResult bindingResult) {
        return ResponseEntity.ok(dictionaryService.update(dictionaryId, dto));
    }


    @DeleteMapping(value = "{dictionaryId}")
    @ApiOperation("Remove dictionary")
    public ResponseEntity<Object> deleteDictionary(@PathVariable("dictionaryId") Long dictionaryId) {
        dictionaryService.delete(dictionaryId);
        return ResponseEntity.noContent().build();
    }
}

