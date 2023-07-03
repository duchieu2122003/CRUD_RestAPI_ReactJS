package com.example.btvnreactapi.service;

import com.example.btvnreactapi.entity.NhanVien;
import com.example.btvnreactapi.model.request.RequestCreateNhanVien;
import com.example.btvnreactapi.model.request.RequestSearchNhanVien;
import com.example.btvnreactapi.model.respone.ResponeNhanVienTable;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

/**
 * By Duc Hiiu212
 */
public interface NhanVienService {

    List<NhanVien> findAllNV();
    List<ResponeNhanVienTable> findAll(Integer pageNo);

    NhanVien getOne(UUID uuid);

    Page<ResponeNhanVienTable> getPageAll(RequestSearchNhanVien request);

    ResponeNhanVienTable add( RequestCreateNhanVien request);

    ResponeNhanVienTable updateById(@Valid RequestCreateNhanVien request, UUID id);

    UUID deleteById(UUID id);

}
