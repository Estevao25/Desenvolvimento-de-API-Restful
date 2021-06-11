package com.residencia.dell.controllers;

import com.residencia.dell.entities.CustHist;
import com.residencia.dell.exceptions.CustHistException;
import com.residencia.dell.services.CustHistService;
import com.residencia.dell.vo.CustHistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custHist")
public class CustHistController {

    @Autowired
    private CustHistService custHistService;

    @GetMapping("/{id}")
    public ResponseEntity<CustHist> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(custHistService.findById(id), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-custhist")
    public ResponseEntity<List<CustHistVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(custHistService.findAllVO(pagina, qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return custHistService.count();
    }

    @PostMapping
    public ResponseEntity<CustHistVO> save(@RequestBody CustHistVO custHistVO) {

        HttpHeaders headers = new HttpHeaders();

        CustHistVO novaCustHistVO = custHistService.saveVO(custHistVO);

        if (null != novaCustHistVO)
            return ResponseEntity.ok().body(novaCustHistVO);
        else
            return new ResponseEntity<>(custHistService.saveVO(novaCustHistVO), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustHist> update(@PathVariable Integer id, @RequestBody CustHist custHist) throws CustHistException {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(custHistService.update(id, custHist), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustHist> delete(@PathVariable Integer id) {
        try {
            custHistService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity<List<CustHist>> findAll(
//            @RequestParam(required = false) Integer pagina,
//            @RequestParam(required = false) Integer qtdRegistros)
//            throws Exception {
//
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(custHistService.findAll(pagina, qtdRegistros), headers, HttpStatus.OK);
//    }
}