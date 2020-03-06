import java.io.Serializable;

public class Position implements Serializable {

    private String itemName;
    private int itemQuantity;
    private double itemPrice;



    public Position(){

    }

    public Position(String itemName, int itemQuantity, double itemPrice){
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
    }


    private String space(int x, int y){
        int spaceLength = x - y;
        StringBuilder space = new StringBuilder();

        for(int i = 0; i <= spaceLength; i++){
        space.append(" ");
    }
        return space.toString();
    }

    private String itemQuantityStr(){
        return "x" + itemQuantity;
    }
    private String itemPriceStr(){
        return "" + itemPrice + "$";
    }
    private String positionValueStr(){
        return "" + positionValue() + "$";
    }
    private String positionValueWithDiscountStr() {return "after " + (positionValueWithDiscount()  - positionValue())+"$ discount only " + positionValueWithDiscount() + "$!"; }


    public String toString(){
        if (positionValue() != positionValueWithDiscount())
       return     itemName
                + space(20, itemName.length())
                + itemPriceStr()
                + space(10, itemPriceStr().length())
                + itemQuantityStr()
                + space(4,itemQuantityStr().length())
                + positionValueStr()
                + space(12, positionValueStr().length())
                + "\n"
                + space(43, positionValueWithDiscountStr().length())
                + positionValueWithDiscountStr();
        else {
             return     itemName
                    + space(20, itemName.length())
                    + itemPriceStr()
                    + space(10, itemPriceStr().length())
                    + itemQuantityStr()
                    + space(4,itemQuantityStr().length())
                    + positionValueStr()
                    + space(12, positionValueStr().length());
        }

    }



    public double positionValue(){
        return itemPrice * itemQuantity;
    }

    public double positionValueWithDiscount() {
        if (itemQuantity < 5){
           return positionValue();
        } else if (itemQuantity >= 5 && itemQuantity < 10){
            return positionValue() * 0.95d;
        } else if (itemQuantity >= 10 && itemQuantity <= 20){
            return positionValue() * 0.9d;
        } else {
            return positionValue() * 0.85d;
        }
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

}