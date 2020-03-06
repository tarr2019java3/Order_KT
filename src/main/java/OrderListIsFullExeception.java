

    public class OrderListIsFullExeception extends RuntimeException {
        public OrderListIsFullExeception() {

            super("Order list get maximum lenght! Cannot add more position!");
        }
    }


