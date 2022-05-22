package java.android.notes;

public class Note {
    private String date;
    private String headline = "",description = "",body = "";

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
    public String getBody() { return body; }

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
    public void setBody(String body) { this.body = body; }

}
