/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author David.ROSAT
 */
public class ParsingFiles {
    

    public static String simplify_name(String name){
      String listExcludeExtension = ".mkv|.avi|.m4v";
      String listExcludeTypeExtension = "Mkv";
      String listExcludeLanguage = "FRENCH|VOSTA|VOSTFR|VF";
      String listExcludeTeam = "Ganesh-AC3";
      String listExcludeFileResolution = "720p|1080p";        
      String listExcludeTypeRecord = "BluRay";
      String listExcludeTypePonctuation = "\\.|-|;|,";
      String listExclude = listExcludeExtension +"|" + 
                           listExcludeTypeExtension + "|" +
                           listExcludeLanguage + "|" +
                           listExcludeTeam + "|" +
                           listExcludeFileResolution + "|" +
                           listExcludeTypeRecord  + "|" +
                           listExcludeTypePonctuation;
                          
      String result = name.replaceAll("("+listExclude+")"," ");  
      // Now create matcher object.
      return result;
    }
      public static String removeNameDate(String name){
      
           // String to be scanned to find the pattern.
      String result = name;
      String pattern = "(\\d{4})";

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(name);
      if (m.find( )) {
   
         result = result.replaceAll("("+m.group(0)+")","");
         
      } else {
         System.out.println("No year on file");
      }
      
      return result;
    }
    public static String removeAfterDoubleSpace(String name){
       
      String[] tab = name.split("  ");
      
      int i=0;
      while(tab[i].equals("")  && i < tab.length)
      {
         i=i + 1;
         
      }
         
      return tab[i];        
    }
    public static ArrayList<String> parse(String listFiles)
    {
      String[] l = listFiles.split(";");
      ArrayList<String> retour = new ArrayList<String>(); 
      for(int i = 0; i < l.length; i++)
      {
            String simplifiedName = simplify_name(l[i]);
            String name = removeNameDate(simplifiedName);
            System.out.println("Oname : "+l[i]);
            name = removeAfterDoubleSpace(name);
            System.out.println("name : "+name);
            retour.add(name);
      }
      return retour;
    }
}