package com.grupo3a.ecommercefrutos.repository;

import com.grupo3a.ecommercefrutos.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE section_sequence SET next_val = 1", nativeQuery = true)
    void resetSequence();
}
