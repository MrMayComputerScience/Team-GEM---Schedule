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

        root.getStylesheets().add(Main.class.getResource("Decor.css").toExternalForm());

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

        ScrollPane sp = new ScrollPane();
        for(int x = 0; x< CheckBoxs.size(); x++)
            sp.setContent(CheckBoxs.get(x));
        sp.setContent(vbox);
        bpane.setCenter(sp);
        bpane.setBottom(hbox);
        root.getChildren().add(bpane);
        primaryStage.setScene(new Scene(root, 800, 500));
        add.setOnAction((ActionEvent e) ->
        {
            CheckBox cb = new CheckBox(field.getText());
            CheckBoxs.add(cb);
            vbox.getChildren().add(cb);
        });
        remove.setOnAction((ActionEvent e) ->
        {
            for(int x = 0; x< CheckBoxs.size(); x++)
            {
                if(CheckBoxs.get(x).isSelected())
                {
                    vbox.getChildren().remove(CheckBoxs.get(x));
                    CheckBoxs.remove(x);
                }

            }
        });
        edit.setOnAction((ActionEvent e) ->
        {
            for(int x = 0; x< CheckBoxs.size(); x++)
            {
                if(CheckBoxs.get(x).isSelected())
                {
                    CheckBox cb = new CheckBox(field.getText());
                    CheckBoxs.set(x, cb);
                    vbox.getChildren().set(x, cb);

                }

            }
            for(int x = 0; x< CheckBoxs.size(); x++)
                System.out.println(CheckBoxs.get(x).getText());
        });
        check.setOnAction((ActionEvent e) ->
        {
            for(int x = 0; x< CheckBoxs.size(); x++)
            {
                if(CheckBoxs.get(x).isSelected())
                {
                    CheckBoxs.get(x).setText("✓"+ CheckBoxs.get(x).getText());
                }

            }
        });
        up.setOnAction((ActionEvent e) ->
        {
            for(int x = 1; x< CheckBoxs.size(); x++)
            {
                    if(CheckBoxs.get(x).isSelected())
                    {
                        CheckBox cb = CheckBoxs.get(x-1);
                        CheckBoxs.set(x-1, CheckBoxs.get(x));
                        CheckBoxs.set(x, cb);
                        vbox.getChildren().removeAll(CheckBoxs);
                        for(int z = 0; z< CheckBoxs.size(); z++)
                            vbox.getChildren().add(CheckBoxs.get(z));
                    }
            }


        });
        down.setOnAction((ActionEvent e) ->
        {
            for(int x = CheckBoxs.size()-1; x>=0; x--)
            {
                if(CheckBoxs.get(x).isSelected())
                {
                    CheckBox cb = CheckBoxs.get(x+1);
                    CheckBoxs.set(x+1, CheckBoxs.get(x));
                    CheckBoxs.set(x, cb);
                    vbox.getChildren().removeAll(CheckBoxs);
                    for(int z = 0; z< CheckBoxs.size(); z++)
                        vbox.getChildren().add(CheckBoxs.get(z));
                }
            }
            for(int x = 0; x< CheckBoxs.size(); x++)
                System.out.println(CheckBoxs.get(x).getText());
        });
        btAddUser.setOnAction(e -> writeNewUser());

    }
    public void writeNewUser() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt", false))) {

            for(int x = 0; x< CheckBoxs.size(); x++)
            {
                bw.write(CheckBoxs.get(x).getText());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
