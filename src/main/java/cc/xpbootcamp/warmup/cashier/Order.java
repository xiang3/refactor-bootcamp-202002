package cc.xpbootcamp.warmup.cashier;

import java.util.Date;
import java.util.List;

public class Order {
    private Date purchaseTime;
    private List<OrderItem> orderItemList;

    public Order(List<OrderItem> orderItemList, Date purchaseTime) {
        this.purchaseTime = purchaseTime;
        this.orderItemList = orderItemList;
    }

    public List<OrderItem> getOrderInfos() {
        return orderItemList;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public double calculateTotalSaleTax() {
        // calculate sales tax @ rate of 10%
        return getOrderInfos().stream().mapToDouble(orderItem -> orderItem.totalAmount()*.10).sum();
    }

    public double calculateTotalAmount() {
        // calculate total amount of lineItem = price * quantity + 10 % sales tax
        return getOrderInfos().stream().mapToDouble(orderItem -> orderItem.totalAmount()+ orderItem.totalAmount()*.10).sum();
    }

    public double calculateWednesdayActivityPrice() {
        // wednesday activity price = total price * 98%
        return calculateTotalAmount()*.98;
    }
}
