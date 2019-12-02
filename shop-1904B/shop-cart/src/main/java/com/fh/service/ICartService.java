package com.fh.service;

import java.util.Map;

public interface ICartService {
    Long addCart(Integer productId, String userPhone);

    Map<String, Object> getCarts(String phone);

    void changeNum(Integer productId, String phone,Integer type);

    void updateCheckStatus(Integer productId, String phone);

    Map<String, Object> getCarts2(String phone);
}
