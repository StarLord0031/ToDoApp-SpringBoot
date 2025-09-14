package com.todoapp.todoapp.repository;

import com.todoapp.todoapp.entity.todoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepo extends JpaRepository<todoEntity,Long> {

}
