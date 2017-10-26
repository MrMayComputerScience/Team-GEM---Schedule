package sample;
import com.sun.xml.internal.ws.server.sei.MessageFiller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.PrintResolution;
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
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.print.PrinterJob;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main extends Application
{
    
    private ArrayList<String> names =new ArrayList<String>(); //list of all the names
    private ArrayList<String> col = new ArrayList<String>(); //list of all the columns
    private ArrayList<String> sorted = new ArrayList<String>(); //list of names sorted
    private BorderPane bp;
    private ArrayList<String> header = new ArrayList<String>();// the header
    private int columns = 0;
    int rows = 0;
    private GridPane gb1;
    private Printer printer;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Stage stg = new Stage();
         BorderPane Bp = new BorderPane();
         VBox hbox = new VBox();
         hbox.getChildren().add(new Text("Header: "));
         hbox.getChildren().add(new Text("Names: "));
         hbox.getChildren().add(new Text("Colum: "));
         Bp.setLeft(hbox);
         VBox hBox = new VBox();
         Button buttH = new Button("Choose Header");
         hBox.getChildren().add(buttH);
        Button buttN = new Button("Choose Names");
        hBox.getChildren().add(buttN);
        Button buttC = new Button("Choose Columns");
        hBox.getChildren().add(buttC);
        buttH.setOnAction((ActionEvent e) -> {
            readHeader();
            buttH.setText("File Chosen");
        });
        buttC.setOnAction((ActionEvent e) -> {
            readColumns();
            buttC.setText("File Chosen");
        });
        buttN.setOnAction((ActionEvent e) -> {
            reorganize();
            buttN.setText("File Chosen");
        });
        Button buttG = new Button("Create Grid");
        buttG.setOnAction((ActionEvent e) -> {
            if(!(names == null)&&!(col == null)&&!(header == null))
                createGrid();

        });
        hbox.getChildren().add(buttG);
        Button print = new Button("Print Grid");
        hBox.getChildren().add(print);
        print.setOnAction((ActionEvent e) -> {
            if(!(gb1==null))
             print(gb1);

        });
        Button buttS = new Button("Save Grid");
        buttS.setOnAction((ActionEvent e) -> {
            if(!(gb1==null))
                saveFile();
        });
        hbox.getChildren().add(buttS);
        Bp.setRight(hBox);
        Bp.setTop(new Text("Schedule Maker"));
        stg.setScene(new Scene(Bp,200,200));
        stg.show();
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
    public void print(GridPane x)
    {

        StackPane p = new StackPane();
        //p.getChildren().add(x);
        printer = Printer.getDefaultPrinter();

        choosePrinter(printer);
        PrinterJob job = PrinterJob.createPrinterJob(printer);
        //job.showPrintDialog(new Stage());
        boolean success1 = job.showPageSetupDialog(new Stage());
        if(success1) {
            double width = x.getWidth();
            double height = x.getHeight();

// Convert to inches
            PrintResolution resolution = job.getJobSettings().getPrintResolution();
            width /= resolution.getFeedResolution();
            height /= resolution.getCrossFeedResolution();

            //double scaleX = job.getJobSettings().getPageLayout().getPrintableWidth() / 72 / width;
            //double scaleY = job.getJobSettings().getPageLayout().getPrintableHeight() / 72 / height;
            double scaleX
                    = job.getJobSettings().getPageLayout().getPrintableWidth() / x.getBoundsInParent().getWidth();
            double scaleY
                    = job.getJobSettings().getPageLayout().getPrintableHeight() / x.getBoundsInParent().getHeight();
            Scale scale = new Scale(scaleX, scaleY);
            x.getTransforms().add(scale);
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
            setPrinter(t.getValue());
            printer.close();

        });


    }
    public void readNames()//modify to use explorer(also read the organize method just in case)
    {
        String[] lines;
        String[] lines1;
        ArrayList<String> temp = new ArrayList<String>();
        String line2;

        System.out.println("Choose the file that contains the row list you want."); //have gui display this

        try (Scanner s = new Scanner(new File(fileChooser()));)
        {

            String line;
            while (s.hasNextLine())
            {
                System.out.println(s);
                line = s.next();
                line2 = s.next();

                //for(int i = 0;i<lines.length;i++)
                //{
                //    temp.add(lines[i]);
                //}

                //if(temp.size()>0 && (temp.get(0)!=null && temp.get(1)!=null))
                //{
                    //if(lines.length==3)
                        names.add(line+ " "+ line2);
                    //else
                    //    names.add(lines[0]+ " "+ lines1[0]);
                //}
                //System.out.println(line);
                rows++;
                temp = new ArrayList<String>();
                s.nextLine();
            }

        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
            e.printStackTrace();
        }
    }
    public void setPrinter(Printer x)
    {
        printer = x;
    }
    public void readColumns()//modify to use explorer
    {
        String[] lines;

        System.out.println("Choose the file that contains the column list you want."); //have gui display this

        try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser())))
        {

            String line;
            while ((line = reader.readLine()) != null)
            {
                lines = line.split("\n");
                for(int i = 0;i<lines.length;i++)
                {
                    col.add(lines[i]);
                    columns++;
                }

            }

        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
            e.printStackTrace();
        }
    }

    public void readHeader()//modify to use explorer
    {
        //Notifies the user what the FileChooser is for
        System.out.println("Choose the file that contains your custom header.");// have gui display this

        //Attempts to read the file chosen by the user
        try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser())))
        {
            //Initializes a String as the first line
            String line = "";

            //Loops until the line is not null nor empty
            while(line != null && line.equals(""))
            {
                line = reader.readLine();

                //If the line is not null nor empty
                if (line != null && !line.equals(""))
                {
                    //Set the header as that line and break
                    header.add(line);
                    System.out.println(header);

                    break;
                }

            }

            //Backup if the file is empty
            if(line == null)
            {
                header.add("Header");
            }

        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
            e.printStackTrace();
        }
    }

    public void reorganize()
    {
        readNames();
        java.util.Collections.sort(names);
        for(int i = 0;i<names.size();i++)
        {
            String[] small = names.get(i).split(" ");
            sorted.add(small[1] + " " + small[0]);
            System.out.println(sorted.get(i));
        }
    }
    public void saveFile()
    {
        try
        {
            //Asks the user for the desired name for the file
            System.out.println("Type in the name that you want for your new file."); //have gui display this
            Scanner input = new Scanner(System.in);
            String filename = input.next();

            //Notifies the user what the FileChooser is for
            System.out.println("Choose the directory to store the file."); //have gui display this

            //If the file exists, the file is overwritten
            //If the file doesn't exist, the file is created
            File toSave = new File(fileChooser());
            if(!toSave.exists()) toSave.createNewFile();

            //Initializes the classes that will write to the file
            FileWriter fr = new FileWriter(toSave + "/" + filename);
            BufferedWriter br = new BufferedWriter(fr);

            //Writes the header
            for(int h = 0; h < header.size(); h++)
                           {
                                        br.write(header.get(h));
                            br.newLine();
                        }
            br.write("--------------------");
            br.newLine();
            br.write("                    ");

            //Writes the columns
            for(int c = 0; c < columns; c++)
            {
                br.write("  " + col.get(c));
            }

            //Writes the names (or rows)
            for(int n = 0; n < rows; n++)
            {
                br.newLine();
                br.write(sorted.get(n));
            }

            //Closes the streams
            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void createGrid()
    {
        ArrayList<String> nam = names;
        ArrayList<String> cols = col;
        Stage grid = new Stage();
        StackPane sp = new StackPane();
        sp.setStyle("-fx-background-color: #FFFFFF");
        GridPane gb = new GridPane();

        gb.setStyle("-fx-background-color: #FFFFFF");
        //int mC = header;
        ColumnConstraints column = new ColumnConstraints();

        for (int i = 0; i < nam.size(); i++) {


            gb.getColumnConstraints().add(column);
            System.out.println(gb.getWidth());
            System.out.println(gb.getHeight());
        }

        gb.setPrefSize(100,100);




        //gb.add(new Text("test"),0,0);

        gb.add(new Pane(),0,1);
        RowConstraints row = new RowConstraints();
        gb.getRowConstraints().add(row);
        gb.getRowConstraints().add(row);
        for(int x = 0;x<col.size();x++)
        {
            gb.getRowConstraints().add(row);
            Pane pan = new Pane();
            Button but = new Button(col.get(x));

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
            String tem = "";
            for(int x = 0;x<header.size();x++)
                tem += header.get(x);
            if(i==0)
                pan.getChildren().add(new Button(tem));
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
            but.getHeight();


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



        gb.setMaxHeight(gb.getBoundsInParent().getHeight());
        gb.setMaxWidth(gb.getBoundsInParent().getWidth());
        gb.getBoundsInParent().getWidth();
        sp.getChildren().add(gb);
        sp.setMaxSize(gb.getWidth(),gb.getHeight());
        grid.setScene(new Scene(sp, gb.getMaxWidth(), gb.getHeight()));

        grid.show();
        gb1 = gb;

    }
    public static void main(String[] args)
    {
        launch(args);
    }

}
