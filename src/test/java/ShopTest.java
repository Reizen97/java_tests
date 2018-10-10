import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import shop.Customer;
import shop.Order;
import shop.OrderItem;
import shop.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopTest {
    private List<Order> orderList;
    private List<OrderItem> orderItems;
    private List<Product> productList;
    private List<Customer> customerList;

    @Before
    public void createCustomer() {
        customerList = new ArrayList<Customer>();
        for (int i = 0; i < 2; i++) {
            customerList.add(i, new Customer("Customer" + i, "Address" + i));
        }
    }

    @Before
    public void createProduct() {
        productList = new ArrayList<Product>();
        for (int i = 0; i < 2; i++) {
            productList.add(i, new Product(i + 10, "Product" + i));
        }
    }

    @Before
    public void createOrders() {
        orderItems = new ArrayList<OrderItem>();
        for (int i = 0; i < 2; i++) {
            orderItems.add(i, new OrderItem(productList.get(i), i + 1));
        }
        orderList = new ArrayList<Order>();
        for (int i = 0; i < 2; i++) {
            orderList.add(i, new Order(i, customerList.get(i), orderItems));
        }
    }

    @Test
    public void createOrderTest() {
        for (Object item:orderList) {
            Assert.assertNotNull(item);
        }
    }

    @Test
    public void checkTotalCostTest() {
        Assert.assertTrue(32 == orderList.get(1).getTotal());
    }

    @Test
    public void payOrderTest() {
        orderList.get(1).setPaid(true);
        Assert.assertFalse(orderList.get(1).pay());
    }

    @Test
    public void cancelOrderTest() {
        orderList.get(1).setPaid(true);
        orderList.get(1).cancel();
        Assert.assertFalse(orderList.get(1).isCancelled());

        orderList.get(0).setPaid(false);
        orderList.get(0).cancel();
        Assert.assertTrue(orderList.get(0).isCancelled());
    }

    @Test
    public void findOrderForCertainCustomerTest() {
        String customerName = "Customer0";
        List<String> ordersForCertainCustomer = new ArrayList<String>();
        for (Order order:orderList) {
            if (order.getCustomer().getName().equals(customerName)) {
                ordersForCertainCustomer.add(order.getCustomer().getName());
            }
        }
        for (String customer:ordersForCertainCustomer) {
            Assert.assertEquals(customerName, customer);
        }
    }

    @Test
    public void findOrderWithCertainProductTest() {
        String product = "Product0";
        List<String> ordersWithCertainProduct = new ArrayList<String>();
        for (Order order:orderList) {
            for (OrderItem item:orderItems) {
                if (item.getProduct().getDescription().equals(product)) {
                    ordersWithCertainProduct.add(item.getProduct().getDescription());
                }
            }
        }
        for (String pr:ordersWithCertainProduct) {
            Assert.assertEquals(product, pr);
        }
    }

    @Test
    public void findOrderWithPriceNotHigherThanCertainNumberTest() {
        int number = 10;
        List<Integer> orderWithPriceNotHigherThanCertainNumber = new ArrayList<Integer>();
        for (Order order:orderList) {
            for (OrderItem item:orderItems) {
                if (item.getProduct().getPrice() > 10) {
                    orderWithPriceNotHigherThanCertainNumber.add(item.getProduct().getPrice());
                }
            }
        }
        for (int num:orderWithPriceNotHigherThanCertainNumber) {
            Assert.assertTrue(num > number);
        }
    }
}
