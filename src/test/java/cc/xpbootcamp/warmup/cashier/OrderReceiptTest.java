package cc.xpbootcamp.warmup.cashier;


import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class OrderReceiptTest {
    @Test
    public void shouldPrintOrderInfoAndSalesTaxInformation() {
        List<OrderInfo> orderInfos = new ArrayList<OrderInfo>() {{
            add(new OrderInfo("milk", 10.0, 2));
            add(new OrderInfo("biscuits", 5.0, 5));
            add(new OrderInfo("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(orderInfos, new Date(1582096830000L)));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk, 10.00 x 2, 20.00\n"));
        assertThat(output, containsString("biscuits, 5.00 x 5, 25.00\n"));
        assertThat(output, containsString("chocolate, 20.00 x 1, 20.00\n"));
        assertThat(output, containsString("税额: 6.50\n"));
        assertThat(output, containsString("总价: 71.50"));
    }

    @Test
    public void shouldPrintSloganAndTime() {
        OrderReceipt receipt = new OrderReceipt(new Order(Collections.emptyList(), new Date(1582096830000L)));

        String output = receipt.printReceipt();

        assertThat(output, containsString("=====老王超市，值得信赖======\n\n"));
        assertThat(output, containsString("2020年2月19日，星期三\n\n"));
    }

    @Test
    public void shouldPrintDiscountWhenDayIsWednesday() {
        List<OrderInfo> orderInfos = new ArrayList<OrderInfo>() {{
            add(new OrderInfo("milk", 10.0, 2));
            add(new OrderInfo("biscuits", 5.0, 5));
            add(new OrderInfo("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(orderInfos, new Date(1582096830000L)));

        String output = receipt.printReceipt();
        assertThat(output, containsString("折扣: 70.07\n"));
    }

    @Test
    public void shouldDiscoverDiscountWhenDayNotIsWednesday() {
        List<OrderInfo> orderInfos = new ArrayList<OrderInfo>() {{
            add(new OrderInfo("milk", 10.0, 2));
            add(new OrderInfo("biscuits", 5.0, 5));
            add(new OrderInfo("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(orderInfos, new Date(1582299930000L)));

        String output = receipt.printReceipt();
        assertThat(output, not(containsString("折扣: 70.07\n")));
    }
}