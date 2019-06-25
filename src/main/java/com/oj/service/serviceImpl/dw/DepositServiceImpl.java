package com.oj.service.serviceImpl.dw;

import com.oj.mapper.dw.DepositMapper;
import com.oj.service.dw.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DepositServiceImpl implements DepositService {
    @Autowired(required = false)
    private DepositMapper mapper;

    @Override
    public boolean deposit(String money, String id) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(new Date());
        String m = mapper.selectMoney(id);
        long mm = Long.valueOf(m) + Long.valueOf(money);
        String key = String.valueOf(mm);
        mapper.addBill(id,time,money);
        return mapper.deposit(key,id);
    }
}
