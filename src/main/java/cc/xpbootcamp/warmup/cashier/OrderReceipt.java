package cc.xpbootcamp.warmup.cashier;

import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    private double getTotalSaleTax() {
        // calculate sales tax @ rate of 10%
        return order.getLineItems().stream().mapToDouble(orderInfo->orderInfo.totalAmount()*.10).sum();
    }

    private double getTotalAmount() {
        // calculate total amount of lineItem = price * quantity + 10 % sales tax
        return order.getLineItems().stream().mapToDouble(orderInfo->orderInfo.totalAmount()+orderInfo.totalAmount()*.10).sum();
    }

    private String printOrderInfo() {
        return order.getLineItems().stream().map(orderInfo->{
            StringBuilder orderInfoBuffer = new StringBuilder();
            return orderInfoBuffer.append(orderInfo.getDescription()).append('\t')
                    .append(orderInfo.getPrice()).append('\t')
                    .append(orderInfo.getQuantity()).append('\t')
                    .append(orderInfo.totalAmount()).append('\n').toString();
        }).collect(Collectors.joining());
    }
    public String printReceipt() {
        StringBuilder receiptBuffer = new StringBuilder();
        return receiptBuffer.append("======Printing Orders======\n")
                .append(order.getCustomerName())
                .append(order.getCustomerAddress())
                .append(printOrderInfo())
                .append("Sales Tax").append('\t').append(getTotalSaleTax())
                .append("Total Amount").append('\t').append(getTotalAmount()).toString();
    }
}