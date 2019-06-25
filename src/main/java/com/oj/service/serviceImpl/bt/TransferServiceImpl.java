package com.oj.service.serviceImpl.bt;

import com.oj.mapper.bt.TransferMapper;
import com.oj.service.bt.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired(required = false)
    private TransferMapper mapper;

    @Transactional
    @Override
    public String transfer(String id, String account, String money) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(new Date());
        String checkaccount = mapper.checkAccount(account);
        if(checkaccount!=null)
        {
            String checkmoney = mapper.checkMoney(id);
            long cm = Long.valueOf(checkmoney);
            long m = Long.valueOf(money);
            if(cm>=m)
            {
                long mymoney = cm-m;
                long amoney = Long.valueOf(mapper.checkMoney(account))+m;

                mapper.addBill3(id,time,money);
                mapper.addBill4(account,time,money);
                mapper.transfer(String.valueOf(mymoney),id);
                mapper.transfer(String.valueOf(amoney),account);
                return "0";
            }
            else
            {
                return "2";//返回2余额不足
            }
        }
        else
        {
            return "1";//返回1账号不存在
        }
    }


}
