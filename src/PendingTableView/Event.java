package PendingTableView;

public class Event {
    private String priority;
    private String time;
    private String evtNumber;
    private String type;
    private String location;
    private String informantName;
    private String headline;
    private String remarks;
    
    Event(){
         this.priority = "undef";
         this.time = "undef";
         this.evtNumber = "undef";
         this.type = "undef";
         this.location = "undef";
         this.informantName = "undef";
         this.headline = "undef";
         this.remarks = "";
    }
    
    public Event(String priority, String time, String evtNumber, String type, String location, String informantName, String headline, String remarks ){
         this.priority = priority;
         this.time = time;
         this.evtNumber = evtNumber;
         this.type = type;
         this.location = location;
         this.informantName = informantName;
         this.headline = headline;
         this.remarks = remarks;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvtNumber() {
        return evtNumber;
    }

    public void setEvtNumber(String evtNumber) {
        this.evtNumber = evtNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }   
    
    public String getInformantName() {
        return informantName;
    }
    
    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }
    
    public String getHeadline() {
        return headline;
    }
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks += remarks;
    }
}   
