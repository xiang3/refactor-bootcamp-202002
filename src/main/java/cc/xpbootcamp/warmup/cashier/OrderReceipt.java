package cc.xpbootcamp.warmup.cashier;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    public static final String SLOGAN = "老王超市，值得信赖";
    public static final String SALES_TAX_PRINT_FORMAT = "税额: %.2f\n";
    public static final String TOTAL_AMOUNT_PRINT_FORMAT = "总价: %.2f\n";
    public static final String DISCOUNT_PRINT_FORMAT = "折扣: %.2f\n";
    public static final String PURCHASE_TIME_PRINT_FORMAT = "yyyy年M月d日，EEEE";
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    private double getTotalSaleTax() {
        // calculate sales tax @ rate of 10%
        return order.getOrderInfos().stream().mapToDouble(orderInfo->orderInfo.totalAmount()*.10).sum();
    }

    private double getTotalAmount() {
        // calculate total amount of lineItem = price * quantity + 10 % sales tax
        return order.getOrderInfos().stream().mapToDouble(orderInfo->orderInfo.totalAmount()+orderInfo.totalAmount()*.10).sum();
    }

    private double getWednesdayActivityPrice() {
        return getTotalAmount()*.98;
    }
    private String printPurchaseTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PURCHASE_TIME_PRINT_FORMAT, Locale.CHINESE);
        return dateFormat.format(order.getPurchaseTime());
    }

    private String printTotalSaleTax() {
        return String.format(SALES_TAX_PRINT_FORMAT, getTotalSaleTax());
    }

    private String printTotalAmout() {
        return String.format(TOTAL_AMOUNT_PRINT_FORMAT, getTotalAmount());
    }

    private String printDiscount() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(order.getPurchaseTime());
        return instance.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY ?
                String.format(DISCOUNT_PRINT_FORMAT, getWednesdayActivityPrice()) : "";
    }

    private String printPriceInfo() {
       StringBuilder priceBuffer = new StringBuilder();
       return priceBuffer.append(printTotalSaleTax()).append(printDiscount()).append(printTotalAmout()).toString();
    }

    private String printOrderInfo() {
        return order.getOrderInfos().stream().map(orderInfo->{
            StringBuilder orderInfoBuffer = new StringBuilder();
            return orderInfoBuffer.append(orderInfo.getDescription()).append(", ")
                    .append(String.format("%.2f",orderInfo.getPrice())).append(" x ")
                    .append(orderInfo.getQuantity()).append(", ")
                    .append(String.format("%.2f",orderInfo.totalAmount())).append('\n').toString();
        }).collect(Collectors.joining());
    }
    public String printReceipt() {
        StringBuilder receiptBuffer = new StringBuilder();
        return receiptBuffer.append(String.format("=====%s======\n\n", SLOGAN))
                .append(printPurchaseTime()).append("\n\n")
                .append(printOrderInfo())
                .append("-----------------------------------\n")
                .append(printPriceInfo()).toString();
    }
}