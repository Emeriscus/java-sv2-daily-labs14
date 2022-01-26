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
