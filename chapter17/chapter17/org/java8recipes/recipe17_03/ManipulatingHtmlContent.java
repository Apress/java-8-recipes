package org.java8recipes.chapter17.recipe17_03;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
 

/**
 * Recipe 17-3:  Manipulating HTML5 Content
 * @author cdea
 * Updated: J Juneau
 */
public class ManipulatingHtmlContent extends Application {
    String url = "http://weather.yahooapis.com/forecastrss?w=2379574&u=f";
    int refreshCountdown = 60;
    
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Chapter 17-3 Manipulating HTML5 content");
        Group root = new Group();
        Scene scene = new Scene(root, 460, 340);
        
        final WebEngine webEngine = new WebEngine(url);
        
        StringBuilder template = new StringBuilder();
        template.append("<head>\n")
                .append("<style type=\"text/css\">body {background-color:#b4c8ee;}</style>\n")
                .append("</head>\n")
                .append("<body id='weather_background'>");
        
        final String fullHtml = template.toString();
        
        final WebView webView = new WebView();
        IntegerProperty countDown = new SimpleIntegerProperty(refreshCountdown);
        countDown.addListener((ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) -> {
            webView.getEngine().executeScript(
                    "document.getElementById('countdown').innerHTML = 'Seconds till refresh: " 
                            + newValue + "'");
            if (newValue.intValue() == 0) {
                webEngine.reload();
            }
        });
        final Timeline timeToRefresh = new Timeline();
        timeToRefresh.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(countDown, refreshCountdown)),
                new KeyFrame(Duration.seconds(refreshCountdown), new KeyValue(countDown, 0))
        );
        
        webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> observable, State oldValue, State newValue) -> {
            System.out.println("done!" + newValue.toString());
            if (newValue != State.SUCCEEDED) {
                return;
            }
            // request 200 OK
            Weather weather = parse(webEngine.getDocument());
            
            StringBuilder locationText = new StringBuilder();
            locationText.append("<b>")
                    .append(weather.city)
                    .append(", ")
                    .append(weather.region)
                    .append(" ")
                    .append(weather.country)
                    .append("</b><br />\n");
            
            String timeOfWeatherTextDiv = "<b id=\"timeOfWeatherText\">" + weather.dateTimeStr + "</b><br />\n";
            String countdownText = "<b id=\"countdown\"></b><br />\n";
            webView.getEngine().loadContent(fullHtml + locationText.toString() +
                    timeOfWeatherTextDiv +
                    countdownText +
                    weather.htmlDescription);
            System.out.println(fullHtml + locationText.toString() +
                    timeOfWeatherTextDiv +
                    countdownText +
                    weather.htmlDescription);
            timeToRefresh.playFromStart();
        });

        root.getChildren().addAll(webView);
        
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