package com.example.btvnreactapi.repository;

import com.example.btvnreactapi.entity.NhanVien;
import com.example.btvnreactapi.model.request.RequestSearchNhanVien;
import com.example.btvnreactapi.model.respone.ResponeNhanVienTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * By Duc Hiiu212
 */
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {

    @Query(value = "select nv.id as idNhanVien, nv.ma as maNhanVien ,nv.ten as tenNhanVien, nv.gioiTinh as gioiTinh," +
            " nv.diaChi as diaChi, nv.sdt as sdt" +
            " nv.idCV as idChucVu, cv.ten as tenChucVu  ,nv.trangThai as trangThai " +
            " from NhanVien nv JOIN ChucVu cv ON cv.id = nv.idCV", nativeQuery = true)
    List<ResponeNhanVienTable> getAll(Pageable pageable);

    @Query(value = "select nv.id as idNhanVien, nv.ma as maNhanVien,nv.ten as tenNhanVien, nv.gioiTinh as gioiTinh," +
            "  nv.diaChi as diaChi, nv.sdt as sdt, " +
            " nv.idCV as idChucVu, cv.ten as tenChucVu , nv.trangThai as trangThai " +
            " from NhanVien as nv JOIN ChucVu as cv ON cv.id = nv.idCV " +
            " where (nv.gioiTinh LIKE :#{#request.gioiTinhSearch} OR :#{#request.gioiTinhSearch} IS NULL OR :#{#request.gioiTinhSearch} LIKE '') " +
            " AND " +
            "( nv.diaChi LIKE %:#{#request.diaChiSdtSearch}% OR  nv.sdt LIKE %:#{#request.diaChiSdtSearch}% OR :#{#request.diaChiSdtSearch} IS NULL OR :#{#request.diaChiSdtSearch} LIKE '')" +
            " ", nativeQuery = true)
    Page<ResponeNhanVienTable> findAllNhanVienCustom(@Param("request") RequestSearchNhanVien request, Pageable pageable);

    @Query(value = "select nv.id as idNhanVien,nv.ma as maNhanVien ,nv.ten as tenNhanVien, nv.gioiTinh as gioiTinh," +
            " nv.diaChi as diaChi, nv.sdt as sdt, " +
            " nv.idCV as idChucVu, cv.ten as tenChucVu , nv.trangThai as trangThai" +
            " from NhanVien nv JOIN ChucVu cv ON cv.id = nv.idCV WHERE nv.id =?1", nativeQuery = true)
    ResponeNhanVienTable findByCustomId(UUID id);

}
