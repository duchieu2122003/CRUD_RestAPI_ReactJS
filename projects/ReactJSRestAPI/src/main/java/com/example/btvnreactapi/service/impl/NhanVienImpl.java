package com.example.btvnreactapi.service.impl;

import com.example.btvnreactapi.entity.NhanVien;
import com.example.btvnreactapi.model.request.RequestCreateNhanVien;
import com.example.btvnreactapi.model.request.RequestSearchNhanVien;
import com.example.btvnreactapi.model.respone.ResponeNhanVienTable;
import com.example.btvnreactapi.repository.ChucVuRepository;
import com.example.btvnreactapi.repository.NhanVienRepository;
import com.example.btvnreactapi.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * By Duc Hiiu212
 */
@Service
public class NhanVienImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository repository;

    @Autowired
    private ChucVuRepository cvRepository;

    @Override
    public List<NhanVien> findAllNV() {
        return repository.findAll();
    }

    @Override
    public List<ResponeNhanVienTable> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return repository.getAll(pageable);
    }

    @Override
    public NhanVien getOne(UUID uuid) {
        return repository.findById(uuid).get();
    }

    @Override
    public Page<ResponeNhanVienTable> getPageAll(RequestSearchNhanVien request) {
        Pageable pageable = PageRequest.of(request.getPageNo(), 5);
        return repository.findAllNhanVienCustom(request, pageable);
    }

    @Override
    public ResponeNhanVienTable add(RequestCreateNhanVien request) {

        NhanVien nhanVien = NhanVien.builder()
                .ma(request.getMa())
                .ten(request.getTen())
                .gioiTinh(request.getGioiTinh())
                .diaChi(request.getDiaChi())
                .sdt(request.getSdt())
                .trangThai(request.getTrangThai())
                .chucVu(cvRepository.findById(request.getIdChucVu()).get())
                .build();
        repository.save(nhanVien);
        if (repository.findByCustomId(nhanVien.getId()) != null) {
            return repository.findByCustomId(nhanVien.getId());
        }
        return null;
    }

    @Override
    public ResponeNhanVienTable updateById(RequestCreateNhanVien request, UUID id) {

        NhanVien nhanVien = NhanVien.builder()
                .id(id)
                .ma(request.getMa())
                .ten(request.getTen())
                .gioiTinh(request.getGioiTinh())
                .diaChi(request.getDiaChi())
                .sdt(request.getSdt())
                .trangThai(request.getTrangThai())
                .chucVu(cvRepository.findById(request.getIdChucVu()).get())
                .build();
        repository.save(nhanVien);
        if (repository.findByCustomId(nhanVien.getId()) != null) {
            return repository.findByCustomId(nhanVien.getId());
        }
        return null;
    }

    @Override
    public UUID deleteById(UUID id) {
        repository.delete(repository.findById(id).get());
        return id;
    }

}
