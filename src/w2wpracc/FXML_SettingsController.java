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
    
    @FXML
    private Label error_emptyPathLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Filling the textfield with the saved movie folder path
        if(! this.prefs.getPath().equals("")) {
            this.pathTextField.setText(this.prefs.getPath());
        }
        
        //this.prefs.removePath();
    }    

    @FXML
    private void browseFolders(ActionEvent event) {
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage s1 = new Stage();
        
        configureDirectoryChooser(directoryChooser);
        File file = directoryChooser.showDialog(s1);
        if (file != null) {
            this.pathTextField.setText(file.getAbsolutePath());
        }
    }

    @FXML
    private void setPath(ActionEvent event) {
        // Making sure the user specified a path for his movie folder
        if (!this.pathTextField.getText().equals("")) {
            this.prefs.savePath(this.pathTextField.getText());
            this.error_emptyPathLabel.setVisible(false);
            
            // Closing the settings window
            Stage stage = (Stage) pathButton.getScene().getWindow();
            stage.close();
        } else {
            this.error_emptyPathLabel.setVisible(true);
        }
    }
    
    private static void configureDirectoryChooser(final DirectoryChooser directoryChooser) {      
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
