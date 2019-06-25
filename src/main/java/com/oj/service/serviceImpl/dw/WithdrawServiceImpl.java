package com.oj.service.serviceImpl.dw;

import com.oj.mapper.dw.WithdrawMapper;
import com.oj.service.dw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired(required = false)
    private WithdrawMapper mapper;
    @Override
    public boolean withdraw(String money, String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(new Date());
        String m = mapper.selectMoney(id);
        long qumoney = Long.valueOf(money);
        long mymoney = Long.valueOf(m);
        if(mymoney>=qumoney)
        {
            long mm = mymoney - qumoney;
            String key = String.valueOf(mm);
            mapper.addBill(id,time,money);
            return mapper.withdraw(key,id);
        }
        else
        {
            return false;
        }
    }
}
