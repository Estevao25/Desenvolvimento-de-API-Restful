package com.residencia.dell.services;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.entities.Products;
import com.residencia.dell.repositories.CustomersRepository;
import com.residencia.dell.repositories.OrderlinesRepository;
import com.residencia.dell.repositories.OrdersRepository;
import com.residencia.dell.repositories.ProductsRepository;
import com.residencia.dell.vo.ItemOrderlinesVO;
import com.residencia.dell.vo.NotaFiscalVO;
import com.residencia.dell.vo.OrderlinesVO;
import com.residencia.dell.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    OrderlinesRepository orderlinesRepository;

    @Autowired
    CustomersRepository customersRepository;


    public Orders findById(Integer id) {
        return ordersRepository.findById(id).get();
    }

    public List<OrdersVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Orders> listOrders = null;
        List<Orders> listOrdersComPaginacao = null;
        List<OrdersVO> listOrdersVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listOrdersComPaginacao = ordersRepository.findAll(page).getContent();

                for (Orders lOrders : listOrdersComPaginacao) {
                    listOrdersVO.add(convertEntidadeParaVO(lOrders));
                }

            } else {
                listOrders = ordersRepository.findAll();

                for (Orders lOrders : listOrders) {
                    listOrdersVO.add(convertEntidadeParaVO(lOrders));
                }
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de pedidos ::" + e.getMessage());
        }
        return listOrdersVO;
    }

    private OrdersVO convertEntidadeParaVO(Orders orders) {
        OrdersVO ordersVO = new OrdersVO();
        List<OrderlinesVO> listOrderlinesVO = new ArrayList<>();

        ordersVO.setNetAmount(orders.getNetAmount());
        ordersVO.setOrderDate(orders.getOrderDate());
        ordersVO.setOrderId(orders.getOrderId());
        ordersVO.setTax(orders.getTax());
        ordersVO.setTotalAmount(orders.getTotalAmount());

        for (Orderlines lOrderlines : orders.getListOrderLines()) {
            OrderlinesVO orderLinesVO = new OrderlinesVO();

            orderLinesVO.setOrderDate(lOrderlines.getOrderDate());
            orderLinesVO.setOrderlineid(lOrderlines.getOrderLineId());
            orderLinesVO.setProdId(lOrderlines.getProdId());
            orderLinesVO.setQuantity(lOrderlines.getQuantity());

            listOrderlinesVO.add(orderLinesVO);
        }

        ordersVO.setListOrderLinesVO(listOrderlinesVO);

        return ordersVO;
    }

    private Orders converteVOParaEntidade (OrdersVO ordersVO) {
        Orders orders = new Orders();

        Customers customer = customersRepository.getById(ordersVO.getCustomerId());

        orders.setNetAmount(ordersVO.getNetAmount());
        orders.setOrderDate(ordersVO.getOrderDate());
        orders.setTax(ordersVO.getTax());
        orders.setTotalAmount(ordersVO.getTotalAmount());
        orders.setCustomer(customer);

        List<Orderlines> listOrderlines = new ArrayList<>();
        for (OrderlinesVO lOrderlinesVO : ordersVO.getListOrderLinesVO()) {
            Orderlines orderLines = new Orderlines();

            orderLines.setOrderDate(lOrderlinesVO.getOrderDate());
            orderLines.setOrderLineId(lOrderlinesVO.getOrderlineid());
            orderLines.setProdId(lOrderlinesVO.getProdId());
            orderLines.setQuantity(lOrderlinesVO.getQuantity());

            listOrderlines.add(orderLines);
            orders.setListOrderLines(listOrderlines);
        }
        return orders;
    }

    public long count() {
        return ordersRepository.count();
    }

    public OrdersVO saveVO(OrdersVO ordersVO) {
        Orders novaOrder = converteVOParaEntidade(ordersVO);
        Orders order = ordersRepository.save(novaOrder);

        List<Orderlines> listOrderline = order.getListOrderLines();
        for (int i = 0; i < listOrderline.size(); i++) {
            listOrderline.get(i).setOrderLineId(i + 100);
            orderlinesRepository.save(listOrderline.get(i));
        }
        return convertEntidadeParaVO(novaOrder);
    }

    public Orders update(Integer id, Orders orders) {
        Orders newOrders = ordersRepository.findById(id).get();
        updateDados(newOrders, orders);
        return ordersRepository.save(newOrders);
    }

    public void delete(Integer id) {
        if (id != null) {
            ordersRepository.deleteById(id);
        }
    }

    private void updateDados(Orders newOrders, Orders orders) {
        newOrders.setOrderDate(orders.getOrderDate());
        newOrders.setCustomer(orders.getCustomer());
        newOrders.setNetAmount(orders.getNetAmount());
        newOrders.setTax(orders.getTax());
        newOrders.setTotalAmount(orders.getTotalAmount());
    }

    public NotaFiscalVO emitirNF(Integer orderId) {
        Orders orders = ordersRepository.getById(orderId);
        List<Orderlines> listOrderlines = orders.getListOrderLines();

        NotaFiscalVO notaFiscalVO = new NotaFiscalVO();

        if (orders.getCustomer() != null) {
            notaFiscalVO.setFirstName(orders.getCustomer().getFirstname());
            notaFiscalVO.setLastName(orders.getCustomer().getLastname());
        } else {
            notaFiscalVO.setFirstName(null);
            notaFiscalVO.setLastName(null);
        }
        notaFiscalVO.setNetAmount(orders.getNetAmount());
        notaFiscalVO.setOrderDate(orders.getOrderDate());
        notaFiscalVO.setOrderId(orders.getOrderId());
        notaFiscalVO.setTotalAmount(orders.getTotalAmount());

        List<ItemOrderlinesVO> listItemOrderlinesVO = new ArrayList<>();
        for (Orderlines orderlines : listOrderlines) {
            ItemOrderlinesVO itemOrderlinesVO = new ItemOrderlinesVO();

            Products products = productsRepository.findById(orderlines.getProdId()).get();

            itemOrderlinesVO.setProdId(orderlines.getProdId());
            itemOrderlinesVO.setQuantity(orderlines.getQuantity());
            itemOrderlinesVO.setTitle(products.getTitle());

            listItemOrderlinesVO.add(itemOrderlinesVO);
            notaFiscalVO.setListOrderlinesVO(listItemOrderlinesVO);
        }
        return notaFiscalVO;
    }

//    public List<Orders> findAll() {
//        return ordersRepository.findAll();
//    }
//
//    public List<Orders> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
//        Pageable page = null;
//        List<Orders> listOrders = null;
//        List<Orders> listOrdersComPaginacao = null;
//
//        try {
//            if (null != pagina && null != qtdRegistros) {
//                page = PageRequest.of(pagina, qtdRegistros);
//                listOrdersComPaginacao = ordersRepository.findAll(page).getContent();
//
//                return listOrdersComPaginacao;
//            } else {
//                listOrders = ordersRepository.findAll();
//
//                return listOrders;
//            }
//        } catch (Exception e) {
//            throw new Exception("Nâo foi possível recuperar a lista de pedidos ::" + e.getMessage());
//        }
//    }
//
//    public Orders save(Orders orders) {
//        Orders newOrders = ordersRepository.save(orders);
//        if (newOrders.getOrderId() != null) {
//            return newOrders;
//        } else {
//            return null;
//        }
//    }
}