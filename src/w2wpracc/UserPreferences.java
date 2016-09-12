/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w2wpracc;

import java.util.prefs.Preferences;

/**
 *
 * @author Raphaël
 */
public class UserPreferences {
    private Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
  
  public void savePath(String path) {
      prefs.put("MovieFolderPath", path);
      System.out.println("The following path has been saved: " + path);
  }
  
  public String getPath() {
      return prefs.get("MovieFolderPath", "");
  }
  
  public void removePath() {
      prefs.remove("MovieFolderPath");
  }
}
