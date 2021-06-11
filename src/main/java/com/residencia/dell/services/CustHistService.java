package com.residencia.dell.services;

import java.util.ArrayList;
import java.util.List;

import com.residencia.dell.entities.CustHist;
import com.residencia.dell.vo.CustHistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.residencia.dell.exceptions.CustHistException;
import com.residencia.dell.repositories.CustHistRepository;

@Service
public class CustHistService {

    @Autowired
    public CustHistRepository custHistRepository;

    public CustHist findById(Integer id) {
        return custHistRepository.findById(id).get();
    }

    public List<CustHistVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
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

    public long count(){
        return custHistRepository.count();
    }

    public CustHistVO saveVO(CustHistVO custHistVO){
        CustHist novaCustHist = convertVOParaEntidade(custHistVO);
        custHistRepository.save(novaCustHist);
        return convertEntidadeParaVO(novaCustHist);
    }

    public CustHist update(Integer id, CustHist custHist) throws CustHistException{
        if(null == id) {
            throw new CustHistException("Não foi informado um ID válido.");
        }else {
            CustHist newCustHist = custHistRepository.findById(id).get();
            updateDados(newCustHist, custHist);
            return custHistRepository.save(newCustHist);
        }
    }

    private void updateDados(CustHist newCustHist, CustHist custHist) {
        newCustHist.setProdId(custHist.getProdId());
        newCustHist.setOrderid(custHist.getOrderid());
    }

    public boolean delete(Integer id) {
        if(id != null){
            custHistRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    private CustHistVO convertEntidadeParaVO(CustHist custHist) {
        CustHistVO custHistVO = new CustHistVO();

        custHistVO.setCustomersid(custHist.getCustomerid());
        custHistVO.setProdId(custHist.getProdId());
        custHistVO.setOrderId(custHist.getOrderid());

        return custHistVO;
    }

    private CustHist convertVOParaEntidade(CustHistVO custHistVO) {
        CustHist custHist = new CustHist();

        custHist.setCustomerid(custHistVO.getCustomersid());
        custHist.setProdId(custHistVO.getProdId());
        custHist.setOrderid(custHistVO.getOrderId());

        return custHist;
    }

//    public List<CustHist> findAll() {
//        return custHistRepository.findAll();
//    }
//
//    public List<CustHist> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
//        Pageable page = null;
//        List<CustHist> listCustHist = null;
//        List<CustHist> listCustHistComPaginacao = null;
//
//        try {
//            if (null != pagina && null != qtdRegistros) {
//                page = PageRequest.of(pagina, qtdRegistros);
//                listCustHistComPaginacao = custHistRepository.findAll(page).getContent();
//
//                return listCustHistComPaginacao;
//            } else {
//                listCustHist = custHistRepository.findAll();
//
//                return listCustHist;
//            }
//        } catch (Exception e) {
//            throw new Exception("Não foi possível recuperar a lista de custHist ::" + e.getMessage());
//        }
//    }
//    public void update(CustHist custHist, Integer id) throws CustHistException{
//    //Utilizando o Exception
//    if(null == id)
//        throw new CustHistException("Não foi informado um ID válido.");
//
//    custHistRepository.save(custHist);
//    }
//
//    public CustHist update(Integer id, CustHist custHist) {
//        CustHist newCustHist = custHistRepository.findById(id).get();
//        updateDados(newCustHist, custHist);
//        return custHistRepository.save(newCustHist);
//    }
//    public void save (CustHist custHist){
//        custHistRepository.save(custHist);
//    }
}