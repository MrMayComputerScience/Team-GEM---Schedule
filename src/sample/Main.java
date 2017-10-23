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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Main extends Application
{

    private ArrayList<String> names =new ArrayList<String>(); //list of all the names
    private ArrayList<String> col = new ArrayList<String>(); //list of all the columns
    private ArrayList<String> sorted = new ArrayList<String>(); //list of names sorted
    private BorderPane bp;
    private String header;// the header
    private int columns = 0;
    int rows = 0;

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
        reorganize();
        readHeader();
        readColumns();
        saveFile();
        primaryStage.show();
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

    public void readNames()//modify to use explorer(also read the organize method just in case)
    {
        String[] lines;
        ArrayList<String> temp = new ArrayList<String>();

        System.out.println("Choose the file that contains the row list you want."); //have gui display this

        try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser())))
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
                {
                    names.add(lines[1] + " " + lines[0]);
                }
                //System.out.println(line);
                rows++;
            }

        }
        catch (IOException e)
        {
            System.out.println("Invalid file path.Please try again.");
            e.printStackTrace();
        }
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
                    header = line;
                    System.out.println(header);

                    break;
                }

            }

            //Backup if the file is empty
            if(line == null)
            {
                header = "Header";
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

    public void writeFile()
    {

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
            br.write(header);
            br.newLine();
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

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

