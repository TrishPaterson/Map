/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogWindow;

/**
 *
 * @author Sakina Pan
 */
public class getTime {
    
    private String getUserTime;
    
    public getTime(){        
        this.getUserTime = "";
    }
    
    public getTime(String getUserTime){
        
        this.getUserTime = getUserTime;
        
    }

    public void setListing(String getUserTime) {
        this.getUserTime = getUserTime;
    }

    public String getListing() {
        return getUserTime;
    }
}

    

