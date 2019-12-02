package com.fh.controller;

import com.fh.bean.CartBean;
import com.fh.login.LoginAnnotation;
import com.fh.service.ICartService;
import com.fh.utils.response.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/carts")
@CrossOrigin(maxAge = 3600,origins = "http://localhost:8080",exposedHeaders="NOLONGIN")
public class CartController {

    @Autowired
    private ICartService cartService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("addCart")
    @LoginAnnotation
    public ResponseServer addCart(Integer productId, HttpServletRequest request){
        String userPhone= (String) request.getAttribute("phone");
        Long longs = cartService.addCart(productId, userPhone);
        return ResponseServer.success(longs);
    }

    @GetMapping("toCart")
    @LoginAnnotation
    public ResponseServer toCart(){
        return ResponseServer.success();
    }

    @GetMapping("cartShow")
    @LoginAnnotation
    public ResponseServer cartShow(HttpServletRequest request){
        String phone= (String) request.getAttribute("phone");
        Map<String, Object> carts = cartService.getCarts(phone);
        return ResponseServer.success(carts);
    }

    @GetMapping("cartShow2")
    @LoginAnnotation
    public ResponseServer cartShow2(HttpServletRequest request){
        String phone= (String) request.getAttribute("phone");
        Map<String, Object> carts = cartService.getCarts2(phone);
        return ResponseServer.success(carts);
    }

    @LoginAnnotation
    @PostMapping("/updataNum")
    public ResponseServer changeNum(Integer type,Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        cartService.changeNum(productId,phone,type);
        return ResponseServer.success();
    }

    /**
     *修改购物车商品的选中状态
     * @param productId
     * @param request
     * @return
     */
    @LoginAnnotation
    @PostMapping("/changCheck")
    public ResponseServer changChecked(Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        cartService.updateCheckStatus(productId,phone);
        return ResponseServer.success();
    }

    @LoginAnnotation
    @PostMapping("/cartCheck")
    public ResponseServer cartCheck(Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean cart= (CartBean) redisTemplate.opsForHash().get(cartId,productId);
        if(cart.getIsChecked()){
            cart.setIsChecked(false);
        }else {
            cart.setIsChecked(true);
        }
        redisTemplate.opsForHash().put(cartId,productId,cart);
        return ResponseServer.success();
    }

    @LoginAnnotation
    @PostMapping("/test")
    public ResponseServer test(HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        Long cartCount=null;
        if(cartId != null && !"".equals(cartId)){
            cartCount=redisTemplate.opsForHash().size(cartId);
            return ResponseServer.success(cartCount);
        }
        return ResponseServer.success(cartCount);
    }

    @LoginAnnotation
    @PostMapping("/remProduct")
    public ResponseServer remProduct(Integer productId,HttpServletRequest request){
        String  phone= (String) request.getAttribute("phone");
        String cartId = (String) redisTemplate.opsForValue().get("cartid_" + phone);
        CartBean cartBean = (CartBean) redisTemplate.opsForHash().get(cartId, productId);
        redisTemplate.opsForHash().delete(cartId,productId);
        return ResponseServer.success();
    }
}
