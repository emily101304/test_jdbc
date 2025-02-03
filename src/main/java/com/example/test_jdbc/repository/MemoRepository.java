package com.example.test_jdbc.repository;

import com.example.test_jdbc.entity.Memo;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {

    Memo save(Memo memo);
    Optional<Memo> findById(Long id);
    List<Memo> findAll();
    Memo updateContent(Long id, String content);
    void deleteById(Long id);
}
