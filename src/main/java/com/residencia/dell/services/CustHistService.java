package com.residencia.dell.services;
/*
import java.util.ArrayList;
import java.util.List;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.CustHist;
import com.residencia.dell.vo.OrderlinesVO;
import com.residencia.dell.vo.CustHistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.residencia.dell.entities.CustHist;
import com.residencia.dell.exceptions.CustHistException;
import com.residencia.dell.repositories.CustHistRepository;

@Service
public class CustHistService {

    @Autowired
    public CustHistRepository custHistRepository;

    public CustHist findById(Integer id) {
        return custHistRepository.findById(id).get();
    }

    public List<CustHist> findAll() {
        return custHistRepository.findAll();
    }

    public List<CustHist> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<CustHist> listCustHist = null;
        List<CustHist> listCustHistComPaginacao = null;

        try {
            if (null != pagina && null != qtdRegistros) {
                page = PageRequest.of(pagina, qtdRegistros);
                listCustHistComPaginacao = custHistRepository.findAll(page).getContent();

                return listCustHistComPaginacao;
            } else {
                listCustHist = custHistRepository.findAll();

                return listCustHist;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de custHist ::" + e.getMessage());
        }
    }

    public List<CustHistVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        PageRequest page = null;
        List<CustHist> listCustHist = null;
        List<CustHist> listCustHistComPaginacao = null;
        List<CustHistVO> listCustHistVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listCustHistComPaginacao = custHistRepository.findAll(page).getContent();

                for (CustHist lCustHist : listCustHistComPaginacao) {
                    listCustHistVO.add(convertEntidadeParaVO(lCustHist));
                }

            } else {
                listCustHist = custHistRepository.findAll();

                for (CustHist lCustHist : listCustHist) {
                    listCustHistVO.add(convertEntidadeParaVO(lCustHist));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de custHist ::" + e.getMessage());
        }

        return listCustHistVO;
    }

    private CustHistVO convertEntidadeParaVO(CustHist custHist) {
        CustHistVO custHistVO = new CustHistVO();

        custHistVO.setCustomersVO(custHist.get());
        custHistVO.setProdId(custHist.getOrderDate());
        custHistVO.setOrderId(custHist.getOrderId());

        return custHistVO;
    }

    public long count(){
        return custHistRepository.count();
    }

//    public void save (CustHist custHist){
//        custHistRepository.save(custHist);
//    }

    public CustHist save(CustHist custHist){
        return custHistRepository.save(custHist);
    }

    public void update(CustHist custHist, Integer id) throws CustHistException{
        //Utilizando o Exception
        if(null == id)
            throw new CustHistException("Não foi informado um ID válido.");

        custHistRepository.save(custHist);
    }

    public CustHist update(Integer id, CustHist custHist) {
        CustHist newCustHist = custHistRepository.findById(id).get();
        updateDados(newCustHist, custHist);
        return custHistRepository.save(newCustHist);
    }

    private void updateDados(CustHist newCustHist, CustHist custHist) {
        newCustHist.set(custHist.getOrderDate());
        newCustHist.setCustomers(custHist.getCustomers());
    }

    public void delete(Integer id) {
        custHistRepository.deleteById(id);
    }
}*/