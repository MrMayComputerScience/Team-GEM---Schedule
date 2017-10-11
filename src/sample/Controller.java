package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import java.util.List;

public class Controller
{
    @FXML List<CheckBox> test;
    @FXML Button addcheck;

    @FXML protected void handleSubmitButtonAction(ActionEvent event)
    {
        if(addcheck.isPressed())
        {
            test.add(new CheckBox("Click me to change"));
        }

        for(int x = 0; x < test.size(); x++)
        {
            if(test.get(x).isSelected())
            {

            }
        }
    }
    @FXML public List<CheckBox> getTest()
    {
        return test;
    }

}
