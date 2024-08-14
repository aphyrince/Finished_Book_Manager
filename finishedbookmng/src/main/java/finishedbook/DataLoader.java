package finishedbook;

import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class DataLoader {
    private List<Book> bookList;
    private File bookListSourceFile;

    public DataLoader(String path) {
        bookListSourceFile = new File(path);
        bookList = new ArrayList<>();
    }

    public void loadData() {
        try {
            FileReader fr = new FileReader(bookListSourceFile);
            StringBuffer sb = new StringBuffer();
            char[] cbuf = new char[1024];
            while (true) {
                int readLen = fr.read(cbuf);
                sb.append(new String(cbuf));
                if (readLen == -1)
                    break;
            }
            fr.close();
            Arrays.stream(sb.toString().split("\r\n"))
                    .forEach(row -> {
                        bookList.add(new Book(row));
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            FileWriter fw = new FileWriter(bookListSourceFile);
            bookList.stream()
                    .map(book -> book.toString() + "\r\n")
                    .forEach(row -> {
                        try{
                            fw.write(row);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    });
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBookList() {
        return bookList;
    }
}
