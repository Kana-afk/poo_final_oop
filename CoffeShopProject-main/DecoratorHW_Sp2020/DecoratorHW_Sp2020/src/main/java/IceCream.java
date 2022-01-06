
public class IceCream extends CoffeeDecorator{

    private double cost = 1.50;

    IceCream(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee(){
        return specialCoffee.makeCoffee() + addIceCream();
    }

    private double addIceCream() {
//        System.out.println(" + IceCream: $1.50");
        return cost;
    }

}
