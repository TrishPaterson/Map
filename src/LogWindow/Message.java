package LogWindow;

public class Message {   
    private String msgLog;
    private String time;
    
    public Message(){        
        this.msgLog = "";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public Message(String msgLog, String time){      
        this.msgLog = msgLog;
        this.time = time;
    }

    public String getMsgLog() {
        return msgLog;
    }

    public void setMsgLog(String msgLog) {
        this.msgLog = msgLog;
    }
}

    

