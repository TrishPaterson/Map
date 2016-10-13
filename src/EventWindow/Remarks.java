package EventWindow;

public class Remarks {    
    private String Listing;
    
    public Remarks(){ 
        this.Listing = "";
    }
   
    public Remarks(String Listing){       
        this.Listing = Listing;        
    }

    public void setListing(String Listing) {
        this.Listing = Listing;
    }

    public String getListing() {
        return Listing;
    }
}



