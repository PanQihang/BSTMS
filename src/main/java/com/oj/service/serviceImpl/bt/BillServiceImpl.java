package com.oj.service.serviceImpl.bt;

import com.oj.mapper.bt.BillMapper;
import com.oj.service.bt.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService {
    @Autowired(required = false)
    private BillMapper mapper;
    @Override
    public List<Map> getMapList(String id) {
        List<Map> list = mapper.getMapList(id);
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).get("AffairType").equals("0"))
            {
                list.get(i).replace("AffairType","存款");
            }
            else if(list.get(i).get("AffairType").equals("1"))
            {
                list.get(i).replace("AffairType","取款");
            }
            else if(list.get(i).get("AffairType").equals("3"))
            {
                list.get(i).replace("AffairType","转入");
            }
            else if(list.get(i).get("AffairType").equals("4"))
            {
                list.get(i).replace("AffairType","转出");
            }

            if(list.get(i).get("TradeBalance").equals("0"))
            {
                list.get(i).replace("TradeBalance","0.00");
            }
            else
            {
                String res = list.get(i).get("TradeBalance").toString();
                String r;
                r = res.substring(0,res.length()-2);
                String x;
                x = res.substring(res.length()-2,res.length());
                res = r+"."+x;
                list.get(i).replace("TradeBalance",res);
            }
        }
        return list;
    }
}
