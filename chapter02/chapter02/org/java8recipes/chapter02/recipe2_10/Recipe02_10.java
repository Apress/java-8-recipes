package org.java8recipes.chapter02.recipe2_10;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Recipe 2-10:  JavaFX DatePicker
 * @author Juneau
 */
public class Recipe02_10 extends Application {

    private Label dateLabel;
    private DatePicker datePicker;

    @Override
    public void start(Stage primaryStage) throws Exception {
        dateLabel = new Label("Select a date using the widget");
        datePicker = new DatePicker(LocalDate.now());
        datePicker.setOnAction(event -> {
            dateLabel.setText("The selected date is: " + datePicker.getValue());
        });
   
        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.getChildren().add(dateLabel);
        flow.getChildren().add(datePicker);

        primaryStage.setScene(new Scene(flow, 300, 100));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
