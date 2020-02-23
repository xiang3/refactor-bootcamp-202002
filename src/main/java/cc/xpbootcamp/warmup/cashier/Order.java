package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

public class Order {
    private Date purchaseTime;
    private List<OrderSummaryInfo> orderSummaryInfoList;

    public Order(List<OrderSummaryInfo> orderSummaryInfoList, Date purchaseTime) {
        this.purchaseTime = purchaseTime;
        this.orderSummaryInfoList = orderSummaryInfoList;
    }

    public List<OrderSummaryInfo> getOrderInfos() {
        return orderSummaryInfoList;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public double getTotalSaleTax() {
        // calculate sales tax @ rate of 10%
        return getOrderInfos().stream().mapToDouble(orderSummaryInfo -> orderSummaryInfo.totalAmount()*.10).sum();
    }

    public double getTotalAmount() {
        // calculate total amount of lineItem = price * quantity + 10 % sales tax
        return getOrderInfos().stream().mapToDouble(orderSummaryInfo -> orderSummaryInfo.totalAmount()+ orderSummaryInfo.totalAmount()*.10).sum();
    }

    public double getWednesdayActivityPrice() {
        // wednesday activity price = total price * 98%
        return getTotalAmount()*.98;
    }
}
