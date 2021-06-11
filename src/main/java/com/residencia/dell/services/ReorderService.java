package com.residencia.dell.services;

import com.residencia.dell.entities.Orderlines;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.entities.Reorder;
import com.residencia.dell.repositories.ReorderRepository;
import com.residencia.dell.vo.OrderlinesVO;
import com.residencia.dell.vo.OrdersVO;
import com.residencia.dell.vo.ReorderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReorderService {

    @Autowired
    public ReorderRepository reorderRepository;


    public Reorder findById(Integer id) {
        return reorderRepository.findById(id).get();
    }

    public List<Reorder> findAll(){
        return reorderRepository.findAll();
    }

    public List<Reorder> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Reorder> listReorder = null;
        List<Reorder> listReorderComPaginacao = null;

        try {
            if (null != pagina && null != qtdRegistros) {
                page = PageRequest.of(pagina, qtdRegistros);
                listReorderComPaginacao = reorderRepository.findAll(page).getContent();

                return listReorderComPaginacao;
            } else {
                listReorder = reorderRepository.findAll();

                return listReorder;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de reorder ::" + e.getMessage());
        }
    }

    public List<ReorderVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        PageRequest page = null;
        List<Reorder> listReorder = null;
        List<Reorder> listReorderComPaginacao = null;
        List<ReorderVO> listReorderVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listReorderComPaginacao = reorderRepository.findAll(page).getContent();

                for (Reorder lReorder : listReorderComPaginacao) {
                    listReorderVO.add(convertEntidadeParaVO(lReorder));
                }

            } else {
                listReorder = reorderRepository.findAll();

                for (Reorder lReorder : listReorder) {
                    listReorderVO.add(convertEntidadeParaVO(lReorder));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de reorder ::" + e.getMessage());
        }

        return listReorderVO;
    }

    private ReorderVO convertEntidadeParaVO(Reorder reorder) {
        ReorderVO reorderVO = new ReorderVO();

        reorderVO.setProdId(reorder.getProdId());
        reorderVO.setDateLow(reorder.getDateLow());
        reorderVO.setQuanLow(reorder.getQuanLow());
        reorderVO.setDateReordered(reorder.getDateReordered());
        reorderVO.setQuanReordered(reorder.getQuanReordered());
        reorderVO.setDateExpected(reorder.getDateExpected());

        return reorderVO;
    }

    public long count(){
        return reorderRepository.count();
    }

    public Reorder save(Reorder reorder){
        if(reorderRepository.save(reorder).getProdId() != null){
            return reorderRepository.save(reorder);
        } else{
            return null;
        }
    }

    public Reorder update(Reorder reorder){
        return reorderRepository.save(reorder);
    }

    public Reorder update(Integer id, Reorder reorder) {
        Reorder newReorder = reorderRepository.findById(id).get();
        updateDados(newReorder, reorder);
        return reorderRepository.save(newReorder);
    }

    private void updateDados(Reorder newReorder, Reorder reorder) {
        newReorder.setDateLow(reorder.getDateLow());
        newReorder.setQuanLow(reorder.getQuanLow());
        newReorder.setDateReordered(reorder.getDateReordered());
        newReorder.setQuanReordered(reorder.getQuanReordered());
        newReorder.setDateExpected(reorder.getDateExpected());
    }

    public boolean delete(Integer id){
        if(id != null){
            reorderRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}