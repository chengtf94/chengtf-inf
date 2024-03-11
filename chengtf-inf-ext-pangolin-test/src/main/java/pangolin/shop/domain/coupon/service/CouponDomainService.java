package pangolin.shop.domain.coupon.service;

import pangolin.shop.domain.coupon.model.CouponModel;
import pangolin.shop.domain.coupon.service.req.CouponQueryReq;

import java.util.List;

/**
 * @description: 券领域服务
 * @author: chengtf
 * @date: 2024/3/10 23:39
 */
public interface CouponDomainService {

    /**
     * 券查询
     * @param couponQueryReq
     * @return
     */
    List<CouponModel> listCouponModels(CouponQueryReq couponQueryReq);

    /**
     * 券过滤
     * @param couponQueryReq
     * @return
     */
    List<CouponModel> filterCouponModels(CouponQueryReq couponQueryReq, List<CouponModel> couponModels);

    /**
     * 券排序
     * @param couponQueryReq
     * @return
     */
    List<CouponModel> sortCouponModels(CouponQueryReq couponQueryReq, List<CouponModel> couponModels);

}
