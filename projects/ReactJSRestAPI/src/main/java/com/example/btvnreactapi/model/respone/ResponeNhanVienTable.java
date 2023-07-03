package com.example.btvnreactapi.model.respone;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

/**
 * By Duc Hiiu212
 */
public interface ResponeNhanVienTable {

    @Value("#{target.idNhanVien}")
    UUID getIdNhanVien();

    @Value("#{target.maNhanVien}")
    String getMaNhanVien();

    @Value("#{target.tenNhanVien}")
    String getTenNhanVien();

    @Value("#{target.gioiTinh}")
    String getGioiTinh();


    @Value("#{target.diaChi}")
    String getDiaChi();

    @Value("#{target.sdt}")
    String getSdt();

    @Value("#{target.idChucVu}")
    UUID getIdChucVu();

    @Value("#{target.tenChucVu}")
    String getTenChucVu();

    @Value("#{target.trangThai}")
    Integer getTrangThai();
}
