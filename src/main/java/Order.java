import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Order implements Serializable {


    public Order(){}

    public Order(int maxOrderLength) {
        this.maxOrderLength = maxOrderLength;
    }

    private int currentOrderLength;
    private int maxOrderLength = 10;


    private Position[] orderList = new Position[maxOrderLength];


@JsonIgnore
    public boolean isEmpty(){
        if(currentOrderLength == 0){
            return true;
        } else {
            return false;
        }
    }
@JsonIgnore
    public boolean isFull(){
        if(currentOrderLength == maxOrderLength){
            return true;
        } else {
            return false;
        }
    }


    public void addPosition(Position position) throws OrderListIsFullException {
        for(int i = 0; i < currentOrderLength; i ++){
            if(position.getItemName().equals(orderList[i].getItemName())) {
                orderList[i].setItemQuantity(orderList[i].getItemQuantity() + position.getItemQuantity());
                return;
            }

        }  if(isFull()) {
            throw new OrderListIsFullException();
        } else {
            orderList[currentOrderLength] = position;
        currentOrderLength++;
        }

    }

    public void removePosition(int i) throws OrderListIsEmptyException {
        if(currentOrderLength == 0) throw new OrderListIsEmptyException();

        for(int j = i - 1; j < currentOrderLength; j++){
            orderList[j] = orderList[j + 1];
        }

        currentOrderLength--;

    }

    public void editPosition(int positionIndex, String itemName, int itemQuantity, double itemPrice) throws EmptyPositionExeception{
        int i = positionIndex - 1;
        if(orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemName(itemName);
        orderList[i].setItemPrice(itemPrice);
        orderList[i].setItemQuantity(itemQuantity);
    }

    public void editPosition(int positionIndex, String itemName, int itemQuantity) throws EmptyPositionExeception {
        int i = positionIndex - 1;
        if (orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemName(itemName);
        orderList[i].setItemQuantity(itemQuantity);
    }

    public void editPosition(int positionIndex, String itemName, double itemPrice) throws EmptyPositionExeception{
        int i = positionIndex - 1;
        if(orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemName(itemName);
        orderList[i].setItemPrice(itemPrice);
    }
    public void editPosition(int positionIndex, int itemQuantity, double itemPrice) throws EmptyPositionExeception {
        int i = positionIndex - 1;
        if (orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemPrice(itemPrice);
        orderList[i].setItemQuantity(itemQuantity);
    }
    public void editPosition(int positionIndex, String itemName) throws EmptyPositionExeception{
        int i = positionIndex - 1;
        if(orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemName(itemName);
    }
    public void editPosition(int positionIndex, double itemPrice) throws EmptyPositionExeception{
        int i = positionIndex - 1;
        if(orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemPrice(itemPrice);
    }
    public void editPosition(int positionIndex, int itemQuantity) throws EmptyPositionExeception{
        int i = positionIndex - 1;
        if(orderList[i] == null) throw new EmptyPositionExeception();

        orderList[i].setItemQuantity(itemQuantity);
    }


    public double calculateTotalOrderValue() throws OrderListIsEmptyException {
        if (isEmpty()) {
            throw new OrderListIsEmptyException();
        }
        double totalOrderValue = 0;

        for (int i = 0; i < currentOrderLength; i++) {
            totalOrderValue += orderList[i].positionValue();
        }
        return totalOrderValue;
    }

        public double calculateTotalOrderValueWithDiscount() throws OrderListIsEmptyException {
            if (isEmpty()) {
                throw new OrderListIsEmptyException();
            }
            double totalOrderValueWithDiscount = 0;

            for (int i = 0; i < currentOrderLength; i++) {

                totalOrderValueWithDiscount += orderList[i].positionValueWithDiscount();

            }
            return totalOrderValueWithDiscount;
        }


    @Override
    public String toString() {
        Position[] tmp = new Position[currentOrderLength];
        for(int i = 0; i < currentOrderLength; i ++){
            tmp[i] = orderList[i];
        }
        StringBuilder sb = new StringBuilder();
        for (Position position: tmp)
            sb.append(position).append('\n');
        if(calculateTotalOrderValue() != calculateTotalOrderValueWithDiscount()) {
            return sb
                    + "\nTotal Value= " + calculateTotalOrderValue() + "$"
                    + "\n-" + (calculateTotalOrderValue() - calculateTotalOrderValueWithDiscount()) +"$ total discount"
                    + "\nTotal Value= " + calculateTotalOrderValueWithDiscount() + "$";
        } else {
            return sb
                    + "\nTotal Value= " + calculateTotalOrderValue()
                    + "\n";
        }

    }

    ObjectMapper mapper =  new ObjectMapper();

    public void saveOrder(Order order, String filename){
        try {
    ObjectMapper mapper = new ObjectMapper();


            mapper.writeValue(new File(filename +".json"), order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentOrderLength() {
        return currentOrderLength;
    }

    public void setCurrentOrderLength(int currentOrderLength) {
        this.currentOrderLength = currentOrderLength;
    }

    public int getMaxOrderLength() {
        return maxOrderLength;
    }

    public void setMaxOrderLength(int maxOrderLength) {
        this.maxOrderLength = maxOrderLength;
    }

    public Position[] getOrderList() {
        return orderList;
    }

    public void setOrderList(Position[] orderList) {
        this.orderList = orderList;
    }

    public Order loadOrder(String filename) {

            File file = new File(filename + ".json");

            try {

            Order loadedorder = mapper.readValue(file, Order.class);

                System.out.println(loadedorder);
            return loadedorder;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
