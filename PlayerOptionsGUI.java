/**
Battleship!
KU EECS 448 project 2
TeamName: BigSegFaultEnergy
  * \Author: Chance Penner
  * \Author: Markus Becerra
  * \Author: Sarah Scott
  * \Author: Thomas Gardner
  * \Author: Haonan Hu
  * \File:	 PlayerOptionsGUI.java
  * \Date:   10/14/2019
  * \Brief:  This class serves as a the executive class of
             Battleship as well as making basic gui elements.

KU EECS 448 project 1
TeamName: Poor Yorick
  * \Author: Max Goad
  * \Author: Jace Bayless
  * \Author: Tri Pham
  * \Author: Apurva Rai
  * \Author: Meet Kapadia
  * \File:	 PlayerOptionsGUI.java
  * \Brief:  This class serves as a the executive class of
             Battleship as well as making basic gui elements.
*/

//Here are erternal classes that need to be imported
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;

import javafx.collections.*;
import javafx.collections.FXCollections;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

import javafx.geometry.HPos;


public class PlayerOptionsGUI implements OverScene, EventHandler<ActionEvent>
{
  //Declare variables for the labels and text fields
  private Label player1, player2, numOfShip, themeText, versus;
  private TextField name1, name2;
  private Button start;
  private String gamemode;
  private GridPane gr;
  private Scene options;
  private Button exit;
  private Label message;
  private int cols = 7, rows = 15;
  private Font f;
  private ComboBox<String> shipNum;

  /*
   * @ pre none
   * @ param string, stage, and font
   * @ post constuctor
   * @ return none
   */
  public PlayerOptionsGUI(String gamemode, Stage s, Font f)
  {
    this.gamemode = gamemode;
    this.f = f;

    //Define the variables
    player1 = new Label("Player 1");
    player1.setFont(Font.font ("Verdana", 20));
    name1 = new TextField("Player 1 Name");
    player2 = new Label("Player 2");
    player2.setFont(Font.font ("Verdana", 20));
    name2 = new TextField("Player 2 Name");

    /* https://stackoverflow.com/questions/31370478/how-get-an-event-when-text-in-a-textfield-changes-javafx/31370556 */
    /* Textfield listeners */
    name1.textProperty().addListener(new ChangeListener<String>()
    {
      @Override
      public void changed(ObservableValue<? extends String> observable,
                          String oldValue, String newValue)
      {
        String newStr = " ";
        if(newValue.length() > 15)  //Player has limited name length (15 characters)
        {
          for(int i = 0;i < 15;i++)
          {
            newStr = newStr + newValue.charAt(i);
          }
          player1.setText(newStr);
        }
        else
        {
          player1.setText(newValue);
        }
      }
    });
    name2.textProperty().addListener(new ChangeListener<String>()
    {
      @Override
      public void changed(ObservableValue<? extends String> observable,
                          String oldValue, String newValue)
      {
        String newStr = " ";
        if(newValue.length() > 15)  //Player has limited name length (15 characters)
        {
          for(int i = 0;i < 15;i++)
          {
            newStr = newStr + newValue.charAt(i);
          }
          player2.setText(newStr);
        }
        else
        {
          player2.setText(newValue);
        }
      }
    });

    //creating Board variables
    numOfShip = new Label("# of ships:");
    numOfShip.setFont(Font.font ("Verdana", 20));

    versus = new Label("versus");
    versus.setFont(Font.font ("Verdana", 20));

    message = new Label("Prepare for Battle!");
    message.setFont(Font.font ("Verdana", 20));

    //List all available ship numbers for player
    ObservableList<String> listOfShip = FXCollections.observableArrayList(
            "1",
            "2",
            "3",
            "4",
            "5");
    shipNum = new ComboBox(listOfShip);
    shipNum.setValue("1");

    //Choose theme for the game (NOT AVAILABLE in the game for now)
    themeText = new Label("Choose theme:");
    ObservableList<String> themeList = FXCollections.observableArrayList(
            "Default",
            "Temp 2",
            "Temp 3",
            "Star Wars");
    final ComboBox theme = new ComboBox(themeList);
    theme.setValue("Default");

    //Make the grid for players
    gr = new GridPane();
    for (int x = 0; x < cols; x++)
    {
      ColumnConstraints c = new ColumnConstraints();
      c.setPercentWidth(100.0 / cols);
      gr.getColumnConstraints().add(c);
    }
    for (int y = 0; y < rows; y++)
    {
      RowConstraints r = new RowConstraints();
      r.setPercentHeight(100.0 / rows);
      gr.getRowConstraints().add(r);
    }

    //Define Player options for pvp(Start and Exit)
    start = new Button("Start");
    start.setFont(Font.font ("Verdana", 20));
    exit = new Button("Exit");
    exit.setOnAction(e -> s.close());
    exit.setFont(Font.font ("Verdana", 20));
    start.setOnAction(this);


    //Define their placement in the grid
    gr.add(player1, 1, 4);
    gr.add(name1, 1, 6);

    gr.add(player2, 5, 4);
    gr.add(name2, 5, 6);

    gr.add(numOfShip, 3, 1);
    gr.add(shipNum, 3, 2);

    gr.add(start, 3, 11);
    gr.add(exit, 3, 13);
    gr.add(versus, 3, 5);
    gr.add(message, 3, 14);

    gr.setHalignment(player1, HPos.CENTER);
    gr.setHalignment(name1, HPos.CENTER);

    gr.setHalignment(player2, HPos.CENTER);
    gr.setHalignment(name2, HPos.CENTER);

    gr.setHalignment(numOfShip, HPos.CENTER);
    gr.setHalignment(shipNum, HPos.CENTER);

    gr.setHalignment(start, HPos.CENTER);
    gr.setHalignment(exit, HPos.CENTER);
    gr.setHalignment(message, HPos.CENTER);

    gr.setHalignment(versus, HPos.CENTER);

    gr.setStyle("-fx-background-color: lightslategray;");

    options = new Scene(gr);

    s.setX(0);
    s.setY(0);

  }

  /*
   * @ pre none
   * @ param none
   * @ post gets the next scene
   * @ return returns the next scene
   */
  @Override
  public Scene getScene()
  {
    return options;
  }

  /*
   * @ pre none
   * @ param action event / button pressed
   * @ post button pressed goes to next scene
   * @ return none
   */
  @Override
  public void handle(ActionEvent e)
  {
    if (e.getSource() == name1)
    {
      player1.setText(name1.getText());
    }

    if (e.getSource() == name2)
    {
      player2.setText(name2.getText());
    }

    if (e.getSource() == start)
    {
      BattleshipGUI.nextScene(new BoardGUI("pogui", BattleshipGUI.getStage(),
                              BattleshipGUI.getFont(), Integer.parseInt(shipNum.getValue()),
                              player1.getText(),player2.getText()).getScene(), 9);
    }
  }

}
