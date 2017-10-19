package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

        primaryStage.show();
        createGrid();

    }
    public void fileChooser()
    {

    }
    public void print(Node x)
    {

        Printer y = Printer.getDefaultPrinter();
        choosePrinter(y);
        PrinterJob job = PrinterJob.createPrinterJob(y);
        boolean success1 = job.showPageSetupDialog(new Stage());
        if(success1) {
            if (job != null) {
                boolean success = job.printPage(x);
                if (success) {
                    job.endJob();
                }
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
        ArrayList<String> nam = new ArrayList<String>();
        nam.add("Gavin");
        nam.add("Kevin");
        nam.add("evan");
        Stage grid = new Stage();
        StackPane sp = new StackPane();
        sp.setStyle("-fx-background-color: #FFFFFF");
        GridPane gb = new GridPane();
        GridPane gridpane = new GridPane();
        for (int i = 0; i < nam.size(); i++) {
            ColumnConstraints column = new ColumnConstraints(100);
            gridpane.getColumnConstraints().add(column);
            System.out.println(gb.getWidth());
            System.out.println(gb.getHeight());
        }
        gb.setPrefSize(100,100);
        //gb.set
        //sp.getChildren().add(gb);


        /*
        gb.add(new Text("test"),0,0);

        gb.add(new Pane(),0,1);
        for(int x = 0;x<nam.size();x++)
        {
            Pane pan = new Pane();
            Button but = new Button(nam.get(x));

            pan.getChildren().add(but);
            pan.resize(50,100);

            gb.add(pan,x+1,1);


        }
        for(int x =0;x<nam.size();x++)
        {
            Pane pan = new Pane();
            pan.resize(50,100);
            //but.setStyle("-fx-background-color: #000000");
            pan.setStyle("-fx-background-color: #FFFFFF");
            Button but = new Button(nam.get(x));
            pan.getChildren().add(but);

            gb.add(pan,0,x+2);
            for(int z = 1;z<nam.size()+2;z++)
            {
                gb.add(new Pane(),z,x+2);
            }
        }

        gb.setStyle("-fx-background-color: #000000;");
        for(Node n: gb.getChildren())
        {
            n.setStyle("-fx-background-color: #FFFFFF;");
        }
        */
        gb.setVgap(1);
        gb.setHgap(1);
        grid.setScene(new Scene(gb, gb.getWidth(), gb.getHeight()));
        grid.show();


    }
    public static void main(String[] args)
    {
        launch(args);
    }

}
