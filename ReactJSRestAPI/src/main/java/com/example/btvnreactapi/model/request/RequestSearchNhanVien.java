package com.example.btvnreactapi.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * By Duc Hiiu212
 */
@Getter
@Setter
public class RequestSearchNhanVien {

    private String gioiTinhSearch;

    private String diaChiSdtSearch;

    private Integer pageNo = 0;

}
