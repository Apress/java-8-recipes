package org.java8recipes.chapter17.recipe17_04;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
 
/**
 * Recipe 17-4:  Responding To HTML Events
 * @author cdea
 * Update:  J. Juneau
 */
public class RespondingToHtmlEvents extends Application {
    
    String url = "http://weather.yahooapis.com/forecastrss?w=2379574&u=f";
    int refreshCountdown = 60;
    
    @Override public void start(Stage stage) {
        
        // create the scene
        stage.setTitle("Chapter 17-4 Responding to Html Events");
        Group root = new Group();
        Scene scene = new Scene(root, 460, 340);
        
        final WebEngine webEngine = new WebEngine(url);
        
        StringBuilder template = new StringBuilder();
        template.append("<head>\n");
        template.append("<style type=\"text/css\">{css_stuff}</style>\n");       
        template.append("</head>\n");      
        
        StringBuilder cssCode = new StringBuilder();
        cssCode.append("body {background-color:#b4c8ee;} \n");

        template.append("<body id='weather_background'>");
        template.append("<form>\n");
        template.append("  <input type=\"button\" onclick=\"alert('warning')\" value=\"Panic Button\" />\n");
        template.append("  <input type=\"button\" onclick=\"alert('unwarning')\" value=\"Calm down\" />\n");
        template.append("</form>\n");

        String cssAndScript = template.toString().replaceAll("\\{css_stuff\\}", cssCode.toString());
        
        final String fullHtml = cssAndScript;
        
        final Text warningMessage = createMessage(Color.RED, "warning: ");
        warningMessage.setOpacity(0);
        final WebView webView = new WebView();
        IntegerProperty countDown = new SimpleIntegerProperty(refreshCountdown);
        countDown.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            webView.getEngine().executeScript("document.getElementById('countdown').innerHTML = 'Seconds till refresh: " + newValue + "'");
            if (newValue.intValue() == 0) {
                webEngine.reload();
            }
        });
        final Timeline timeToRefresh = new Timeline();
        timeToRefresh.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(countDown, refreshCountdown)),
                new KeyFrame(Duration.seconds(refreshCountdown), new KeyValue(countDown, 0))
        );
        
        webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> observable,
                        State oldValue, State newValue) -> {
                    System.out.println("done!" + newValue.toString());
                    if (newValue != State.SUCCEEDED) {
                        return;
                    }
                    Weather weather = parse(webEngine.getDocument());
                    warningMessage.setText("Warning: " + weather.currentWeatherText
                            + "\nTemp: " + weather.temperature + "\n E-mailed others");
            
            StringBuilder location = new StringBuilder();
            location.append("<b>")
                    .append(weather.city)
                    .append(", ")
                    .append(weather.region)
                    .append(" ")
                    .append(weather.country)
                    .append("</b><br />\n");
            
            String timeOfWeatherTextDiv = "<b id=\"timeOfWeatherText\">" + weather.dateTimeStr + "</b><br />\n";
            String countdownText = "<b id=\"countdown\"></b><br />\n";
            webView.getEngine().loadContent(fullHtml + location.toString() + timeOfWeatherTextDiv + countdownText + weather.htmlDescription);
            
            timeToRefresh.playFromStart();
        }); // end of addListener()
       
        webView.getEngine().setOnAlert((WebEvent<String> evt) -> {
            warningMessage.setOpacity("warning".equalsIgnoreCase(evt.getData()) ? 1d : 0d);
        });
        
        root.getChildren().addAll(webView, warningMessage);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args){
        Application.launch(args);
    }
    private static String obtainAttribute(NodeList nodeList, String attribute) {
        String attr = nodeList
                .item(0)
                .getAttributes()
                .getNamedItem(attribute)
                .getNodeValue();
        return attr;
    
    }
    private static Weather parse(Document doc) {
        
        NodeList currWeatherLocation = doc.getElementsByTagNameNS("http://xml.weather.yahoo.com/ns/rss/1.0", "location");

        Weather weather = new Weather();
        weather.city = obtainAttribute(currWeatherLocation, "city");
        weather.region = obtainAttribute(currWeatherLocation, "region");
        weather.country = obtainAttribute(currWeatherLocation, "country");
        
        NodeList currWeatherCondition = doc.getElementsByTagNameNS("http://xml.weather.yahoo.com/ns/rss/1.0", "condition");
        weather.dateTimeStr = obtainAttribute(currWeatherCondition, "date");
        weather.currentWeatherText = obtainAttribute(currWeatherCondition, "text");
        weather.temperature = obtainAttribute(currWeatherCondition, "temp");
        
        String forcast = doc.getElementsByTagName("description")
                        .item(1)
                        .getTextContent();
        weather.htmlDescription = forcast;
        
        return weather;
    }
    
    private Text createMessage(Color color, String message) {
        DropShadow dShadow = new DropShadow();
        dShadow.setOffsetX(3.5f);
        dShadow.setOffsetY(3.5f);

        Text textMessage = new Text();
        textMessage.setText(message);
        textMessage.setX(100);
        textMessage.setY(50);
        textMessage.setStrokeWidth(2);
        textMessage.setStroke(Color.WHITE);
        textMessage.setEffect(dShadow);
        textMessage.setFill(color);
        textMessage.setFont(Font.font(null, FontWeight.BOLD, 35));
        textMessage.setTranslateY(50);
                    
        return textMessage;
    }
}

class Weather {
    String dateTimeStr;
    String city;
    String region;
    String country;
    String currentWeatherText;
    String temperature;
    String htmlDescription;
    
}