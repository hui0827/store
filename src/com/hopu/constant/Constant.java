package com.hopu.constant;

public interface Constant {
    /**
     *  用户未激活
     */
    int USER_IS_NOT_ACTIVE = 0;
    /**
     *  用户已激活
     */
    int USER_IS_ACTIVE = 1;

    /**
     *  商品热门
     */
    int PRODUCT_IS_HOT = 1;

    /**
     *  商品未下架
     */
    int PRODUCT_IS_NOT_DOWN = 0;

    /**
     * 商品下架
     */
    int PRODUCT_IS_DOWN = 1;

    /**
     * 查询最新和热门的数量
     */
    int PRODUCT_NUM = 9;

    /**
     * 订单状态：未付款
     */
    int ORDER_WEIFUKUAN = 0;

    /**
     * 订单状态：已付款
     */
    int ORDER_YIFUKUAN = 1;

    /**
     * 订单状态：已发货
     */
    int ORDER_YIFAHUO = 2;

    /**
     * 订单状态：已完成
     */
    int ORDER_YIWANCHENG = 3;

}
