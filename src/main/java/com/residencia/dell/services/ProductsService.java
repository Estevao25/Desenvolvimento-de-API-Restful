package com.residencia.dell.services;

import com.residencia.dell.entities.Products;
import com.residencia.dell.repositories.ProductsRepository;
import com.residencia.dell.vo.ProductsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    public ProductsRepository productsRepository;

    public Products findById(Integer id){
        return productsRepository.findById(id).get();
    }

    public List<ProductsVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Products> listProducts = null;
        List<Products> listProductsComPaginacao = null;
        List<ProductsVO> listProductsVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listProductsComPaginacao = productsRepository.findAll(page).getContent();

                for (Products lProducts : listProductsComPaginacao) {
                    listProductsVO.add(convertEntidadeParaVO(lProducts));
                }

            } else {
                listProducts = productsRepository.findAll();

                for (Products lProducts : listProducts) {
                    listProductsVO.add(convertEntidadeParaVO(lProducts));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de produtos ::" + e.getMessage());
        }

        return listProductsVO;
    }

    public long count() {
        return productsRepository.count();
    }

    public Products save(Products products){
        if (productsRepository.save(products).getProdId() != null){
            return productsRepository.save(products);
        }else {
            return null;
        }
    }

    public ProductsVO saveVO(ProductsVO productsVO){
        Products novoProduct = convertVOParaEntidade(productsVO);
        productsRepository.save(novoProduct);
        return convertEntidadeParaVO(novoProduct);
    }

    public Products update(Integer id, Products products) {
        Products newProducts = productsRepository.findById(id).get();
        updateDados(newProducts, products);
        return productsRepository.save(newProducts);
    }

    private void updateDados(Products newProducts, Products products) {
        newProducts.setCategory(products.getCategory());
        newProducts.setTitle(products.getTitle());
        newProducts.setActor(products.getActor());
        newProducts.setPrice(products.getPrice());
        newProducts.setSpecial(products.getSpecial());
        newProducts.setCommonProdId(products.getCommonProdId());
    }

    public boolean delete(Integer id) {
        if (id != null) {
            productsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private ProductsVO convertEntidadeParaVO(Products products) {
        ProductsVO productsVO = new ProductsVO();

        productsVO.setProdId(products.getProdId());
        productsVO.setCategory(products.getCategory());
        productsVO.setTitle(products.getTitle());
        productsVO.setActor(products.getActor());
        productsVO.setPrice(products.getPrice());
        productsVO.setSpecial(products.getSpecial());
        productsVO.setCommonProdId(products.getCommonProdId());

        return productsVO;
    }
        private Products convertVOParaEntidade(ProductsVO productsVO) {
        Products products = new Products();

        products.setProdId(productsVO.getProdId());
        products.setCategory(productsVO.getCategory());
        products.setTitle(productsVO.getTitle());
        products.setActor(productsVO.getActor());
        products.setPrice(productsVO.getPrice());
        products.setSpecial(productsVO.getSpecial());
        products.setCommonProdId(productsVO.getCommonProdId());

        return products;
    }


//    public List<Products> findAll(){
//        return productsRepository.findAll();
//    }
//
//    public List<Products> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
//        Pageable page = null;
//        List<Products> listProducts = null;
//        List<Products> listProductsComPaginacao = null;
//
//        try {
//            if (null != pagina && null != qtdRegistros) {
//                page = PageRequest.of(pagina, qtdRegistros);
//                listProductsComPaginacao = productsRepository.findAll(page).getContent();
//
//                return listProductsComPaginacao;
//            } else {
//                listProducts = productsRepository.findAll();
//
//                return listProducts;
//            }
//        } catch (Exception e) {
//            throw new Exception("Não foi possível recuperar a lista de produtos ::" + e.getMessage());
//        }
//    }

//    public Products update(Products products){
//        return productsRepository.save(products);
//    }
}