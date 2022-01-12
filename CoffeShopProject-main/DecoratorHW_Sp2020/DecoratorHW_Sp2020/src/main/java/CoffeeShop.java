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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Arrays;

public class CoffeeShop extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Coffee order;

	Button item1, item2, item3, item4, item5; 
	Button startOrder, deleteOrder, finishOrder; 
	TextField totalCost = new TextField("Welcome to Aryan's Coffee Shop!"); 

	TextArea output = new TextArea("Try out the Most Amazing:\n" +
			"Black Coffee: $3.99\n" +
			" + extra shot: $1.20\n" +
			" + cream: $.50\n" +
			" + sugar: $.50\n" +
			" + Chocolate Syrup: $1.00\n" +
			" + IceCream: $1.50");

	HBox main; 

	int[] freqArray = new int[]{0, 0, 0, 0, 0}; 

	String currentOrder(){
		return ("\nYour Toppings: \n\n"
				+ freqArray[0] + " Extra Shot\n"
				+ freqArray[1] + " Cream\n"
				+ freqArray[2] + " Sugar\n"
				+ freqArray[3] + " Chocolate Syrup\n"
				+ freqArray[4] + " Ice Cream");
	}

	TextArea yourOrder = new TextArea(currentOrder()); 

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Aryan's Coffee Shop");

		item1 = new Button("Add Extra Shot");
		item2 = new Button("Add Cream");
		item3 = new Button("Add Sugar");
		item4 = new Button("Add Chocolate Syrup");
		item5 = new Button("Add Ice Cream");
		startOrder = new Button("Start Order");
		deleteOrder = new Button( "Delete Order");
		finishOrder = new Button("Finish Order");

		for(Button b : Arrays.asList(item1, item2, item3, item4, item5, deleteOrder, finishOrder)){
			b.setDisable(true);
		}

		startOrder.setDisable(false);
		output.setDisable(false);
		yourOrder.setDisable(false);

		VBox toppings = new VBox(item1, item2, item3, item4, item5, yourOrder);

		toppings.setStyle( "-fx-font-family: 'Arial';" + "-fx-font-size: 14 px;" );
		toppings.setSpacing(15);
		toppings.setPadding(new Insets(30,15,10,15));

		VBox mainButtons = new VBox(output, totalCost, startOrder, deleteOrder, finishOrder);

		mainButtons.setSpacing(15);
		mainButtons.setPadding(new Insets(30,15,10,15));
		mainButtons.setStyle( "-fx-font-family: 'Arial';" + "-fx-font-size: 14 px;" );

		output.setStyle("-fx-control-inner-background:#8B4513;" + "-fx-opacity: 0.85;" );
		totalCost.setPadding(new Insets(18,20,18,20));
		yourOrder.setStyle("-fx-control-inner-background:#8B4513;" + "-fx-opacity: 0.85;" );

		for(Button b : Arrays.asList(item1, item2, item3, item4, item5, startOrder, deleteOrder, finishOrder)){
			b.setMinWidth(200);
			b.setPadding(new Insets(5,5,5,5));
			b.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 12px");
		}
		startOrder.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 15px");
		deleteOrder.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 15px");
		finishOrder.setStyle("-fx-font-family: 'Arial Black';" + "-fx-text-fill: #6666A9;" + "-fx-font-size: 15px");

		item1.setOnAction(e -> {

			freqArray[0]++; 
			order = new ExtraShot(order); 
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100;
			totalCost.setText("cost: $" + newCost); 
			yourOrder.setText(currentOrder()); 

		});

		item2.setOnAction(e -> {
			freqArray[1]++; 
			order = new Cream(order);
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; 
			totalCost.setText("cost: $" + newCost); 
			yourOrder.setText(currentOrder()); 
		});

		item3.setOnAction(e -> {
			freqArray[2]++; 
			order = new Sugar(order); 
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; 
			totalCost.setText("cost: $" + newCost); 
			yourOrder.setText(currentOrder()); 
		});

		item4.setOnAction(e -> {
			freqArray[3]++; 
			order = new ChocolateSyrup(order); 
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100; 
			totalCost.setText("cost: $" + newCost);
			yourOrder.setText(currentOrder());
		});

		item5.setOnAction(e -> {
			freqArray[4]++;
			order = new IceCream(order); 
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100;
			totalCost.setText("cost: $" + newCost); 
			yourOrder.setText(currentOrder()); 
		});

		startOrder.setOnAction(e -> {

			order = new BasicCoffee();
			double newCost = (double) Math.round (order.makeCoffee() * 100) / 100;
			totalCost.setText("cost: $" + newCost);

			for (Button b : Arrays.asList(item1, item2, item3, item4, item5, deleteOrder, finishOrder)){
				b.setDisable(false);
			}

			startOrder.setDisable(true);
		});

		deleteOrder.setOnAction(e -> {

			try{
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

		finishOrder.setOnAction(e -> {

			for(Button b : Arrays.asList(item1, item2, item3, item4, item5, startOrder)){
				b.setDisable(true);
			}
			output.setDisable(true);
			yourOrder.setDisable(true);

			deleteOrder.setText("New-Order");

			totalCost.setText("Your Total " + totalCost.getText() + ". Thanks!");

		});

		main = new HBox(toppings, mainButtons); 
		main.setStyle("-fx-background-image: url('img3.jpg');" ); 
		Scene scene = new Scene(main,600,600); 
		primaryStage.setScene(scene); 
		primaryStage.show();

	}

}
