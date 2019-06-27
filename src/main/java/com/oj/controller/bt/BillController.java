package com.oj.controller.bt;

import com.oj.service.bt.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/billMn")
public class BillController {

    @Autowired
    BillService service;
    //返回ip管理界面
    @RequestMapping("/")
    public String index(ModelMap modelMap, HttpServletRequest request) { return "bt/bill"; }


    @RequestMapping("/getMapList")
    @ResponseBody
    public List<Map> getMapList(HttpServletRequest request)
    {
        String id = request.getSession().getAttribute("user_account").toString();
        return service.getMapList(id);
    }
}
