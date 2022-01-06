import static org.junit.jupiter.api.Assertions.*;

//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoffeeDecoratorTest {

	// BasicCoffee Object used for Testing
	Coffee myCoffee = new BasicCoffee();

	@Test
	//Testing myCoffee's Class using BasicCoffee Class.
	void Test1(){
		assertEquals(myCoffee.getClass().getName(), "BasicCoffee", "myCoffee is not BasicCoffee member");
	}
	@Test
	//Testing myCoffee's Class using ExtraShot Class.
	void Test2(){
		myCoffee = new ExtraShot(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "ExtraShot", "myCoffee is not ExtraShot member");
	}
	@Test
	//Testing myCoffee's Class using Cream Class.
	void Test3(){
		myCoffee = new Cream(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "Cream", "myCoffee is not Cream member");
	}
	@Test
	//Testing myCoffee's Class using Sugar Class.
	void Test4(){
		myCoffee = new Sugar(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "Sugar", "myCoffee is not Sugar member");
	}
	@Test
	//Testing myCoffee's Class using Chocolate Syrup Class.
	void Test5(){
		myCoffee = new ChocolateSyrup(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "ChocolateSyrup", "myCoffee is not Chocolate Syrup member");
	}
	@Test
	//Testing myCoffee's Class using IceCream Class.
	void Test6(){
		myCoffee = new IceCream(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "IceCream", "myCoffee is not IceCream member");
	}
	@Test
	//Testing myCoffee's Total Cost for a basic coffee.
	void Test7(){
		assertEquals(myCoffee.makeCoffee(), 3.99, "cost for basic Coffee is incorrect");
	}
	@Test
	//Testing myCoffee's Total Cost for a basic coffee with one Chocolate Syrup topping.
	void Test8(){
		myCoffee = new ChocolateSyrup(myCoffee);
		assertEquals(myCoffee.makeCoffee(), 4.99, "cost for basicCoffee + ChocolateSyrup is incorrect");
	}
	@Test
	//Testing myCoffee's Total Cost for a basic coffee with one Ice Cream topping.
	void Test9(){
		myCoffee = new IceCream(myCoffee);
		assertEquals(myCoffee.makeCoffee(), 5.49, "cost for basicCoffee + IceCream is incorrect");
	}
	@Test
	//Testing myCoffee's Total Cost for a basic coffee with one of each of the toppings.
	void Test10(){
		myCoffee = new Sugar( new ExtraShot( new Cream( new ChocolateSyrup(new IceCream(myCoffee)))));
		assertEquals(myCoffee.makeCoffee(), 8.69, "cost for all toppings once is incorrect");
	}

}
