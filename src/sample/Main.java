package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.print.PrinterJob;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application
{
    private ArrayList<String> names;
    private int column;
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

        primaryStage.show();
    }
    public void fileChooser()
    {

    }
    public void print(Node x)
    {

        PrinterJob job = PrinterJob.createPrinterJob(choosePrinter());
        if (job != null) {
            boolean success = job.printPage(x);
            if (success) {
                job.endJob();
            }
        }
    }
    public Printer choosePrinter()
    {


        return Printer.getDefaultPrinter();
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
