package finishedbook;

import java.time.LocalDate;

public class Book {
    private String title;
    private LocalDate date;

    public Book(String rawData) {
        String[] tmpStr = rawData.trim().split("\\|");
        title = tmpStr[0].trim();
        if(tmpStr[1].equals("null"))
            date = null;
        else
            date = LocalDate.parse(tmpStr[1]);
    }

    public Book(String title, LocalDate date) {
        this.title = title;
        this.date = date;
    }

    public String toString() {
        if(date == null)
            return title+"|null";
        return title+"|"+date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
