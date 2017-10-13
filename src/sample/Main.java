package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.print.PrinterJob;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application
{
    private BorderPane bp;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("sample.fxml"));
        StackPane root = new StackPane();
        primaryStage.setTitle("GEM's To-Do List");
        bp = new BorderPane();
        root.getChildren().add(bp);
        primaryStage.setScene(new Scene(root, 300, 275));
        //print(new Circle(100));
        Printer x = Printer.getDefaultPrinter();
        choosePrinter(x);
        primaryStage.show();
    }
    public void fileChooser()
    {

    }
    public void print(Node x)
    {
        Printer y = Printer.getDefaultPrinter();
        choosePrinter(y);
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(x);
            if (success) {
                job.endJob();
            }
        }
    }
    public void choosePrinter(Printer print)
    {
        ChoiceBox<Printer> t = new ChoiceBox();


        Button butt = new Button();
        Stage printer = new Stage();
        StackPane root = new StackPane();

        t.getItems().add(Printer.getDefaultPrinter());

        for (Printer x : Printer.getAllPrinters()) {
            t.getItems().add(x);
        }
        //t = Printer.getAllPrinters();
        root.getChildren().add(t);
        printer.setScene(new Scene(root, 300, 275));
        printer.show();
        root.getChildren().add(butt);
        butt.setOnAction((ActionEvent e) -> {
            printer.close();

        });
        if(butt.isPressed()) {
            print = t.getValue();
        }

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
