package com.oj.service.serviceImpl.er;

import com.oj.mapper.er.ExchangeMapper;
import com.oj.service.er.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired(required = false)
    private ExchangeMapper mapper;
    @Override
    public String exchange(String money, String id) {
        long m = Long.valueOf(mapper.checkmoney(id));
        long mm = Long.valueOf(money);
        if(m>=mm)
        {
            return "1";
        }
        else
        {
            return "0";
        }
    }
}
