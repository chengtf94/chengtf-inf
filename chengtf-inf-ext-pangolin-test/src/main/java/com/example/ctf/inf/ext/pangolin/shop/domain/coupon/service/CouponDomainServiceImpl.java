package com.example.ctf.inf.ext.pangolin.shop.domain.coupon.service;

import com.example.ctf.inf.ext.pangolin.shop.domain.coupon.model.CouponModel;
import com.example.ctf.inf.ext.pangolin.shop.domain.coupon.service.req.CouponQueryReq;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: TODO
 * @author: chengtf
 * @date: 2024/3/10 23:42
 */
@Service
public class CouponDomainServiceImpl implements CouponDomainService {
    @Override
    public List<CouponModel> listCouponModels(CouponQueryReq couponQueryReq) {
        return null;
    }

    @Override
    public List<CouponModel> filterCouponModels(CouponQueryReq couponQueryReq, List<CouponModel> couponModels) {
        return null;
    }

    @Override
    public List<CouponModel> sortCouponModels(CouponQueryReq couponQueryReq, List<CouponModel> couponModels) {
        return null;
    }
}
