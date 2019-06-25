package com.oj.service.serviceImpl.bt;

import com.oj.mapper.bt.BalanceMapper;
import com.oj.service.bt.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired(required = false)
    private BalanceMapper mapper;
    @Override
    public String balance(String id) {
        String res = mapper.selectMoney(id);
        if(res.equals("0"))
        {
            res = "000";
        }
        String r;
        r = res.substring(0,res.length()-2);
        String x;
        x = res.substring(res.length()-2,res.length());
        res = r+"."+x;
        System.out.println(res);
        return res;
    }
}
