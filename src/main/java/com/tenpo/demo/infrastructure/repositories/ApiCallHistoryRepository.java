package com.tenpo.demo.infrastructure.repositories;

import com.tenpo.demo.domain.ApiCallHistory;
import com.tenpo.demo.domain.port.IPersistence;
import com.tenpo.demo.infrastructure.db.ApiCallHistoryEntity;
import com.tenpo.demo.infrastructure.db.IApiCallHistoryRepository;
import com.tenpo.demo.infrastructure.utilities.ApiCallHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ApiCallHistoryRepository implements IPersistence<ApiCallHistory, Long> {

    @Autowired
    private IApiCallHistoryRepository repository;

    @Autowired
    private ApiCallHistoryMapper mapper;

    @Override
    public ApiCallHistory save(ApiCallHistory apiCallHistory) {
        log.info("Saving API call history: {}", apiCallHistory.toString());
        ApiCallHistoryEntity entity = mapper.toEntity(apiCallHistory);
        log.info("Saved API call history: {}", entity.toString());
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public ApiCallHistory findById(Long id) {
        ApiCallHistoryEntity entity = repository.findById(id).orElse(null);
        return mapper.toDomain(entity);
    }

    @Override
    public List<ApiCallHistory> findAll() {
        List<ApiCallHistoryEntity> entities = repository.findAll();
        log.info("Found {} API call history records", entities.size());
        if (!entities.isEmpty()) {
            return entities.stream()
                    .map(mapper::toDomain)
                    .toList();
        }
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(ApiCallHistory entity) {
        repository.save(mapper.toEntity(entity));
    }
}
