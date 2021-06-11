package com.residencia.dell.controllers;

import com.residencia.dell.entities.Reorder;
import com.residencia.dell.services.ReorderService;
import com.residencia.dell.vo.ReorderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reorder")
public class ReorderController {

    @Autowired
    private ReorderService reorderService;

    @GetMapping("/{id}")
    public ResponseEntity<Reorder> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(reorderService.findById(id), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reorder>> findAll(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(reorderService.findAll(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<ReorderVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(reorderService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return reorderService.count();
    }

    @PostMapping
    public ResponseEntity<Reorder> save(@RequestBody Reorder reorder) {
        HttpHeaders headers = new HttpHeaders();
        Reorder reorders = reorderService.save(reorder);
        if (null != reorders)
            return ResponseEntity.ok().body(reorders);
        else
            return new ResponseEntity<>(reorderService.save(reorders), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reorder> update(@PathVariable Integer id, @RequestBody Reorder reorder) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(reorderService.update(id, reorder), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reorder> delete(@PathVariable Integer id) {
        try {
            reorderService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}