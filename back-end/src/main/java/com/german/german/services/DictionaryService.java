package com.german.german.services;

import com.german.german.controller.DataPage;
import com.german.german.dto.DictionaryGet;
import com.german.german.dto.DictionaryPatch;
import com.german.german.dto.DictionaryPost;
import com.german.german.dto.StandardQueryParams;
import com.german.german.entity.Dictionary;
import com.german.german.mappers.DictionaryMapper;
import com.german.german.repository.DictionaryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@AllArgsConstructor
@Slf4j
@Service
public class DictionaryService extends BaseService{
    @Autowired
    DictionaryMapper dictionaryMapper;
    @Autowired
    DictionaryRepository dictionaryRepository;

    public DictionaryGet create(DictionaryPost dto) {
        Dictionary dictionary = create(Dictionary.class,dto,dictionaryMapper::toEntity);
        return dictionaryMapper.fromEntity(dictionary);
    }
    public DataPage<DictionaryGet> findAll(StandardQueryParams queryParams){
        return findAll("dictionaries", queryParams,
                pageRequest -> dictionaryRepository.findAll(pageRequest),dictionaryMapper::fromEntity);
    }
    public DictionaryGet update(Long id, DictionaryPatch dto) {
        Dictionary dictionary = updateEntity(
                Dictionary.class,
                () ->  dictionaryRepository.findOne(id),
                () -> dto,
                dictionaryMapper::copy);

        return dictionaryMapper.fromEntity(dictionary);
    }

    public void delete(Long leaderBoardId) {
        delete(Dictionary.class, leaderBoardId);
    }
    public DictionaryGet findOne(Long leaderBoardId) {
        return dictionaryMapper.fromEntity(findEntity(leaderBoardId));
    }
    public Dictionary findEntity(Long leaderBoardId) {
        return findOne(Dictionary.class, leaderBoardId);
    }
}

