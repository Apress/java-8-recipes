var Canvas = javafx.scene.canvas.Canvas;
var GraphicsContext = javafx.scene.canvas.GraphicsContext;
var Button = javafx.scene.control.Button;
var ChoiceBox = javafx.scene.control.ChoiceBox;
var FxCollections = javafx.collections.FXCollections;
var StackPane = javafx.scene.layout.StackPane;
var Scene = javafx.scene.Scene;
var Color = javafx.scene.paint.Color;

function start(primaryStage) {
    primaryStage.title = "JS Draw";
    var canvas = new Canvas(300, 300);
    var graphicsContext = canvas.getGraphicsContext2D();
    
    var resetButton = new Button("Reset");
    resetButton.onAction = function(ae) {
        graphicsContext.clearRect(1,1,
          graphicsContext.getCanvas().getWidth()-2,
          graphicsContext.getCanvas().getHeight()-2);
    };
    resetButton.setTranslateX(10);
    
   
    var root = new StackPane();
    root.children.add(resetButton);
    root.children.add(canvas);
    primaryStage.scene = new Scene(root, 300, 250);
    primaryStage.show();
}