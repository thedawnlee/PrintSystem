package com.dawn.service.impl;

import com.dawn.dao.BonusMapper;
import com.dawn.pojo.Bonus;
import com.dawn.service.IBonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "iBonusService")
public class BonusServiceImpl implements IBonusService {

    @Autowired
    private BonusMapper bonusMapper;

    @Override
    public Bonus getBonus(Integer shopId) {
        return bonusMapper.selectBonusByShopId(shopId);
    }
}
