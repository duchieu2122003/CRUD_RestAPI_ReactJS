package com.example.btvnreactapi.service.impl;

import com.example.btvnreactapi.entity.ChucVu;
import com.example.btvnreactapi.repository.ChucVuRepository;
import com.example.btvnreactapi.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * By Duc Hiiu212
 */
@Service
public class ChucVuImpl implements ChucVuService {

    @Autowired
    private ChucVuRepository repository;

    @Override
    public List<ChucVu> getAll() {
        return repository.findAll();
    }
    
}
