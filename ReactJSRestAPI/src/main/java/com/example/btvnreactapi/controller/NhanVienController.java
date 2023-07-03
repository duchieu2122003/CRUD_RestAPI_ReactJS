package com.example.btvnreactapi.controller;

import com.example.btvnreactapi.model.request.RequestCreateNhanVien;
import com.example.btvnreactapi.model.request.RequestSearchNhanVien;
import com.example.btvnreactapi.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * By Duc Hiiu212
 */
@RestController
@RequestMapping("/nhan-vien")
@CrossOrigin({"*"})
public class NhanVienController {

    @Autowired
    private NhanVienService service;

    @GetMapping("/find")
    public ResponseEntity hienThiFind() {
        return new ResponseEntity(service.findAllNV(), HttpStatus.OK);
    }

    @GetMapping("/hien-thi")
    public ResponseEntity hienThi(final RequestSearchNhanVien searchNhanVien) {
        return new ResponseEntity(service.getPageAll(searchNhanVien), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody RequestCreateNhanVien create) {
        return new ResponseEntity(service.add(create), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update( @RequestBody RequestCreateNhanVien update,
                                 @PathVariable UUID id) {
        return new ResponseEntity(service.updateById(update, id), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable UUID id) {
        return new ResponseEntity(service.getOne(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        return new ResponseEntity(service.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity search(final RequestSearchNhanVien search) {
        return new ResponseEntity(service.getPageAll(search), HttpStatus.OK);
    }

}
