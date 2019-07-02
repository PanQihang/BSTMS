package com.oj.controller.bt;

import com.oj.service.bt.BalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/balanceMn")
public class BalanceController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BalanceService service;
    @RequestMapping("/")
    //返回login.html页面
    public String index(HttpServletRequest request) {
        return "bt/balance.html";
    }

    @RequestMapping("/balance")
    @ResponseBody
    public String balance(HttpServletRequest request)
    {
        String id = request.getSession().getAttribute("user_account").toString();
        String res = service.balance(id);
        return res;
    }

    @RequestMapping("/balance1")
    @ResponseBody
    public String balance1(HttpServletRequest request)
    {
        String id = request.getSession().getAttribute("user_account").toString();
        String res = service.balance(id);
        return res;
    }
}
