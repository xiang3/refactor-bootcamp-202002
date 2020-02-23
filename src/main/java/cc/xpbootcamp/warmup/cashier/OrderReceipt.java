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
    private static final String SLOGAN = "=====老王超市，值得信赖======\n";

    private static final String SALES_TAX_PRINT_FORMAT = "税额: %.2f\n";

    private static final String TOTAL_AMOUNT_PRINT_FORMAT = "总价: %.2f\n";

    private static final String DISCOUNT_PRINT_FORMAT = "折扣: %.2f\n";

    private static final String ORDER_ITEM_PRINT_FORMAT = "%s, %.2f x %s, %.2f\n";

    private static final String PURCHASE_TIME_PRINT_FORMAT = "yyyy年M月d日，EEEE";

    private static final String BLANK_LINE = "\n";

    private static final String TRANSVERSE_LINE = "-----------------------------------\n";

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder receiptBuffer = new StringBuilder();
        return receiptBuffer.append( SLOGAN)
                .append(printPurchaseTime())
                .append(BLANK_LINE)
                .append(printOrderItems())
                .append(TRANSVERSE_LINE)
                .append(printPriceInfo()).toString();
    }

    private String printPurchaseTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PURCHASE_TIME_PRINT_FORMAT, Locale.CHINESE);
        return dateFormat.format(order.getPurchaseTime());
    }

    private String printTotalSaleTax() {
        return String.format(SALES_TAX_PRINT_FORMAT, order.getTotalSaleTax());
    }

    private String printTotalAmount() {
        return String.format(TOTAL_AMOUNT_PRINT_FORMAT, order.getTotalAmount());
    }

    private String printDiscount() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(order.getPurchaseTime());
        return instance.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY ?
                String.format(DISCOUNT_PRINT_FORMAT, order.getWednesdayActivityPrice()) : "";
    }

    private String printPriceInfo() {
       StringBuilder priceBuffer = new StringBuilder();
       return priceBuffer.append(printTotalSaleTax()).append(printDiscount()).append(printTotalAmount()).toString();
    }

    private String printOrderItems() {
        return order.getOrderInfos().stream().map(orderInfo->
            String.format(ORDER_ITEM_PRINT_FORMAT,
                    orderInfo.getDescription(),
                    orderInfo.getPrice(),
                    orderInfo.getQuantity(),
                    orderInfo.totalAmount())).collect(Collectors.joining());
    }
}