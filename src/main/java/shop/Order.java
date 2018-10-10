package shop;

import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private boolean paid;
    private boolean canceled;
    private List<OrderItem> orderItems;

    public Order(int id, Customer customer, List<OrderItem> orderItems) {
        this.id = id;
        this.customer = customer;
        this.paid = false;
        this.canceled = false;
        this.orderItems = orderItems;
    }

    public int getTotal() {
        int totalPrice = 0;
        for (OrderItem orderItem:orderItems) {
            totalPrice += orderItem.getQuantity()*orderItem.getProduct().getPrice();
        }
        return totalPrice;
    }

    public boolean pay() {
        if (!paid) {
            return true;
        } else {
            return false;
        }
    }

    public void cancel() {
        if (!paid) {
            canceled = true;
        } else {
            System.out.println("Paid order cannot be canceled.");
        }
    }

    @Override
    public String toString() {
        String str = "Order{" +
                    "id " + id +
                    ", customer " + customer.getName() +
                    ", paid " + paid +
                    ", orderItems: \n[";

        for (OrderItem item:orderItems) {
            str += item + ",\n";
        }

        str += "]\n}";

        return str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancelled) {
        this.canceled = cancelled;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
