@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrder(List<OrderItem> orderItems) {
        // Calculate total price
        double totalPrice = orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity())
                .sum();

        // Create a new order
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);

        // Save the order to the database
        return orderRepository.save(order);
    }

    @Transactional
    public Order addItemToOrder(Long orderId, OrderItem orderItem) {
        // Find the order by orderId
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Add the new order item to the order
            order.getOrderItems().add(orderItem);
            // Update the total price of the order
            order.setTotalPrice(order.getTotalPrice() + orderItem.getProduct().getPrice() * orderItem.getQuantity());
            // Save the updated order to the database
            return orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
    }

    @Transactional
    public Order removeItemFromOrder(Long orderId, Long orderItemId) {
        // Find the order by orderId
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            // Find the order item to remove
            Optional<OrderItem> optionalOrderItem = order.getOrderItems().stream()
                    .filter(item -> item.getId().equals(orderItemId))
                    .findFirst();
            if (optionalOrderItem.isPresent()) {
                OrderItem orderItemToRemove = optionalOrderItem.get();
                // Remove the order item from the order
                order.getOrderItems().remove(orderItemToRemove);
                // Update the total price of the order
                order.setTotalPrice(order.getTotalPrice() - orderItemToRemove.getProduct().getPrice() * orderItemToRemove.getQuantity());
                // Save the updated order to the database
                return orderRepository.save(order);
            } else {
                throw new IllegalArgumentException("Order item not found with ID: " + orderItemId);
            }
        } else {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
    }

}
