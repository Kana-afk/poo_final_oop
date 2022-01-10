import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CoffeeDecoratorTest {
	Coffee myCoffee = new BasicCoffee();

	@Test
	void Test1(){
		assertEquals(myCoffee.getClass().getName(), "BasicCoffee", "myCoffee is not BasicCoffee member");
	}
	@Test
	void Test2(){
		myCoffee = new ExtraShot(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "ExtraShot", "myCoffee is not ExtraShot member");
	}
	@Test
	void Test3(){
		myCoffee = new Cream(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "Cream", "myCoffee is not Cream member");
	}
	@Test
	void Test4(){
		myCoffee = new Sugar(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "Sugar", "myCoffee is not Sugar member");
	}
	@Test
	void Test5(){
		myCoffee = new ChocolateSyrup(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "ChocolateSyrup", "myCoffee is not Chocolate Syrup member");
	}
	@Test
	void Test6(){
		myCoffee = new IceCream(myCoffee);
		assertEquals(myCoffee.getClass().getName(), "IceCream", "myCoffee is not IceCream member");
	}
	@Test
	void Test7(){
		assertEquals(myCoffee.makeCoffee(), 3.99, "cost for basic Coffee is incorrect");
	}
	@Test
	void Test8(){
		myCoffee = new ChocolateSyrup(myCoffee);
		assertEquals(myCoffee.makeCoffee(), 4.99, "cost for basicCoffee + ChocolateSyrup is incorrect");
	}
	@Test
	void Test9(){
		myCoffee = new IceCream(myCoffee);
		assertEquals(myCoffee.makeCoffee(), 5.49, "cost for basicCoffee + IceCream is incorrect");
	}
	@Test
	void Test10(){
		myCoffee = new Sugar( new ExtraShot( new Cream( new ChocolateSyrup(new IceCream(myCoffee)))));
		assertEquals(myCoffee.makeCoffee(), 8.69, "cost for all toppings once is incorrect");
	}

}
