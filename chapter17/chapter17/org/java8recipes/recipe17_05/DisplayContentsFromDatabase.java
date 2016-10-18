package org.java8recipes.chapter17.recipe17_05;

import java.util.*;
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 

/**
 * Display Contents From Database
 * @author cdea
 */
public class DisplayContentsFromDatabase extends Application {

    @Override public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 640, 480, Color.WHITE);
        final Map<String, Hyperlink> hyperLinksMap = new TreeMap<>();
        
        
        final WebView newsBrief = new WebView(); // upper right
        final WebEngine webEngine = new WebEngine();
        final WebView websiteView = new WebView(); // lower right
        
        webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> observable,
                        State oldValue, State newValue) -> {
            if (newValue != State.SUCCEEDED) {
                return;
            }
            
            RssFeed rssFeed = parse(webEngine.getDocument(), webEngine.getLocation());
            
            hyperLinksMap.get(webEngine.getLocation()).setText(rssFeed.channelTitle);
            
            // print feed info:
            StringBuilder rssSource = new StringBuilder();
            rssSource.append("<head>\n")
                    .append("</head>\n")
                    .append("<body>\n");
            rssSource.append("<b>")
                    .append(rssFeed.channelTitle)
                    .append(" (")
                    .append(rssFeed.news.size())
                    .append(")")
                    .append("</b><br />\n");
            StringBuilder htmlArticleSb = new StringBuilder();
            for (NewsArticle article:rssFeed.news) {
                
                htmlArticleSb.append("<hr />\n")
                        .append("<b>\n")
                        .append(article.title)
                        .append("</b><br />")
                        .append(article.pubDate)
                        .append("<br />")
                        .append(article.description)
                        .append("<br />\n")
                        .append("<input type=\"button\" onclick=\"alert('")
                        .append(article.link)
                        .append("')\" value=\"View\" />\n");
            }
            
            String content = rssSource.toString() + "<form>\n" + htmlArticleSb.toString() + "</form></body>\n";
            System.out.println(content);
            newsBrief.getEngine().loadContent(content);
            // write to disk if not already.
            DBUtils.saveRssFeed(rssFeed);
        }); // end of webEngine addListener()

        newsBrief.getEngine().setOnAlert((WebEvent<String> evt) -> {
            websiteView.getEngine().load(evt.getData());
        }); // end of newsBrief setOnAlert()
        
        // Left and right split pane
        SplitPane splitPane = new SplitPane();
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());

        final VBox leftArea = new VBox(10);
        final TextField urlField = new TextField();
        urlField.setOnAction((ActionEvent ae) -> {
            String url = urlField.getText();
            final Hyperlink jfxHyperLink = createHyperLink(url, webEngine);
            hyperLinksMap.put(url, jfxHyperLink);
            HBox rowBox = new HBox(20);
            rowBox.getChildren().add(jfxHyperLink);
            leftArea.getChildren().add(rowBox);
            webEngine.load(url);
            urlField.setText("");
        }); // end of urlField setOnAction()
        
        leftArea.getChildren().add(urlField);
        
        List<RssFeed> rssFeeds = DBUtils.loadFeeds();
        rssFeeds.stream().map((feed) -> {
            HBox rowBox = new HBox(20);
            final Hyperlink jfxHyperLink = new Hyperlink(feed.channelTitle);
            jfxHyperLink.setUserData(feed);
            final String location = feed.link;
            hyperLinksMap.put(feed.link, jfxHyperLink);
            jfxHyperLink.setOnAction((ActionEvent evt) -> {
                webEngine.load(location);
            });
            rowBox.getChildren().add(jfxHyperLink);
            return rowBox;
        }).forEach((rowBox) -> {
            leftArea.getChildren().add(rowBox);
        });
        
        
        // Dragging over surface
        scene.setOnDragOver((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasUrl()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        }); // end of scene.setOnDragOver()
         
        // Dropping over surface
        scene.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            HBox rowBox = new HBox(20);
            if (db.hasUrl()) {
                if (!hyperLinksMap.containsKey(db.getUrl())) {
                    final Hyperlink jfxHyperLink = createHyperLink(db.getUrl(), webEngine);
                    hyperLinksMap.put(db.getUrl(), jfxHyperLink);
                    rowBox.getChildren().add(jfxHyperLink);
                    leftArea.getChildren().add(rowBox);
                }
                webEngine.load(db.getUrl());
            }
            event.setDropCompleted(success);
            event.consume();
        });  // end of scene.setOnDragDropped()
        
        leftArea.setAlignment(Pos.TOP_LEFT);
        
        // Upper and lower split pane
        SplitPane splitPane2 = new SplitPane();
        splitPane2.setOrientation(Orientation.VERTICAL);
        splitPane2.prefWidthProperty().bind(scene.widthProperty());
        splitPane2.prefHeightProperty().bind(scene.heightProperty());

        HBox centerArea = new HBox();
        
        centerArea.getChildren().add(newsBrief);

        HBox rightArea = new HBox();

        rightArea.getChildren().add(websiteView);

        splitPane2.getItems().add(centerArea);
        splitPane2.getItems().add(rightArea);

        // add left area
        splitPane.getItems().add(leftArea);

        // add right area
        splitPane.getItems().add(splitPane2);
        newsBrief.prefWidthProperty().bind(scene.widthProperty());
        websiteView.prefWidthProperty().bind(scene.widthProperty());
        // evenly position divider
        ObservableList<SplitPane.Divider> dividers = splitPane.getDividers();
        for (int i = 0; i < dividers.size(); i++) {
            dividers.get(i).setPosition((i + 1.0) / 3);
        }

        HBox hbox = new HBox();
        hbox.getChildren().add(splitPane);
        root.getChildren().add(hbox);
        
        stage.setScene(scene);
        stage.show();
 
        
    } // end of start()

    private static RssFeed parse(Document doc, String location) {
         
        RssFeed rssFeed = new RssFeed();
        rssFeed.link = location;
        
        rssFeed.channelTitle = doc.getElementsByTagName("title")
             .item(0)
             .getTextContent();

        NodeList items = doc.getElementsByTagName("item");
        for (int i=0; i<items.getLength(); i++){
            Map<String, String> childElements = new HashMap<>();
            NewsArticle article = new NewsArticle();
            for (int j=0; j<items.item(i).getChildNodes().getLength(); j++) {
                Node node = items.item(i).getChildNodes().item(j);
                childElements.put(node.getNodeName().toLowerCase(), node.getTextContent());
            }
            article.title = childElements.get("title");
            article.description = childElements.get("description");
            article.link = childElements.get("link");
            article.pubDate = childElements.get("pubdate");
            
            rssFeed.news.add(article);
        }
  
        return rssFeed;
    } // end of parse()
    
    private Hyperlink createHyperLink(String url, final WebEngine webEngine) {
        final Hyperlink jfxHyperLink = new Hyperlink("Loading News...");
        RssFeed aFeed = new RssFeed();
        aFeed.link = url;
        jfxHyperLink.setUserData(aFeed);
        jfxHyperLink.setOnAction((ActionEvent evt) -> {
            RssFeed rssFeed = (RssFeed)jfxHyperLink.getUserData();
            webEngine.load(rssFeed.link);
        });
        return jfxHyperLink;
    } // end of createHyperLink()
    
    public static void main(String[] args){
        DBUtils.setupDb();
        Application.launch(args);
    }
}
class RssFeed {
    int id;
    String channelTitle = "News...";
    String link;
    List<NewsArticle> news = new ArrayList<>();

    public String toString() {
        return "RssFeed{" + "id=" + id + ", channelTitle=" + channelTitle + ", link=" + link + ", news=" + news + '}';
    }
    public RssFeed() {
    }
    public RssFeed(String title, String link) {
        this.channelTitle = title;
        this.link = link;
    }
}
class NewsArticle {
    String title;
    String description;
    String link;
    String pubDate;

    public String toString() {
        return "NewsArticle{" + "title=" + title + ", description=" + description + ", link=" + link + ", pubDate=" + pubDate + ", enclosure=" + '}';
    }
    
}