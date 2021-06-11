package com.residencia.dell.services;

import com.residencia.dell.entities.Inventory;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.repositories.InventoryRepository;
import com.residencia.dell.vo.InventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    public InventoryRepository inventoryRepository;


    public Inventory findById(Integer id) {
        return inventoryRepository.findById(id).get();
    }

    public List<Inventory> findAll(){
        return inventoryRepository.findAll();
    }

    public List<Inventory> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Inventory> listInventory = null;
        List<Inventory> listInventoryComPaginacao = null;

        try {
            if (null != pagina && null != qtdRegistros) {
                page = PageRequest.of(pagina, qtdRegistros);
                listInventoryComPaginacao = inventoryRepository.findAll(page).getContent();

                return listInventoryComPaginacao;
            } else {
                listInventory = inventoryRepository.findAll();

                return listInventory;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de inventários ::" + e.getMessage());
        }
    }

    public List<InventoryVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        PageRequest page = null;
        List<Inventory> listInventory = null;
        List<Inventory> listInventoryComPaginacao = null;
        List<InventoryVO> listInventoryVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listInventoryComPaginacao = inventoryRepository.findAll(page).getContent();

                for (Inventory lInventory : listInventoryComPaginacao) {
                    listInventoryVO.add(convertEntidadeParaVO(lInventory));
                }

            } else {
                listInventory = inventoryRepository.findAll();

                for (Inventory lInventory : listInventory) {
                    listInventoryVO.add(convertEntidadeParaVO(lInventory));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de inventários ::" + e.getMessage());
        }

        return listInventoryVO;
    }

    private InventoryVO convertEntidadeParaVO(Inventory inventory) {
        InventoryVO inventoryVO = new InventoryVO();

        inventoryVO.setProdId(inventory.getProdId());
        inventoryVO.setQuanInStock(inventory.getQuanInStock());
        inventoryVO.setSales(inventory.getSales());

        return inventoryVO;
    }

    public long count(){
        return inventoryRepository.count();
    }

    public Inventory save(Inventory inventory){
        if(inventoryRepository.save(inventory).getProdId() != null){
            return inventoryRepository.save(inventory);
        } else{
            return null;
        }
    }

    public Inventory update(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    public Inventory update(Integer id, Inventory inventory) {
        Inventory newInventory = inventoryRepository.findById(id).get();
        updateDados(newInventory, inventory);
        return inventoryRepository.save(newInventory);
    }

    private void updateDados(Inventory newInventory, Inventory inventory) {
        newInventory.setProdId(inventory.getProdId());
        newInventory.setQuanInStock(inventory.getQuanInStock());
        newInventory.setSales(inventory.getSales());
    }

    public boolean delete(Integer id){
        if(id != null){
            inventoryRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}