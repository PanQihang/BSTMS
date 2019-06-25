package com.oj.controller.dw;

import com.oj.service.dw.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/depositMn")
public class DepositController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DepositService depositservice;

    @RequestMapping("/")
    //返回login.html页面
    public String index(HttpServletRequest request) {
        return "dw/deposit";
    }

    @RequestMapping("/deposit")
    @ResponseBody
    public boolean deposit(HttpServletRequest request)
    {
        String id = request.getSession().getAttribute("user_account").toString();
        String money = request.getParameter("money");
        return depositservice.deposit(money,id);
    }
}
