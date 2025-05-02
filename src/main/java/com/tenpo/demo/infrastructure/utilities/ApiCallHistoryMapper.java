package com.tenpo.demo.infrastructure.utilities;

import com.tenpo.demo.domain.ApiCallHistory;
import com.tenpo.demo.infrastructure.db.ApiCallHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ApiCallHistoryMapper {
    ApiCallHistoryMapper INSTANCE = Mappers.getMapper(ApiCallHistoryMapper.class);
    ApiCallHistory toDomain(ApiCallHistoryEntity entity);
    ApiCallHistoryEntity toEntity(ApiCallHistory dto);
}
