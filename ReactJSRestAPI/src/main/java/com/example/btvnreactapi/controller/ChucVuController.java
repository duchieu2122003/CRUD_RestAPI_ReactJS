package com.example.btvnreactapi.controller;

import com.example.btvnreactapi.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * By Duc Hiiu212
 */
@RestController
@RequestMapping("/chuc-vu")
@CrossOrigin({"*"})
public class ChucVuController {

    @Autowired
    private ChucVuService service;

    @GetMapping("/hien-thi")
    public ResponseEntity hienThiChucVu() {
        return new ResponseEntity(service.getAll(), HttpStatus.OK);
    }

}
