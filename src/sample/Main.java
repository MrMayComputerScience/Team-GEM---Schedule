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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application
{
    private File students;
    private File header;
    private File columns;

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

        students = fileChooser();
        header = fileChooser();
        columns = fileChooser();

    }
    public File fileChooser()
    {
        //Creates the JFileChooser and bases its selection on the OS directory
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        //Limits the JFileChooser to select only files and only one file at a time
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
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
            File chosen = fc.getSelectedFile();
            return chosen;
        }

        return null;
    }
    public String pathChooser()
    {
        //Creates the JFileChooser and bases its selection on the OS directory
        JFileChooser dc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        //Limits the JFileChooser to select only directories and only one directory at a time
        dc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dc.setMultiSelectionEnabled(false);

        //Gets the hash int for the selected directory
        int returnValue = dc.showOpenDialog(null);

        //If the hash means the directory is accepted, return the path to the directory
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            String chosen = dc.getSelectedFile().getAbsolutePath();
            return chosen;
        }

        return null;
    }
    public void print()
    {

    }
    public void choosePrinter()
    {

    }
    public List readFile(File toRead)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("todo.txt")))) {

            String line;
            while ((line = reader.readLine()) != null)
            {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }
    public void writeFile()
    {

    }
    public void saveFile()
    {
        try
        {
            //If the file exists, the file is overwritten
            //If the file doesn't exist, the file is created
            File toSave = new File(pathChooser());
            if(!toSave.exists()) toSave.createNewFile();

            //Initializes the classes that will write to the file
            FileWriter fr = new FileWriter(toSave);
            BufferedWriter br = new BufferedWriter(fr);

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
