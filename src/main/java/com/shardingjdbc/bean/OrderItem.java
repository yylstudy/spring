package com.shardingjdbc.bean;

/**
 * @Author: yyl
 * @Date: 2018/11/14 11:30
 */
public class OrderItem {
    private Long itemId;
    private Long orderId;
    private String itemDesc;
    private Long userId;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", itemDesc='" + itemDesc + '\'' +
                ", userId=" + userId +
                '}';
    }
}
