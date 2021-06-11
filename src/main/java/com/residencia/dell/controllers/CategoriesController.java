package com.residencia.dell.controllers;

import com.residencia.dell.entities.Categories;
import com.residencia.dell.services.CategoriesService;
import com.residencia.dell.vo.CategoriesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/{id}")
    public ResponseEntity<Categories> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriesService.findById(id), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Categories>> findAll(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriesService.findAll(pagina, qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<CategoriesVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriesService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return categoriesService.count();
    }

    @PostMapping
    public ResponseEntity<Categories> save(@RequestBody Categories category) {
        HttpHeaders headers = new HttpHeaders();
        Categories categories = categoriesService.save(category);
        if (null != categories)
            return ResponseEntity.ok().body(categories);
        else
            return new ResponseEntity<>(categoriesService.save(categories), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> update(@PathVariable Integer id, @RequestBody Categories categories) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(categoriesService.update(id, categories), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categories> delete(@PathVariable Integer id) {
        try {
            categoriesService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
