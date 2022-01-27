package day01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> findOrderByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public long getCountOrdersByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .count();
    }

    public List<Product> findProductOverPrice(int price) {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream()
                        .filter(product -> product.getPrice() > price))
                .distinct()
                .toList();
    }


    //   más osztálybeli nem static, paraméteres metódus használata:
    public List<String> methodReferenceTest() {
        Order o = new Order("aaa", LocalDate.now());
        return orders.stream()
                .map(o::sayHyToOrder)
                .map(String::toUpperCase)
                .toList();
    }

    //   más osztálybeli static, paraméteres metódus használata:
    public List<String> methodReferenceTest0() {
        return orders.stream()
                .map(Order::sayHyToOrder2)
                .map(String::toUpperCase)
                .toList();
    }

    //   más osztálybeli paraméter nélküli metódus használata:
    public List<String> methodReferenceTest1() {
        return orders.stream()
                .map(Order::getStatus)
                .toList();
    }

    //     osztályon belüli nem static, paraméteres metódus használata:
    public List<String> methodReferenceTest2() {
        return orders.stream()
                .map(order -> orderToString(order))
                .toList();
    }

    public String orderToString(Order order) {
        return order.getStatus() + " " + order.getOrderDate().toString();
    }

    // osztályon belüli static paraméteres metódus használata:
    public List<String> methodReferenceTest3() {
        return orders.stream()
                .map(OrderService::orderToString2)
                .toList();
    }

    public static String orderToString2(Order order) {
        return order.getStatus() + " " + order.getOrderDate().toString();
    }

    public List<Order> getOrdersBetweenTwoDate(LocalDate start, LocalDate end) {
        return orders.stream()
                .filter(order -> order.getOrderDate().isAfter(start)
                        && order.getOrderDate().isBefore(end))
                .collect(Collectors.toList());
    }

    public boolean getOrdersByProductCount(int productCount) {
        return orders.stream()
                .anyMatch(order -> order.getProducts().size() < productCount);

    }

    public Order getOrderByMaxProduct() {
        return orders.stream()
                .max(new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getProducts().size() - o2.getProducts().size();
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException("Empty"));
    }

    // max() lamda kifejezéssel:
    public Order getOrderByMaxProduct2() {
        return orders.stream()
                .max((o1, o2) -> o1.getProducts().size() - o2.getProducts().size())
                .orElseThrow(() -> new IllegalArgumentException("Empty"));
    }

    // videóban még egy megoldás(képernyőmentés)
    // .sorted
    // .findFirst


    public List<Order> getOrdersByProductCategory(String category) {
        return orders.stream()
                .filter(order -> order.getProducts().
                        stream().anyMatch(product -> product.getCategory().equals(category)))
                .collect(Collectors.toList());
    }
}
