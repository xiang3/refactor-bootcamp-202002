package cc.xpbootcamp.warmup.cashier;

import java.util.List;

public class Order {
    String cName;
    String addr;
    List<OrderInfo> orderInfoList;

    public Order(String cName, String addr, List<OrderInfo> orderInfoList) {
        this.cName = cName;
        this.addr = addr;
        this.orderInfoList = orderInfoList;
    }

    public String getCustomerName() {
        return cName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<OrderInfo> getLineItems() {
        return orderInfoList;
    }
}
