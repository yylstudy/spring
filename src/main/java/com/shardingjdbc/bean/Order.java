package com.shardingjdbc.bean;

import java.util.List;

/**
 * @Author: yyl
 * @Date: 2018/11/1 20:37
 */
public class Order {
    private Long orderId;
    private Long userId;
    private String orderName;
    private List<OrderItem> itemList;
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderName='" + orderName + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
