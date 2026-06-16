package com.aula.oop.app.repository;

import com.aula.oop.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
    public interface TaskRepository extends JpaRepository<Task, Long> {
    }
