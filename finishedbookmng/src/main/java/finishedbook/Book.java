package finishedbook;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private String title;
    private Date date;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.mm.dd");

    public Book(String rawData) {
        String[] tmpStr = rawData.split(",");
        title = tmpStr[0];
        if (tmpStr.length >= 2)
            setDate(tmpStr[1]);
    }

    public Book(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public String toString() {
        return title+","+sdf.format(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String rawDate) {
        if (rawDate.equals("null"))
            date = null;
        try {
            sdf.parse(rawDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
