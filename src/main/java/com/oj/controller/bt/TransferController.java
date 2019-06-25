package com.oj.controller.bt;

import com.oj.service.bt.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/transferMn")
public class TransferController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransferService service;
    @RequestMapping("/")
    //返回login.html页面
    public String index(HttpServletRequest request) {
        return "bt/transfer";
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public String transfer(HttpServletRequest request)
    {
        String id = request.getSession().getAttribute("user_account").toString();
        String account = request.getParameter("account");
        String money = request.getParameter("money");
        return service.transfer(id,account,money);
    }
}
