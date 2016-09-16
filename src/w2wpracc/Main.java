/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Raphaël
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* First step : create the cache folder / file*/
        CacheDb cacheDb = new CacheDb();
        // If DB file (for cache) doesn't exists, we will create one
        if(!cacheDb.exists()) {
            cacheDb.create();
        }else{
            System.out.println("The cache file already exists.");
        }
        
        /* After : Launch the window */
        launch(args);  
        
    }
}
