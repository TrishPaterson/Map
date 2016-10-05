package UnitTableView;

public class Unit {
    protected enum Type {
        I("I"), S("S"), Q("Q"), T("T"), D("D");
        
        private String car;
        Type(String car){
            this.car = car;
        }
        public String getType(){
            return car;
        }
    };

    protected enum Status { 
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

    private final String id;
    private String callSign;
    private String currLocation;
    private String currEvent;
    private String time;
    private Type type;
    private Status status;
    
    Unit(String uniqId, String callSign, String currLocation, String currEvent, String time, String type, String stat) {
        this.id = uniqId;
        this.callSign = callSign;
        this.currLocation = currLocation;
        this.currEvent = currEvent;
        this.time = time; 
        setType(type);
        setStatus(stat);
    }
    
//setters
    public void setTime(String time) {
        this.time = time; //not final TBD Type
    }
         
    public void setStatus(String status){ 
        switch(status){
            case "avail": this.status = Status.avail; break;
            case "unAvail": this.status = Status.unAvail; break;
            case "onRot": this.status = Status.onRot; break;
            case "onSen": this.status = Status.onSen; break;
            default: break;
        }
    }   

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public void setCurrLocation(String currLocation) {
        this.currLocation = currLocation;
    }

    public void setCurrEvent(String currEvent) {
        this.currEvent = currEvent;
    }
    
    public void setType(String type){   
        switch(type){
            case "I": this.type = Type.I; break;
            case "S": this.type = Type.S; break;
            case "Q": this.type = Type.Q; break;
            case "T": this.type = Type.T; break;
            case "D": this.type = Type.D; break;
            default: break;
        }
    }   
    
//getters    
    public String getId(){
        return id;
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
