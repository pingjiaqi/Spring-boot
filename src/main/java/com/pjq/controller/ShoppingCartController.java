package com.pjq.controller;


import com.pjq.pojo.Course;
import com.pjq.pojo.Result;
import com.pjq.service.CourseService;
import com.pjq.service.ShoppingCartService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@Scope("prototype")
@RequestMapping(path = "/api/shoppingcart")
public class ShoppingCartController {

    @Resource
    private CourseService courseService;
    @Resource
    private ShoppingCartService shoppingCartService;


    @RequestMapping(path = "/addtoshoppingCart",method = RequestMethod.POST)
    public Result addToShoppingCart(String product_name, HttpSession httpSession){
        Result result=new Result();
        String username=(String)httpSession.getAttribute("username");
        Course course=courseService.findDetailCourse(product_name);
        if(course==null)
        {
            result.setCode("200");
            result.setMessage("null");
            return result;
        }
        double price = course.getPrice();
        shoppingCartService.addToShoppingCart(username, product_name, price);
        result.setCode("200");
        result.setMessage("success");
        return result;
    }

    @RequestMapping(path = "/showshoppingcart", method = RequestMethod.GET)
    public Result showMyShoppingCart(HttpSession httpSession){
        String username=(String)httpSession.getAttribute("username");
        Result result=new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(shoppingCartService.showShoppingCart(username));
        return result;
    }

    @RequestMapping(path = "/deleteproduct",method = RequestMethod.POST)
    public Result cutShoppingCart(String product_name,HttpSession httpSession){
        String username=httpSession.getAttribute("username").toString();
        shoppingCartService.cutProduct_name(username,product_name);
        Result result=new Result();
        result.setCode("200");
        result.setMessage("success");
        return result;
    }


}
