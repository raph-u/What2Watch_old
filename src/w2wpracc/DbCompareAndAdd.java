/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David.ROSAT
 */
public class DbCompareAndAdd {
    private CacheDb database;
   public DbCompareAndAdd(CacheDb db){
        database = db;
        if(isMovieExist("Titanic"))
            System.out.println("titanic Existe");
      else
            System.out.println("titanic Existe pas");
            
         if(isMovieExist("blalbla"))
            System.out.println("blalbla Existe");
        else
            System.out.println("blalbla Existe pas");
   }
   public boolean isMovieExist(String movieName){
          
    String sql = "SELECT id, title FROM movies WHERE title like \""+movieName+"\"";


    String rs = database.doSqlQuerry(sql);
    System.out.println(rs);
    // loop through the result set¨$
    if(rs.equals("") == true)
        return false;
    else
        return true;        
    }
   
    public static String getJsonOnWeb(String movieName) throws IOException 
    {
        InputStream is = null;
        URL url;  
        BufferedReader br = null;
        String retour ="";
        try{    
            url = new URL("http://www.omdbapi.com/?s="+movieName+"&r=json");
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));
        } catch (MalformedURLException mue){
            mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        }finally{
            try {
                
                String line;
                while((line = br.readLine())!=null)
                {
                    retour +=line;
                }
               
            if(is != null)
                is.close();
            } catch (IOException ex) {
                
            }
        }
       return retour; 
    } 
    
    
    
}
