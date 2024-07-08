import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;


    private String title;
    private int pages;
    private LinkedList<String> authors = new LinkedList<String>();
    private String summary;
    private String lang;

    public Book(String title,int pages,String summary,String lang,String mainAuthor){
        this.title = title;
        this.pages = pages;
        this.summary = summary;
        this.lang = lang;
        this.authors.add(mainAuthor);
    }
    public String getTitle() {
        return title;
    }
    public Book(String fileName){
        this.getDatafromFile(fileName);
    }
    public int getPages(){
        return this.pages;
    }
    public void setPages(int newPages){
        this.pages = newPages;
    }

    public String getAuthors() {
        StringBuilder authors = new StringBuilder("\nAuthors:\n");

        for (String temp : this.authors) {
            authors.append("    "+temp+"\n");
        }
        return authors.toString();
    }
    public void addAuthor(String author){
        this.authors.add(author);
    }

    // Сеттеры
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        StringBuilder return_str = new StringBuilder("\nBook:");
        return_str.append("\nTitle: '" + title + "\'");
        return_str.append(this.getAuthors());
        return_str.append("Pages: "+this.pages+"\n");
        return_str.append("Summary: '"+this.summary+"'\n");
        return_str.append("Language: '"+this.lang+"'\n");

        return return_str.toString();
    }

    public void getDatafromFile(String fileName){
        try {
            String str  = Files.readString(Paths.get(fileName));

            Pattern pattern = Pattern.compile("Title: '([^']*)'");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                // Извлечение строки в кавычках
                this.title = matcher.group(1);
            } else {
                System.out.println("No match found.");
            }

            pattern = Pattern.compile("    ([^\n]+)\n");
            matcher = pattern.matcher(str);
            this.authors.clear();
            while (matcher.find()) {
                String match = matcher.group();
                match = match.replace("\n","");
                match = match.replace("    ","");


                this.authors.add(match);
                //System.out.print(match);
                //System.out.println(match.replace("\n",""));
            }

            pattern = Pattern.compile("Pages: (\\d+)\\n");
            matcher = pattern.matcher(str);
            if (matcher.find()) {
                // Извлечение строки в кавычках
                String match  = matcher.group(1);
                match = match.replace("\n","");
                match = match.replace("Pages: ","");
                this.pages = Integer.parseInt(match);
            } else {
                System.out.println("No match found.");
            }

            pattern = Pattern.compile("Summary: '([^']*)'");
            matcher = pattern.matcher(str);
            if (matcher.find()) {
                // Извлечение строки в кавычках
                String match  = matcher.group(1);
                match = match.replace("Summary: '","");
                match = match.replace("'","");
                this.summary= match;
            } else {
                System.out.println("No match found.");
            }

            pattern = Pattern.compile("Language: '([^']*)'");
            matcher = pattern.matcher(str);
            if (matcher.find()) {
                // Извлечение строки в кавычках
                String match  = matcher.group(1);
                match = match.replace("Language: '","");
                match = match.replace("'","");
                this.lang= match;
            } else {
                System.out.println("No match found.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public int getUniqId(){
        return System.identityHashCode(this);
    }
    public void wtiteToFile(){
        try (FileWriter writer = new FileWriter(this.getUniqId()+".txt")) {
            writer.write(this.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void serialize(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
            System.out.println("Объект успешно сериализован в файл " + fileName);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сериализации объекта", e);
        }
    }

    public static Book deserialize(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = in.readObject();
            if (obj instanceof Book) {
                Book book = (Book) obj;
                System.out.println("Объект успешно десериализован из файла " + fileName);
                return book;
            } else {
                throw new IllegalArgumentException("Файл не содержит объект класса Book");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при десериализации объекта", e);
        }
    }
    /*public void readFromFile(){
        try (FileReader reader = new FileReader(this.getUniqId()+".txt")) {
            System.out.println(reader.r());
            System.out.println("asdasd");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/
}
