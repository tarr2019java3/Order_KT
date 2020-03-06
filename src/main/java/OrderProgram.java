import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProgram {



    public static void main(String[] args) {
        Position p1 = new Position("item1", 4, 40 );

        Position p2 = new Position("item2", 4, 200);




        Order order = new Order();

        order.addPosition(p1);
        order.addPosition(p2);
        System.out.println(order);
        order.addPosition(p1);
        order.addPosition(p2);
        System.out.println(order);

        order.saveOrder(order, "neworder");
        System.out.println("\nloadtest\n");
        order.loadOrder("neworder");




    }
}
