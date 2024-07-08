//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        //Пример создания нескольких книг и считывания данных из файлов
        Book first = new Book("asd",123,"Good book","Russia","Armen");
        first.addAuthor("Vlad");
        first.wtiteToFile();
        Book second = new Book("123213 asdasd",798,"asdasd asdaaa","English","Aasssrm 13 en");
        second.addAuthor("VLADIK");
        second.wtiteToFile();
        Book third = new Book(second.getUniqId()+".txt");

        // Пример филтрации реализован в отдельном классе библеотоека
        third.setPages(355);
        Library mainLibrary = new Library();
        mainLibrary.addBook(first);
        mainLibrary.addBook(second);
        mainLibrary.addBook(third);
        mainLibrary.filterByPages(100);

        //Пример Сериализации

        Book book = new Book("Java Programming", 400, "A comprehensive guide to Java programming.", "English", "John Doe");
        book.addAuthor("Jane Smith");

        // Сохраняем объект в файл
        String fileName = "book.ser";
        book.serialize(fileName);

        System.out.println("Объект успешно сериализован в файл " + fileName);


        // Десериализуем объект Book из файла
        Book deserializedBook = Book.deserialize(fileName);

        // Выводим информацию о десериализованной книге
        System.out.println("Десериализованная книга:");
        System.out.println(deserializedBook.toString());
    }
}