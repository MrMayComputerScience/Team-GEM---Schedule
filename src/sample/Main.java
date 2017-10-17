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
import java.util.regex.*;

public class Main extends Application
{
    private ArrayList<String> names =new ArrayList<String>();
    private int column;
    private BorderPane bp;
    private String header;
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
        //readNames();
        //readHeader();
        readColumns();
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
    public void readNames()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter file path.");
        String[] lines;
        ArrayList<String> temp = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(sc.next()))))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                    lines = line.split(" ");
                    for(int i = 0;i<lines.length;i++)
                    {
                        temp.add(lines[i]);
                    }
                    if(temp.size()>0 && (temp.get(0)!=null && temp.get(1)!=null))
                    names.add(lines[0] + " " + lines[1]);
                    //System.out.println(line);
                    column++;
            }
            for(int i = 0; i<names.size();i++)
            {
                System.out.println(names.get(i));
            }
        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
            e.printStackTrace();
        }
    }

    public void readColumns()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter file path.");
        String[] lines;
        ArrayList<String> temp = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(sc.next()))))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                lines = line.split("\n");
                for(int i = 0;i<lines.length;i++)
                {
                    temp.add(lines[i]);
                }

            }
            for(int i = 0; i<temp.size();i++)
            {
                System.out.println(temp.get(i));
            }
        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
            e.printStackTrace();
        }
    }

    public void readHeader()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter file path.");
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(sc.next()))))
        {
            String line;
            if((line = reader.readLine()) != null)
            {
                header =line;
                System.out.println(header);
            }
        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
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
