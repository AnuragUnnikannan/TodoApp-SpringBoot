package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepo extends JpaRepository<Todo, Integer> {
    @Query("select max(id) from Todo")
    public Integer findMaxId();
}
