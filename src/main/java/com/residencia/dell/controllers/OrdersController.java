package com.residencia.dell.controllers;

import com.residencia.dell.entities.Orders;
import com.residencia.dell.services.OrdersService;
import com.residencia.dell.vo.NotaFiscalVO;
import com.residencia.dell.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ordersService.findById(id), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<OrdersVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ordersService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return ordersService.count();
    }

    @GetMapping("/nota-fiscal/{id}")
    public ResponseEntity<NotaFiscalVO> emitirNotaFiscal(@PathVariable Integer id) {
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(ordersService.emitirNF(id), headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrdersVO> save(@RequestBody OrdersVO ordersVO) {
        HttpHeaders headers = new HttpHeaders();

        OrdersVO novaOrderVO = ordersService.saveVO(ordersVO);

        if (null != novaOrderVO)
            return new ResponseEntity<>(novaOrderVO, headers, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> update(@PathVariable Integer id, @RequestBody Orders orders) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ordersService.update(id, orders), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> delete(@PathVariable Integer id) {
        try {
            ordersService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
    /*@GetMapping
    public ResponseEntity<List<Orders>> findAll(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(ordersService.findAll(pagina, qtdRegistros), headers, HttpStatus.OK);
    }*/

}
