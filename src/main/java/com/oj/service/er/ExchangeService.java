package com.oj.service.er;

import java.util.List;
import java.util.Map;

public interface ExchangeService {
    public String exchange(String money, String id);

    public List<Map> getMap();
}
