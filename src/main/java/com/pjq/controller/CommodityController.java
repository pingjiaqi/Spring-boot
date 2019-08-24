package com.pjq.controller;


import com.pjq.pojo.Commodity;
import com.pjq.pojo.Result;
import com.pjq.service.CommodityService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(path = "/home")
public class CommodityController {

    @Resource
    private CommodityService service;

    @Autowired
    CommodityService commodityService;

    @RequestMapping(path = "/")
    @ResponseBody
    public Result showAllCommodity(HttpServletRequest request, HttpServletResponse response){
        Result result=new Result();
        result.setResult(commodityService.showAllCommodity());
        result.setMessage("success");
        result.setCode("200");
        return result;
    }
}
