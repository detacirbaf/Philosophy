// *** Lacifitra.com ***

package application;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class quotesofphilosophy extends Application {
  private static final String[] Quotes = { 
    "True happiness is to enjoy the present,\n" + "without anxious dependence upon the future,\n" + "not to amuse ourselves with either hopes\n" + "or fears but to rest satisfied\n" + "with what we have, which is sufficient, for he that\n" + "is so wants nothing. The greatest blessings\n" + "of mankind are within us and within our reach.\n"
    + "A wise man is content with his lot,\n" + "whatever it may be, without wishing for\n" + "what he has not. ― Seneca"
    ,
    "You are a simpleton, Hegesias; \n" + "you do not choose painted figs,\n" +" but real ones; and yet you\n" +  "pass over the true training and would\n" + "apply yourself to written rules\n" + "― Diogenes of Sinope"
    ,
    "Be kind, for everyone you meet\n" + "is fighting a harder battle.\n" + 
    "― Plato",
    "Man is the only creature who refuses\n"  + "to be what he is.\n" + "― Albert Camus",
    "You have power over your mind - not outside events.\n" + "Realize this, and you will find strength.\n" + "― Marcus Aurelius",
    "The soul becomes dyed with the colour of its thoughts.\n" + "― Marcus Aurelius",
    "I cannot teach anybody anything. I can only make them think.\n" + "― Socrates",
    "By all means marry; if you get a good wife,\n" +  "you’ll become happy; if you get a bad one,\n"  + "you’ll become a philosopher.\n" + "― Socrates",
    "I am the wisest man alive,\n" + " for I know one thing, and that is\n" + " that I know nothing.\n" + "― Plato",
    "As a well-spent day brings happy sleep, so a life well spent\n" + "brings happy death.\n" + "― Leonardo Da Vinci",
    "At the bottom of enmity between strangers lies indifference.\n" + 
    "―  Soren Kierkegaard",
    "Wise to resolve, and patient to perform.\n" + 
    "― Homer",
    
    
    
    
  };
  int philosophyIndex = 0;
  public static void main(String[] args) throws Exception { launch(args); }
  public void start(final Stage stage) throws Exception {
    stage.setTitle("lacifitra.com");
    

    final Text philosophy = new Text(Quotes[philosophyIndex]);

    philosophy.setFill(Color.WHITE);
    philosophy.setTextAlignment(TextAlignment.CENTER);
    philosophy.setStyle("-fx-font: 22 impact; -fx-base:  transparent;");
    


    final Button changeQuotes = new Button("Get a different Quote");
    changeQuotes.setStyle("-fx-font: 22 impact; -fx-base:  transparent;");
    changeQuotes.setMaxWidth(Double.MAX_VALUE);
    changeQuotes.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent actionEvent) {
        philosophyIndex++;
        philosophy.setText(Quotes[philosophyIndex % 25]);
      }
    });
    
  
    
 
    
    final BorderPane philosophyPane = new BorderPane();
    philosophyPane.setCenter(philosophy);
    philosophyPane.setBottom(changeQuotes);

  
    SideBar sidebar = new SideBar(philosophyPane);
    VBox.setVgrow(philosophyPane, Priority.ALWAYS);
    VBox.setVgrow(sidebar,   Priority.ALWAYS);
    sidebar.setMinWidth(350);

  
    
 
    final StackPane layout = new StackPane();
    
   
    layout.setStyle("-fx-background-color: #CCFF99; -fx-font-size: 20; -fx-padding: 10; -fx-background-image: url('https://dailystoic.com/wp-content/uploads/2016/07/seneca.jpg');  -fx-background-size: cover; ");
    		  
    layout.getChildren().addAll(
      HBoxBuilder.create().spacing(10)
        .children(
          VBoxBuilder.create().spacing(10)
            .children(
              sidebar.getControlButton(),
              sidebar
            ).build()
          // webView
        ).build()
    );
    Scene scene = new Scene(layout);
    
    stage.setScene(scene);
    stage.show();
  }

  class SideBar extends VBox {
  
    public Button getControlButton() { return controlButton; }
    private final Button controlButton;

    SideBar(Node... nodes) {
   
      setAlignment(Pos.CENTER);
      setStyle("-fx-padding: 10; -fx-background-color: transparent; -fx-border-color: transparent; -fx-border-width: 3;");
      getChildren().addAll(nodes);
      controlButton = new Button("Hide");
      controlButton.setMaxWidth(Double.MAX_VALUE);
      controlButton.setTooltip(new Tooltip("Play sidebar hide and seek"));
      controlButton.setStyle("-fx-font: 22 impact; -fx-base:  transparent;");
   
      
      controlButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent actionEvent) {
       
          final double startWidth = getWidth();
          final Animation hideSidebar = new Transition() {
            { setCycleDuration(Duration.millis(250)); }
            protected void interpolate(double frac) {
              final double curWidth = startWidth * (1.0 - frac);
              setTranslateX(-startWidth + curWidth);
            }
          };
          hideSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
              setVisible(false);
              controlButton.setText("Show");
              controlButton.setStyle("-fx-font: 22 impact; -fx-base:  transparent;");
            }
          });
  
       
          final Animation showSidebar = new Transition() {
            { setCycleDuration(Duration.millis(250)); }
            protected void interpolate(double frac) {
              final double curWidth = startWidth * frac;
              setTranslateX(-startWidth + curWidth);
            }
          };
          showSidebar.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
              controlButton.setText("Hide");
            }
          });
  
          if (showSidebar.statusProperty().get() == Animation.Status.STOPPED && hideSidebar.statusProperty().get() == Animation.Status.STOPPED) {
            if (isVisible()) {
              hideSidebar.play();
            } else {
              setVisible(true);
              showSidebar.play();
            }
          }
        }
      });
    }
  }
}
