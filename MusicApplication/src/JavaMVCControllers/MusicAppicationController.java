/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaMVCControllers;

import JavaMVCViews.*;
import JavaMVCModels.*;
import java.sql.ResultSet;

/**
 *
 * @author waqas
 */
public class MusicAppicationController {
 MusicApplicationModel model =  new MusicApplicationModel();
    public void startApplication() {
        // display main GUI. 
        MusicApplicationView view = new MusicApplicationView();
        view.setVisible(true);
    }

    public ResultSet loadTracks() {
       // Search all tracks and display this data in tabel
        return model.loadData();
    }

    public ResultSet searchTrack(String track) {
        // creating an model obj and calling model class function to search secfic track..
        // This will return details of a track.... 
       
        return model.searchTrackDetails(track);
    }    

    public void getDataInsertApplication() {
        // Starting Window for inserting a new track details..... 
        MusicApplicatioDataInsertView view2 = new MusicApplicatioDataInsertView();
        view2.setVisible(true);
        
    }

    public boolean saveTrackDetails(String title_str, String singer_str, String duration_str) {
        // Creating model class object to insert data in database.... 
       return model.savaTrackData(title_str,singer_str,duration_str);
    }
}
