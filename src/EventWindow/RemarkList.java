package EventWindow;

public class RemarkList {
    
    private String Listing;

    public RemarkList(){
        
        this.Listing = "";
    }
    
    public RemarkList(String Listing){
        
        this.Listing = Listing;
        
    }

    public void setListing(String Listing) {
        this.Listing = Listing;
    }

    public String getListing() {
        return Listing;
    }
}



