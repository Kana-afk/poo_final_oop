import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
//import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Arrays;

public class CoffeeShop extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//Coffee Object which is being ordered by the user.
	Coffee order;

	//Objects Needed for GUI
	Button item1, item2, item3, item4, item5; // Represent Each of the 5 toppings // ExtraShot, Cream, Sugar, ChocolateSyrup, IceCream
	Button startOrder, deleteOrder, finishOrder; // start, delete, finish the order
	TextField totalCost = new TextField("Welcome to Aryan's Coffee Shop!"); //Message Bar that display's Cost While Building the order

	//Used to display Prices and Types of toppings.
	TextArea output = new TextArea("Try out the Most Amazing:\n" +
			"Black Coffee: $3.99\n" +
			" + extra shot: $1.20\n" +
			" + cream: $.50\n" +
			" + sugar: $.50\n" +
			" + Chocolate Syrup: $1.00\n" +
			" + IceCream: $1.50");

	HBox main; // Main HBox that is put in the scene

	//Order for frequency: ExtraShot, Cream, Sugar, ChocolateSyrup, IceCream
	int[] freqArray = new int[]{0, 0, 0, 0, 0}; //Checks how many times a topping was added.

	//Function that returns the amount of toppings used in one order as a string.
	String currentOrder(){
		return ("\nYour Toppings: \n\n"
				+ freqArray[0] + " Extra Shot\n"
				+ freqArray[1] + " Cream\n"
				+ freqArray[2] + " Sugar\n"
				+ freqArray[3] + " Chocolate Syrup\n"
				+ freqArray[4] + " Ice Cream");
	}

	TextArea yourOrder = new TextArea(currentOrder()); //Output the string above to a TextArea.

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Aryan's Coffee Shop");

		item1 = new Button("Add Extra Shot");
		item2 = new Button("Add Cream");
		item3 = new Button("Add Sugar");
		item4 = new Button("Add Chocolate Syrup");
		item5 = new Button("Add Ice Cream");
		startOrder = new Button("Start Order");
		deleteOrder = new Button( "Delete Order");
		finishOrder = new Button("Finish Order");

		//Disabled Buttons
		for(Button b : Arrays.asList(item1, item2, item3, item4, item5, deleteOrder, finishOrder)){
			b.setDisable(true);
		}

		//Enabled Items
		startOrder.setDisable(false);
		output.setDisable(false);
		yourOrder.setDisable(false);

		//Left VBox
		//Contains buttons for toppings and output of number of toppings added to your order.
		VBox toppings = new VBox(item1, item2, item3, item4, item5, yourOrder);

		//Left VBox Styling
		toppings.setStyle( "-fx-font-family: 'Arial';" + "-fx-font-size: 14 px;" );
		toppings.setSpacing(15);
		toppings.setPadding(new Insets(30,15,10,15));

		//Right VBox
		//Contains buttons for start, finish, delete the order, and output the cost of individual toppings.
		VBox mainButtons = new VBox(output, totalCost, startOrder, deleteOrder, finishOrder);

		//Right VBox Styling
		mainButtons.setSpacing(15);
		mainButtons.setPadding(new Insets(30,15,10,15));
		mainButtons.setStyle( "-fx-font-family: 'Arial';" + "-fx-font-size: 14 px;" );

		//TextField, Text Area Styling
		output.setStyle("-fx-control-inner-background:#8B4513;" + "-fx-opacity: 0.85;" );
		totalCost.setPadding(new Insets(18,20,18,20));
		yourOrder.setStyle("-fx-control-inner-background:#8B4513;" + "-fx-opacity: 0.85;" );

		//General Button Styling
		for(Button b : Arrays.asList(item1, item2, item3, item4, item5, startOrder, deleteOrder, finishOrder)){
			b.setMinWidth(200);
			b.setPadding(new Insets(5,5,5,5));
			b.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 12px");
		}
		startOrder.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 15px");
		deleteOrder.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 15px");
		finishOrder.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 15px");

	//Event Handlers
		//Topping 1: ExtraShot
		item1.setOnAction(e -> {

			freqArray[0]++;  //Update Frequency Array
			order = new ExtraShot(order); //Load Topping to already existing Coffee Object
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; //Calculate Cost of newly created order.
			totalCost.setText("cost: $" + newCost); //Display Cost of order.
			yourOrder.setText(currentOrder()); //Update the number of toppings shown in TextArea (yourOrder)

		});

		//Topping 2: Cream
		item2.setOnAction(e -> {
			freqArray[1]++; //Update Frequency Array
			order = new Cream(order); //Load Topping to already existing Coffee Object
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; //Calculate Cost of newly created order.
			totalCost.setText("cost: $" + newCost); //Display Cost of order.
			yourOrder.setText(currentOrder()); //Update the number of toppings shown in TextArea (yourOrder)
		});

		//Topping 3: Sugar
		item3.setOnAction(e -> {
			freqArray[2]++; //Update Frequency Array
			order = new Sugar(order); //Load Topping to already existing Coffee Object
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; //Calculate Cost of newly created order.
			totalCost.setText("cost: $" + newCost); //Display Cost of order.
			yourOrder.setText(currentOrder()); //Update the number of toppings shown in TextArea (yourOrder)
		});

		//Topping 4: Chocolate Syrup
		item4.setOnAction(e -> {
			freqArray[3]++; //Update Frequency Array
			order = new ChocolateSyrup(order); //Load Topping to already existing Coffee Object
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; //Calculate Cost of newly created order.
			totalCost.setText("cost: $" + newCost); //Display Cost of order.
			yourOrder.setText(currentOrder()); //Update the number of toppings shown in TextArea (yourOrder)
		});

		//Topping 5: Ice Cream
		item5.setOnAction(e -> {
			freqArray[4]++; //Update Frequency Array
			order = new IceCream(order); //Load Topping to already existing Coffee Object
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; //Calculate Cost of newly created order.
			totalCost.setText("cost: $" + newCost); //Display Cost of order.
			yourOrder.setText(currentOrder()); //Update the number of toppings shown in TextArea (yourOrder)
		});

		//Start Order
		startOrder.setOnAction(e -> {

			order = new BasicCoffee(); //Instantiate order with Basic Coffee
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; //Calculate cost
			totalCost.setText("cost: $" + newCost); //Display Cost

			//Enable Buttons
			for (Button b : Arrays.asList(item1, item2, item3, item4, item5, deleteOrder, finishOrder)){
				b.setDisable(false);
			}

			//set startOrder to disable
			startOrder.setDisable(true);
		});

		//Delete Order
		deleteOrder.setOnAction(e -> {

			try{ //Re-instantiate objects and call start function
				freqArray = new int[]{0, 0, 0, 0, 0};
				yourOrder.setText(currentOrder());
				totalCost.setText("Welcome to Aryan's Coffee Shop!");
				start(primaryStage);
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		});

		//Finish Order
		finishOrder.setOnAction(e -> {

			//Disable Buttons
			for(Button b : Arrays.asList(item1, item2, item3, item4, item5, startOrder)){
				b.setDisable(true);
			}
			output.setDisable(true);
			yourOrder.setDisable(true);

			//Change delete Order Button to New-Order since they have same functionality
			deleteOrder.setText("New-Order");

			//Final Message upon order completion
			totalCost.setText("Your Total " + totalCost.getText() + ". Thanks!");

		});

		main = new HBox(toppings, mainButtons); //Add 2 VBoxes to main
		main.setStyle("-fx-background-image: url('img3.jpg');" ); //Add image as background of HBox
		Scene scene = new Scene(main,600,600); //Add HBox to scene
		primaryStage.setScene(scene); //Add scene to PrimaryStage
		primaryStage.show(); //Show Primary Stage

	}

}
