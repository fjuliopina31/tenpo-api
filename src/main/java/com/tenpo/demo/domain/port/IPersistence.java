package com.tenpo.demo.domain.port;

import java.util.List;

public interface IPersistence<T, ID> {

    T save(T entity);

    T findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    void update(T entity);
}
