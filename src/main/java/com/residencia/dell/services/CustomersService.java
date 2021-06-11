package com.residencia.dell.services;

import com.residencia.dell.entities.Customers;
import com.residencia.dell.entities.Orders;
import com.residencia.dell.repositories.CustomersRepository;
import com.residencia.dell.vo.CustomersVO;
import com.residencia.dell.vo.OrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomersService {

    @Autowired
    public CustomersRepository customersRepository;


    public Customers findById(Integer id) {
        return customersRepository.findById(id).get();
    }

    public List<Customers> findAll(){
        return customersRepository.findAll();
    }

    public List<Customers> findAll(Integer pagina, Integer qtdRegistros) throws Exception {
        Pageable page = null;
        List<Customers> listCustomers = null;
        List<Customers> listCustomersComPaginacao = null;

        try {
            if (null != pagina && null != qtdRegistros) {
                page = PageRequest.of(pagina, qtdRegistros);
                listCustomersComPaginacao = customersRepository.findAll(page).getContent();

                return listCustomersComPaginacao;
            } else {
                listCustomers = customersRepository.findAll();

                return listCustomers;
            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de customers ::" + e.getMessage());
        }
    }

    public List<CustomersVO> findAllVO(Integer pagina, Integer qtdRegistros) throws Exception {
        PageRequest page = null;
        List<Customers> listCustomers = null;
        List<Customers> listCustomersComPaginacao = null;
        List<CustomersVO> listCustomersVO = new ArrayList<>();

        try {
            if (null != pagina && null != qtdRegistros) {

                page = PageRequest.of(pagina, qtdRegistros);
                listCustomersComPaginacao = customersRepository.findAll(page).getContent();

                for (Customers lCustomers : listCustomersComPaginacao) {
                    listCustomersVO.add(convertEntidadeParaVO(lCustomers));
                }

            } else {
                listCustomers = customersRepository.findAll();

                for (Customers lCustomers : listCustomers) {
                    listCustomersVO.add(convertEntidadeParaVO(lCustomers));
                }

            }
        } catch (Exception e) {
            throw new Exception("Não foi possível recuperar a lista de customers ::" + e.getMessage());
        }

        return listCustomersVO;
    }

    private CustomersVO convertEntidadeParaVO(Customers customers) {
        CustomersVO customersVO = new CustomersVO();

        customersVO.setCustomerId(customers.getCustomerid());
        customersVO.setFirstName(customers.getFirstname());
        customersVO.setLastName(customers.getLastname());
        customersVO.setAddress1(customers.getAddress1());
        customersVO.setAddress2(customers.getAddress2());
        customersVO.setCity(customers.getCity());
        customersVO.setState(customers.getState());
        customersVO.setZip(customers.getZip());
        customersVO.setCountry(customers.getCountry());
        customersVO.setRegion(customers.getRegion());
        customersVO.setEmail(customers.getEmail());
        customersVO.setPhone(customers.getPhone());
        customersVO.setCreditCardType(customers.getCreditcardtype());
        customersVO.setCreditCard(customers.getCreditcard());
        customersVO.setCreditCardExpiration(customers.getCreditcardexpiration());
        customersVO.setUsername(customers.getUsername());
        customersVO.setPassword(customers.getPassword());
        customersVO.setAge(customers.getAge());
        customersVO.setIncome(customers.getIncome());
        customersVO.setGender(customers.getGender());

        return customersVO;
    }

    public long count(){
        return customersRepository.count();
    }

    public Customers save(Customers customer){
        if(customersRepository.save(customer).getCustomerid() != null){
            return customersRepository.save(customer);
        } else{
            return null;
        }
    }

//    public Customers update(Customers customer){
//        return customersRepository.save(customer);
//    }

    public Customers update(Integer id, Customers customers) {
        Customers newCustomers = customersRepository.findById(id).get();
        updateDados(newCustomers, customers);
        return customersRepository.save(newCustomers);
    }

    private void updateDados(Customers newCustomers, Customers customers) {
        newCustomers.setCustomerid(customers.getCustomerid());
        newCustomers.setFirstname(customers.getFirstname());
        newCustomers.setLastname(customers.getLastname());
        newCustomers.setAddress1(customers.getAddress1());
        newCustomers.setAddress2(customers.getAddress2());
        newCustomers.setCity(customers.getCity());
        newCustomers.setState(customers.getState());
        newCustomers.setZip(customers.getZip());
        newCustomers.setCountry(customers.getCountry());
        newCustomers.setRegion(customers.getRegion());
        newCustomers.setEmail(customers.getEmail());
        newCustomers.setPhone(customers.getPhone());
        newCustomers.setCreditcardtype(customers.getCreditcardtype());
        newCustomers.setCreditcard(customers.getCreditcard());
        newCustomers.setCreditcardexpiration(customers.getCreditcardexpiration());
        newCustomers.setUsername(customers.getUsername());
        newCustomers.setPassword(customers.getPassword());
        newCustomers.setAge(customers.getAge());
        newCustomers.setIncome(customers.getIncome());
        newCustomers.setGender(customers.getGender());
    }

    public boolean delete(Integer id){
        if(id != null){
            customersRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}