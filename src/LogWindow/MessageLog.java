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
public class MessageLog {
    
    private String msgLog;
    private String time;
    
    public MessageLog(){        
        this.msgLog = "";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public MessageLog(String msgLog, String time){
        
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

    

