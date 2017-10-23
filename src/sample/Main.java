package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.Printer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.print.PrinterJob;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
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
    public String fileChooser()
    {
        //Creates the JFileChooser and bases its selection on the OS directory
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        //Limits the JFileChooser to select only files and only one file at a time
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setMultiSelectionEnabled(false);

        //Limits the JFileChooser to select only text files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filter);

        //Gets the hash int for the selected file
        int returnValue = fc.showOpenDialog(null);

        //If the hash means the File is accepted, store the File
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            String chosen = fc.getSelectedFile().getAbsolutePath();
            return chosen;
        }

        return null;
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
        gb.setStyle("-fx-background-color: #FFFFFF");

        for (int i = 0; i < nam.size(); i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            gb.getColumnConstraints().add(column);
            System.out.println(gb.getWidth());
            System.out.println(gb.getHeight());
        }

        gb.setPrefSize(100,100);

        sp.getChildren().add(gb);
        sp.setMaxSize(gb.getWidth(),gb.getHeight());


        gb.add(new Text("test"),0,0);

        gb.add(new Pane(),0,1);
        RowConstraints row = new RowConstraints(25);
        gb.getRowConstraints().add(row);
        gb.getRowConstraints().add(row);
        for(int x = 0;x<nam.size();x++)
        {
            gb.getRowConstraints().add(row);
            Pane pan = new Pane();
            Button but = new Button(nam.get(x));

            pan.getChildren().add(but);
            pan.resize(50,100);

            gb.add(pan,x+1,1);


        }
        for(int i = 0; i <gb.getRowConstraints().size(); i++)
        {
            Pane pan = new Pane();
            pan.resize(50,100);
            //but.setStyle("-fx-background-color: #000000");
            pan.setStyle("-fx-background-color: #FFFFFF");
            if(i==0)
                pan.getChildren().add(new Button("test"));
            gb.add(pan,i,0);
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
        gb.setPadding(new Insets(1));
        gb.setStyle("-fx-background-color: #000000;");
        for(Node n: gb.getChildren())
        {
            n.setStyle("-fx-background-color: #FFFFFF;");
        }

        gb.setVgap(1);
        gb.setHgap(1);

        gb.setMaxHeight(gb.getRowConstraints().size()*25);
        gb.setMaxWidth(gb.getColumnConstraints().size()*50+50);
        grid.setScene(new Scene(sp, gb.getColumnConstraints().size()*50+50, gb.getRowConstraints().size()*25));

        grid.show();


    }
    public static void main(String[] args)
    {
        launch(args);
    }

}
