package map;
/* 
Code might change in the future when GUI backened and Unit.JavaScript is done.
*/

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
        avail("Available"), onRot("Onroute"), 
        onSen("Onscene"), unAvail("Unavailable");
        
        private String status;   
        Status(String status){
            this.status = status;
        }
        
        public String getStatus() {
            return status;
        }
    }

    private final String uniqId;
    private String callSign;
    private String currLocation;
    //private String defLocation;
    private String currEvent;
    private String time;
    private final Type type;
    private Status status;

    Unit(String uniqId, String callSign, String defLocation, String currEvent, String time, String type, String stat) {
        this.uniqId = uniqId;
        this.callSign = callSign;
        this.currLocation = defLocation;
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
         
    public Status setStatus(String status){ //may change
        switch(status){
            case "avail": return this.status.avail; 
            case "unAvail": return this.status.unAvail; 
            case "onRot": return this.status.onRot; 
            case "onSen": return this.status.onSen; 
            default: break;
        }
        return null;
    }   
    
    public Type setType(String type){ //may change  
        switch(type){
            case "car1": return this.type.car1;
            case "car2": return this.type.car2;
            default: break;
        }
        return null;
    }   
    
//getters    
    public String getId(){
        return uniqId;
    }
    
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

    public String getType(){
        return type.getType();
    }
    
    public String getStatus(){
        return status.getStatus();
    }
}
