package com.tenpo.demo.infrastructure.utilities;

import com.tenpo.demo.domain.Calculate;
import com.tenpo.demo.infrastructure.rest.dto.CalculateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CalculateMapper {
    CalculateMapper INSTANCE = Mappers.getMapper(CalculateMapper.class);
    Calculate toDomain(CalculateDTO calculateDTO);
    CalculateDTO toDTO(Calculate calculate);
}
