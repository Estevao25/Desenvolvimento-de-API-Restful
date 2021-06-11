package com.residencia.dell.services;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.repositories.OrderlinesRepository;
import com.residencia.dell.repositories.OrdersRepository;
import com.residencia.dell.vo.OrderlinesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderlinesService {

    @Autowired
    OrderlinesRepository orderlinesRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public Orderlines findById(Integer orderlinesId, Integer ordersByOrderid) {
        Orders orders = ordersRepository.findById(ordersByOrderid).get();
        return orderlinesRepository.findByOrderLineIdAndOrders(orderlinesId, orders);
    }

    public List<Orderlines> findAll() {
        return orderlinesRepository.findAll();
    }

    public List<Orderlines> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Orderlines> listOrderlines = null;
        List<Orderlines> listOrderlinesComPaginacao = null;

        try {
            if (null != pagina && null != qtdRegistros) {
                page = PageRequest.of(pagina, qtdRegistros);
                listOrderlinesComPaginacao = orderlinesRepository.findAll(page).getContent();

                return listOrderlinesComPaginacao;
            } else {
                listOrderlines = orderlinesRepository.findAll();

                return listOrderlines;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de orderlines ::" + e.getMessage());
        }
    }

    public List<OrderlinesVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        PageRequest page = null;
        List<Orderlines> listOrderlines = null;
        List<Orderlines> listOrderlinesComPaginacao = null;
        List<OrderlinesVO> listOrderlinesVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listOrderlinesComPaginacao = orderlinesRepository.findAll(page).getContent();

                for (Orderlines lOrderlines : listOrderlinesComPaginacao) {
                    listOrderlinesVO.add(convertEntidadeParaVO(lOrderlines));
                }

            } else {
                listOrderlines = orderlinesRepository.findAll();

                for (Orderlines lOrderlines : listOrderlines) {
                    listOrderlinesVO.add(convertEntidadeParaVO(lOrderlines));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de orderlines ::" + e.getMessage());
        }

        return listOrderlinesVO;
    }

    private OrderlinesVO convertEntidadeParaVO(Orderlines orderlines) {
        OrderlinesVO orderlinesVO = new OrderlinesVO();

        orderlinesVO.setOrderlineid(orderlines.getOrderLineId());
        orderlinesVO.setProdId(orderlines.getProdId());
        orderlinesVO.setQuantity(orderlines.getQuantity());
        orderlinesVO.setOrderDate(orderlines.getOrderDate());

        return orderlinesVO;
    }

    public long count(){
        return orderlinesRepository.count();
    }

    public Orderlines save(Orderlines orderlines){
        /*precisa implementar pra pegar o ordersid tbm*/
        if (orderlinesRepository.save(orderlines).getOrderLineId() != null) {
            return orderlinesRepository.save(orderlines);
        } else{
            return null;
        }
    }

    public Orderlines update(Orderlines orderlines){
        return orderlinesRepository.save(orderlines);
    }

    public Orderlines update(Integer orderlineId, Integer orderId, Orderlines orderlines) {
        Orders orders = ordersRepository.findById(orderId).get();

        Orderlines newOrderlines = orderlinesRepository.findByOrderLineIdAndOrders(orderlineId, orders);
        updateDados(newOrderlines, orderlines);
        return orderlinesRepository.save(newOrderlines);
    }

    private void updateDados(Orderlines newOrderlines, Orderlines orderlines) {
        newOrderlines.setOrderLineId(orderlines.getOrderLineId());
        newOrderlines.setProdId(orderlines.getProdId());
        newOrderlines.setQuantity(orderlines.getQuantity());
        newOrderlines.setOrderDate(orderlines.getOrderDate());
    }

    public boolean delete(Integer orderlineId, Integer orderId){
        if(orderlineId != null || orderId != null){
            Orders orders = ordersRepository.findById(orderId).get();
            orderlinesRepository.deleteByOrderLineIdAndOrders(orderlineId, orders);
            return true;
        }else {
            return false;
        }
    }


        /*public long count() {
            return orderlinesRepository.count();
        }
        public Orderlines save(Orderlines orderlines, Integer orderlinesId, Integer ordersByOrderid) {
        Orders orders = ordersRepository.findById(ordersByOrderid).get();
        Orderlines newOrderlines = orderlinesRepository.save(orderlines);
        if (newOrderlines.getOrderLineId() != null) {
            return newOrderlines;
        } else {
            return null;
        }
    }

    public Orderlines update(Orderlines orderlines) {
        return orderlinesRepository.save(orderlines);
    }

    public boolean delete(Integer id) {
        if (id != null) {
            orderlinesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }*/
}