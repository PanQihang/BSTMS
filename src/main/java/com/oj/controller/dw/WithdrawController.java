package com.oj.controller.dw;

import com.oj.service.dw.WithdrawService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/withdrawMn")
public class WithdrawController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WithdrawService service;
    @RequestMapping("/")
    //返回login.html页面
    public String index(HttpServletRequest request) {
        return "dw/withdrawal";
    }

    @RequestMapping("/withdraw")
    @ResponseBody
    public boolean withdraw(HttpServletRequest request) {
        String id = request.getSession().getAttribute("user_account").toString();
        String money = request.getParameter("money");
        return service.withdraw(money,id);
    }
}
