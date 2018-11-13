package com.german.german.mappers;

import com.german.german.dto.DictionaryGet;
import com.german.german.dto.DictionaryPatch;
import com.german.german.dto.DictionaryPost;
import com.german.german.entity.Dictionary;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DictionaryMapper {

    public abstract DictionaryGet fromEntity(Dictionary leaderBoard);

    public abstract Dictionary toEntity(DictionaryPost leaderBoardPostDto);

    public abstract void copy(DictionaryPatch dto, @MappingTarget Dictionary leaderBoard);
}
