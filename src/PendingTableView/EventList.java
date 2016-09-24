package PendingTableView;

public class EventList {
    private String priority;
    private String time;
    private String evtNumber;
    private String type;
    private String location;
    
    EventList(){
         this.priority = "undef";
         this.time = "undef";
         this.evtNumber = "undef";
         this.type = "undef";
         this.location = "undef";
    }
    
    EventList(String priority, String time, String evtNumber, String type, String location ){
         this.priority = priority;
         this.time = time;
         this.evtNumber = evtNumber;
         this.type = type;
         this.location = location;
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
}
