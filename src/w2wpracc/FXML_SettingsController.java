/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raphaël
 */
public class FXML_SettingsController implements Initializable {

    @FXML
    private TextField pathTextField;
    @FXML
    private Button browseButton;
    @FXML
    private Button pathButton;
    @FXML
    private Label instructionLabel;
    
    private String chosenPath;
    private UserPreferences prefs = new UserPreferences();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(! this.prefs.getPath().equals("")) {
            this.pathTextField.setText(this.prefs.getPath());
        }
    }    

    @FXML
    private void browseFolders(ActionEvent event) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage s1 = new Stage();
        
        configureDirectoryChooser(directoryChooser);
        File file = directoryChooser.showDialog(s1);
        if (file != null) {
            this.chosenPath = file.getAbsolutePath();
            pathTextField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void setPath(ActionEvent event) {
        this.prefs.savePath(this.chosenPath);
    }
    
    private static void configureDirectoryChooser(final DirectoryChooser directoryChooser) {      
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
