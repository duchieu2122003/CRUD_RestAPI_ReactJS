package com.example.btvnreactapi.model.request;

import com.example.btvnreactapi.entity.ChucVu;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

/**
 * By Duc Hiiu212
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateNhanVien {

    @NotBlank(message = "Ma trong !")
    private String ma;

    @NotBlank(message = "Ten trong !")
    @Size(max = 30, message = "Do dai <=30")
    private String ten;

    @NotBlank(message = "Gioi tinh trong")
    private String gioiTinh;

//    @NotNull(message = "Ngay sinh trong")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @PastOrPresent(message = "Ngay sinh phai nho hon ngay hien tai")
//    private Date ngaySinh;

    @NotBlank(message = "Dia chi trong")
    private String diaChi;

    @NotBlank(message = "So dien thoai trong")
    private String sdt;

    @NotNull(message = "Chuc vu trong")
    private UUID idChucVu;

    @NotNull(message = "Tranng thai trong")
    private Integer trangThai;//0 hd, 1 ngung hd

}
