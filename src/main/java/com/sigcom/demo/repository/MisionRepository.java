package com.sigcom.demo.repository;

import com.sigcom.demo.model.Mision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MisionRepository extends JpaRepository<Mision, Long> {
}