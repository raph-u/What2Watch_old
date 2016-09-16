/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

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
        
        
        /* Move later (get Json) */
        try {
            JSONObject json = ParsingJSON.readJsonFromUrl("http://www.omdbapi.com/?t=titanic&y=&plot=full&r=json");
            String title = json.get("Title").toString();
            String director = json.get("Director").toString();
            String actors = json.get("Actors").toString();
            String year = json.get("Year").toString();
            String poster = json.get("Poster").toString();
            String genre = json.get("Genre").toString();
        } catch (JSONException ex) {
            System.out.println("ERROR on parsingJSON (JSON exception) : "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR on parsingJSON (IO exception) : "+ex.getMessage() + "\nVeuillez vérifier votre connexion internet");
        }
        
                
        
        /* After : Launch the window */
        launch(args);  
        
    }
}
