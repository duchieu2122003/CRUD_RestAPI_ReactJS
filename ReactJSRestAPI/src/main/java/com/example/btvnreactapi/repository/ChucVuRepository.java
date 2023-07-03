package com.example.btvnreactapi.repository;

import com.example.btvnreactapi.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * By Duc Hiiu212
 */
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
}
