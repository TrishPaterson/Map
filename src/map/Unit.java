package map;
/* 
Code might change in the future when GUI backened and Unit.JavaScript is done.
*/
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Unit {
    protected enum Type { // Not Final Object Names 
        car1("P10"), car2("P20");
        
        private String car;
        Type(String car){
            this.car = car;
        }
        public String getType(){
            return car;
        }
    };

    protected enum Status { // Not Final Object Names 
        avail("available"), onRot("Onroute"), 
        onSen("Onscene"), unAvail("unavailable");
        
        private String status;   
        Status(String status){
            this.status = status;
        }
        
        public String getStatus() {
            return status;
        }
    }

    private String currLocation;
    private String uniqId;
    private String callSign;
    private String defLocation;
    private String currEvent;
    private String time;
    private Type type;
    private Status status;

    Unit(String uniqId, String callSign, String defLocation, String currEvent, String time, String type, String stat) {
        this.uniqId = uniqId;
        this.callSign = callSign;
        this.defLocation = defLocation;
        this.currEvent = currEvent;
        this.time = time; //not final TBD 'Type'
        this.type = setType(type);
        this.status = setStatus(stat);
    }
    
//setters
    public String changeLocation(String unitID){ //might become static
        return("changeLocation('unitID')"); //not final return type
    }
    
    public String setFirstLocation(String unitID) {//might become static  
        return("setCurrLocation('unitID')"); //not final return type
    }
    
    public String setCurrEvent(String unitID) { //might become static
        return("setEvent('unitID')"); //not final return type
    }

    public void setTime(String time) {
        this.time = time; //not final TBD Type
    }
    
    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }
         
    public Status setStatus(String status){ //may change
        switch(status){
            case "available": return this.status.avail; 
            case "unavailable": return this.status.unAvail; 
            case "unroute": return this.status.onRot; 
            case "onscene": return this.status.onSen; 
            default: break;
        }
        return null;
    }   
    
    public Type setType(String type){ //may change  
        switch(type){
            case "P10": return this.type.car1;
            case "P20": return this.type.car2;
            default: break;
        }
        return null;
    }   
    
//getters   
    public String getCallSign() {
        return callSign;
    }

    public String getCurrLocation() {
        return currLocation;
    }

    public String getCurrEvent() {
        return currEvent;
    }

    public String getTime() {
        return time;
    }
}
