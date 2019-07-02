package com.oj.controller.er;

import com.oj.service.er.ExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/exchangeMn")
public class ExchangeController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExchangeService service;
    @RequestMapping("/")
    //返回login.html页面
    public String index(HttpServletRequest request) {
        return "er/exchange";
    }

    @RequestMapping("/exchange")
    @ResponseBody
    public String exchange(HttpServletRequest request)
    {
        String id = request.getSession().getAttribute("user_account").toString();
        String money = request.getParameter("money");
        return service.exchange(money,id);
    }

    @RequestMapping("/getMap")
    @ResponseBody
    public List<Map> getMap(HttpServletRequest request)
    {
        List<Map> list = service.getMap();
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
        return service.getMap();
    }
}
