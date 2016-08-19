package map;

public class Unit {
	// this enum values are temporary
	protected enum Type{car1,car2}; 
	protected enum Status{available,Onroute,Onscene,unavailable}	
	
	private String callSign;
	private String currLocation;
	private String currEvent;
	private String time;
	private Type type;
	private Status status;

	Unit(){ //temporary values
		this.callSign = "BatSign";
	    this.type = type.car1;
		this.time = "00:00:00";
		this.currLocation = "Gotham City";
		this.status = status.available;		
	}
	
	Unit(String callSign, String time, String currLocation, Status status){
		this.callSign = callSign;
		this.time = time;
		this.currLocation = currLocation;
		this.status = status;
	}
//setters
	public void setCallSign(String callSign){
		this.callSign = callSign;
	}
	
	public void setCurrLocation(String currLocation){
		this.currLocation = currLocation;
	}
	
	public void setCurrEvent(String currEvent){
		this.currEvent = currEvent;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public void setStatus(Status status){
		this.status = status;
	}
	
	public void setType(Type type){
		this.type = type;
	}
	
//getters	
	public String getCallSign(){
		return callSign;
	}
	
	public String getCurrLocation(){
		return currLocation;
	}
	
	public String getCurrEvent(){
		return currEvent;
	}
	
	public String getTime(){
		return time;
	}
	
	public Status getStatus(){
		return status;
	}
	
	public Type getType(){
		return type;
	}
}
