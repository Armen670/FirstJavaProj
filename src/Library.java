import java.util.LinkedList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import java.util.stream.Collectors;

public class Library {
    LinkedList<Book> books = new LinkedList<Book>();

    public void addBook(Book book){
        books.add(book);
    }
    public int filterByPages(int filterPages) {
        // Фильтрация коллекции по условию с использованием стримов
        List<Book> filteredBooks = this.books.stream()
                //фильтруем книги, у которых pages больше 300
                .filter(book -> book.getPages() > filterPages)
                .collect(Collectors.toList());

        LocalDateTime currentDateTime = LocalDateTime.now();

        String message = "По итогам фильтрации на " + currentDateTime + " обнаружено " + filteredBooks.size() + " элементов.";

        try (FileWriter writer = new FileWriter("Library.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл", e);
        }
        return filteredBooks.size();
    }

}
