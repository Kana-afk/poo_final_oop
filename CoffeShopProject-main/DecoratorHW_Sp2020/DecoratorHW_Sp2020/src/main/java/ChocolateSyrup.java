
public class ChocolateSyrup extends CoffeeDecorator {

    private double cost = 1.00;

    ChocolateSyrup (Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee(){

        return specialCoffee.makeCoffee() + addChocolateSyrup();
    }

    private double addChocolateSyrup() {
        return cost;
    }

}
