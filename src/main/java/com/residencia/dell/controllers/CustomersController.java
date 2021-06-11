package com.residencia.dell.controllers;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.services.CustomersService;
import com.residencia.dell.vo.CustomersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @GetMapping("/{id}")
    public ResponseEntity<Customers> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(customersService.findById(id), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customers>> findAll(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(customersService.findAll(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<CustomersVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(customersService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return customersService.count();
    }

    @PostMapping
    public ResponseEntity<Customers> save(@RequestBody Customers customer) {
        HttpHeaders headers = new HttpHeaders();
        Customers customers = customersService.save(customer);
        if (null != customers)
            return ResponseEntity.ok().body(customers);
        else
            return new ResponseEntity<>(customersService.save(customers), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customers> update(@PathVariable Integer id, @RequestBody Customers customers) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(customersService.update(id, customers), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customers> delete(@PathVariable Integer id) {
        try {
            customersService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}