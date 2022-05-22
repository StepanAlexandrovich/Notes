package java.android.notes;

public class Note {
    private String date;
    private String headline = "",description = "",keeping = "";

    public boolean ready(){
        if(headline.equals("")){ return false; }
        return true;
    }

    // get //
    public String getDate() { return date; }

    public String getHeadline() {
        return headline;
    }
    public String getDescription() { return description; }
    public String getKeeping() { return keeping; }

    // set //
    public void setDate(String date) {
        this.date = date;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setKeeping(String keeping) {
        this.keeping = keeping;
    }
}
