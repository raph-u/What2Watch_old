/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Raphaël
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button pathButton;
    
    private UserPreferences prefs = new UserPreferences();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void browseFiles(ActionEvent event) throws IOException {
        String path = this.prefs.getPath();
        if (!path.equals("")) {
            String[] extensions = {".avi", ".mkv", ".mpeg", ".wmv", ".m4v", ".mp4", ".flv"};
            ArrayList<String> movieFiles = new ArrayList<String>();

            Files.find(Paths.get(path),
                    Integer.MAX_VALUE,
                    (filePath, fileAttr) -> fileAttr.isRegularFile())
                    .forEach((o) -> {
                        // Get rid of this (loop complexity)
                        for (int i = 0; i < extensions.length; i++) {
                            if (o.getFileName().toString().endsWith(extensions[i])) {
                                movieFiles.add(o.getFileName().toString());
                                break;
                            }
                        }
                    });
            
            Iterator<String> it = movieFiles.iterator();

            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
    }

    @FXML
    private void showSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Settings.fxml"));
        Stage settingStage = new Stage();
        settingStage.initModality(Modality.APPLICATION_MODAL);
        settingStage.setTitle("Sélection du répertoire de films");
        
        Scene scene = new Scene(root);
        settingStage.setScene(scene);
        settingStage.showAndWait();
    }
}
