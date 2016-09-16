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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private Button settingsButton;
    
    private UserPreferences prefs = new UserPreferences();
    
    @FXML
    private Button button;
    @FXML
    private Label dateLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label directorsLabel;
    @FXML
    private Label actorsLabel;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<?> movieListView;
    @FXML
    private ImageView movieImageView;
    @FXML
    private Label genreLabel;
    @FXML
    private Label dateValueLabel;
    @FXML
    private Label genreValueLabel;
    @FXML
    private Label directorsValueLabel;
    @FXML
    private Label actorsValueLabel;
    @FXML
    private Label titleValueLabel;
    @FXML
    private ComboBox<?> categoryComboBox;
    
    private ObservableList movies = 
        FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Checking whether the user set a path refering to his movie folder
        if(this.prefs.getPath().equals("")) {
            try {
                showSettings(null);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    

    @FXML
    private void browseFiles(ActionEvent event) throws IOException {
        String path = this.prefs.getPath();
        if(!path.equals("")) {
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
                                movies.add(o.getFileName().toString());
                                break;
                            }
                        }
                    });
            
            Iterator<String> it = movieFiles.iterator();
            
            movieListView.setItems(movies);

            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
    }

    @FXML
    private void showSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Settings.fxml"));
        Stage settingStage = new Stage();
        settingStage.setResizable(false);
        settingStage.initModality(Modality.APPLICATION_MODAL);
        settingStage.setTitle("Sélection du répertoire de films");
        
        Scene scene = new Scene(root);
        settingStage.setScene(scene);
        settingStage.showAndWait();
    }
}
