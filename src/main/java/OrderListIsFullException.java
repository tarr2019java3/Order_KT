

    public class OrderListIsFullException extends RuntimeException {
        public OrderListIsFullException() {

            super("Order list get maximum lenght! Cannot add more position!");
        }
    }


