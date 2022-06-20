package java.android.notes.core;

public class Note {
    private String date;
    private String headline = "",description = "",body = "";
    private String id;

    public Note() {}
    public Note(String date) {
        this.date = date;
    }

    // get //
    public String getDate() { return date; }

    public String getHeadline() {
        return headline;
    }
    public String getDescription() { return description; }
    public String getBody() { return body; }

    public String getId() { return id; }

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

    public void setId(String id) { this.id = id; }

    // logic //
    public boolean ready(){
        if(headline.equals("")){ return false; }
        return true;
    }

}
