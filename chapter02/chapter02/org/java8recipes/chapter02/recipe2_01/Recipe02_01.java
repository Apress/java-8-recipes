
package org.java8recipes.chapter02.recipe2_01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Recipe 2-1:  Lambda Expressions
 * @author Juneau
 */
public class Recipe02_01 extends Application {

    final Group root = new Group();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello Lambda");
        Scene scene = new Scene(root, 300, 250);
        Button btn = new Button();
        Label message = new Label();
        btn.setLayoutX(60);
        btn.setLayoutY(80);
        btn.setText("Invoke Lambda Expression");
        btn.setOnAction((event) -> {
                message.setText("Lambda expression invoked!");
        });
        root.getChildren().add(btn);
        message.setLayoutX(300/2 - 90);
        message.setLayoutY(30);
        root.getChildren().add(message);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

