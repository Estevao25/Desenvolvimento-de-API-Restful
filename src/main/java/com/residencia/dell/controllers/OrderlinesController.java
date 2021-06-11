package com.residencia.dell.controllers;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.services.OrderlinesService;
import com.residencia.dell.vo.OrderlinesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderlines")
public class OrderlinesController {

    @Autowired
    private OrderlinesService orderlinesService;

    @GetMapping("/{orderlineId}/{orderId}")
    public ResponseEntity<Orderlines> findById(@PathVariable Integer orderlineId, @PathVariable Integer orderId) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(orderlinesService.findById(orderlineId, orderId), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-orderlines")
    public ResponseEntity<List<OrderlinesVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(orderlinesService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return orderlinesService.count();
    }

    @PostMapping
    public ResponseEntity<OrderlinesVO> save(@RequestBody OrderlinesVO orderlinesVO) {
        HttpHeaders headers = new HttpHeaders();

        OrderlinesVO novaOrderlinesVO = orderlinesService.saveVO(orderlinesVO);
        if (null != novaOrderlinesVO)
            return ResponseEntity.ok().body(novaOrderlinesVO);
        else
            return new ResponseEntity<>(orderlinesService.saveVO(novaOrderlinesVO), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{orderlineId}/{orderId}")
    public ResponseEntity<Orderlines> update(@PathVariable Integer orderlineId, @PathVariable Integer orderId, @RequestBody Orderlines orderlines) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(orderlinesService.update(orderlineId, orderId, orderlines), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{orderlineId}/{orderId}")
    public ResponseEntity<Orderlines> delete(@PathVariable Integer orderlineId, @PathVariable Integer orderId) {
        try {
            orderlinesService.delete(orderlineId, orderId);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity<List<Orderlines>> findAll(
//            @RequestParam(required = false) Integer pagina,
//            @RequestParam(required = false) Integer qtdRegistros)
//            throws Exception {
//
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(orderlinesService.findAll(pagina,
//                qtdRegistros), headers, HttpStatus.OK);
//    }
}
