package com.fh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.bean.CartBean;
import com.fh.httpclient.HttpConnection;
import com.fh.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cartService")
public class CartServiceImpl implements ICartService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Long addCart(Integer productId, String userPhone) {
        //获取购物车id
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + userPhone);
        //根据商品id查询商品信息
        String url = "http://localhost:9092/productSearch/" + productId;
        String result = HttpConnection.doGet(url);
        JSONObject jsonObject= JSON.parseObject(result);
        System.out.println(jsonObject.get("data"));
        JSONObject productData=JSON.parseObject(jsonObject.get("data").toString());

        //讲数据加入购物车实体bean中
        CartBean cartBean=new CartBean();
        cartBean.setProductId(productData.getInteger("id"));
        cartBean.setProductName(productData.getString("name"));
        cartBean.setMainImg(productData.getString("mainImg"));
        cartBean.setPrice(productData.getBigDecimal("price"));
        //判断商品是否存在购物车
        if(redisTemplate.opsForHash().hasKey(cartId,productId)){
            CartBean cart= (CartBean) redisTemplate.opsForHash().get(cartId,productId);
            cartBean.setCount(cart.getCount()+1);
        }else{
            cartBean.setCount(1);
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        BigDecimal count = new BigDecimal(cartBean.getCount());
        BigDecimal subtotal = bigDecimal.add(cartBean.getPrice()).multiply(count);
        cartBean.setSubtotal(subtotal);
        cartBean.setIsChecked(true);
        //加入到redis
        redisTemplate.opsForHash().put(cartId,productId,cartBean);
        Long cartCount=redisTemplate.opsForHash().size(cartId);
        return cartCount;
    }

    @Override
    public Map<String, Object> getCarts(String phone){
        //获取购物车的id
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        //取出购物车数据
        List<CartBean> cartList =redisTemplate.opsForHash().values(cartId);
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        for (CartBean cart:cartList){
            if(cart.getIsChecked()){
                bigDecimal = bigDecimal.add(cart.getSubtotal());
            }
        }
        Map<String, Object> map=new HashMap<>();
        map.put("cartList",cartList);
        map.put("total",bigDecimal);
        return map;
    }

    @Override
    public void changeNum(Integer productId, String phone,Integer type) {
        //获取购物车的ID
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(cartId, productId);
        if(type ==1){
            cartBean.setCount(cartBean.getCount()+1);
        }else if (type == 2 && cartBean.getCount() > 1 ){
            cartBean.setCount(cartBean.getCount()-1);
        }
        cartBean.setSubtotal(cartBean.getPrice().multiply(new BigDecimal(cartBean.getCount())));
        redisTemplate.opsForHash().put(cartId, productId,cartBean);
    }

    @Override
    public void updateCheckStatus(Integer productId, String phone) {
        //获取购物车的ID
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(cartId, productId);
        if(cartBean.getIsChecked()){
            cartBean.setIsChecked(false);
        }else {
            cartBean.setIsChecked(true);
        }
        redisTemplate.opsForHash().put(cartId, productId,cartBean);
    }


}
