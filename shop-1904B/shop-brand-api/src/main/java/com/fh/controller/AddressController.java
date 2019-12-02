package com.fh.controller;


import com.fh.bean.AddressBean;
import com.fh.service.IAddressService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("address")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("newAddress")
    public ResponseServer newAddress(AddressBean addressBean){
        if(addressBean.getName() != null && !"".equals(addressBean.getName())){
            addressBean.setAddTime(new Date());
            if(addressBean.getId()!=0 && !"".equals(addressBean.getId())){
                addressService.updateAddress(addressBean);
            }else {
                addressService.newAddress(addressBean);
            }
            return ResponseServer.success();
        }
        return ResponseServer.error();
    }

    @PostMapping("queryAddress")
    public ResponseServer queryAddress(){
        List<AddressBean> list=addressService.queryAddress();
        return ResponseServer.success(list);
    }

    @PostMapping("queryAddressById")
    public ResponseServer queryAddressById(Integer id){
        AddressBean addressBean=addressService.queryAddressById(id);
        return ResponseServer.success(addressBean);
    }

    @PostMapping("delAddress")
    public ResponseServer delAddress(Integer id){
        addressService.delAddress(id);
        return ResponseServer.success();
    }

}
