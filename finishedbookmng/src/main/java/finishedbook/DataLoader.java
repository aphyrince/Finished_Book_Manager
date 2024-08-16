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
                if (readLen == -1)
                    break;
                sb.append(new String(cbuf, 0, readLen));
            }
            fr.close();
            Arrays.stream(sb.toString().split("\n"))
                    .forEach(str -> bookList.add(new Book(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            FileWriter fw = new FileWriter(bookListSourceFile);
            fw.write(String.join("\n", bookList.stream()
                    .map(Book::toString)
                    .toArray(String[]::new)));
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
