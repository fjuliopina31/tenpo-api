package com.tenpo.demo.infrastructure.repositories;

import com.tenpo.demo.domain.ApiCallHistory;
import com.tenpo.demo.infrastructure.db.ApiCallHistoryEntity;
import com.tenpo.demo.infrastructure.db.IApiCallHistoryRepository;
import com.tenpo.demo.infrastructure.utilities.ApiCallHistoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApiCallHistoryRepositoryTest {

    @InjectMocks
    private ApiCallHistoryRepository apiCallHistoryRepository;

    @Mock
    private IApiCallHistoryRepository repository;

    @Mock
    private ApiCallHistoryMapper mapper;

    private ApiCallHistory domainObject;
    private ApiCallHistoryEntity entityObject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        domainObject = new ApiCallHistory();
        domainObject.setId(1L);
        domainObject.setEndpoint("/test");

        entityObject = new ApiCallHistoryEntity();
        entityObject.setId(1L);
        entityObject.setEndpoint("/test");
    }

    @Test
    void save_shouldSaveAndReturnDomain() {
        when(mapper.toEntity(domainObject)).thenReturn(entityObject);
        when(repository.save(entityObject)).thenReturn(entityObject);
        when(mapper.toDomain(entityObject)).thenReturn(domainObject);

        ApiCallHistory result = apiCallHistoryRepository.save(domainObject);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(entityObject);
    }

    @Test
    void findById_shouldReturnDomainIfFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(entityObject));
        when(mapper.toDomain(entityObject)).thenReturn(domainObject);

        ApiCallHistory result = apiCallHistoryRepository.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_shouldReturnNullIfNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ApiCallHistory result = apiCallHistoryRepository.findById(1L);

        assertNull(result);
    }

    @Test
    void findAll_shouldReturnMappedList() {
        when(repository.findAll()).thenReturn(List.of(entityObject));
        when(mapper.toDomain(entityObject)).thenReturn(domainObject);

        List<ApiCallHistory> result = apiCallHistoryRepository.findAll();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        doNothing().when(repository).deleteById(1L);

        apiCallHistoryRepository.deleteById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void update_shouldCallSave() {
        when(mapper.toEntity(domainObject)).thenReturn(entityObject);
        when(repository.save(entityObject)).thenReturn(entityObject);

        apiCallHistoryRepository.update(domainObject);

        verify(repository).save(entityObject);
    }
}
