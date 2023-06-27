package com.grupo3a.ecommercefrutos.service;

import com.grupo3a.ecommercefrutos.repository.SectionRepository;
import com.grupo3a.ecommercefrutos.entity.Section;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public List<Section> getAllSections() {
        return repository.findAll();
    }

    public Section getSectionById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
