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
import java.util.Scanner;

public class Main extends Application
{

    private ArrayList<String> names;
    private int column;
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
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Controller controller = loader.getController();
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

        int column = 0;
        ArrayList<String> names = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.print("enter file name.");
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(sc.next())))) {

            String line;
            while ((line = reader.readLine()) != null)
            {
                names.add(line);
                System.out.println(line);
                column++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.names = names;
        this.column = column;
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
