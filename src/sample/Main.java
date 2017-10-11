package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application
{

    private List<CheckBox> CheckBoxs;
    private Button btAddUser = new Button("Save");
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        StackPane root = new StackPane();
        CheckBoxs = new ArrayList<CheckBox>();



        primaryStage.setTitle("GEM's To-Do List");

        //root.getStylesheets().add(Main.class.getResource("Decor.css").toExternalForm());

        primaryStage.show();

        //Controller controller = loader.getController();
        BorderPane bpane = new BorderPane();

        VBox vbox = new VBox();

        HBox hbox = new HBox();
        TextField field = new TextField("");
        Button add = new Button("add");
        Button edit = new Button("edit");
        Button remove = new Button("remove");
        Button check = new Button("Check off");
        Button up = new Button("Move Up");
        Button down = new Button("Move Down");
        hbox.getChildren().addAll(add,field,edit,remove,check,up,down,btAddUser);

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("todo.txt")))) {

            String line;
            while ((line = reader.readLine()) != null)
            {
                CheckBox cb1 = new CheckBox(line);
                CheckBoxs.add(cb1);
                vbox.getChildren().add(cb1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void fileChooser()
    {

    }
    public void print()
    {

    }
    public void choosePrinter()
    {

    }
    public void readFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("todo.txt")))) {

            String line;
            while ((line = reader.readLine()) != null)
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile()
    {

    }
    public void saveFile()
    {

    }
    public void createGrid()
    {
        
    }
    public static void main(String[] args)
    {
        launch(args);
    }

}
