package com.tenpo.demo.infrastructure.db;

import com.tenpo.demo.domain.ApiCallHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApiCallHistoryRepository extends JpaRepository<ApiCallHistoryEntity, Long> {
}
