package com.twu.biblioteca.service;

import com.twu.biblioteca.db.BookDB;
import com.twu.biblioteca.db.BorrowBookDB;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Role;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Thoughtworks on 5/31/15.
 */
public class BookService {

    public void saveAsBorrow(User user, Book chooseBook) {
        BookDB.removeBook(chooseBook);
        BorrowBookDB.saveBook(user, chooseBook);
    }

    public void borrowBook() {
        Scanner reader = new Scanner(System.in);
        int chooseBookIndex;
        do{
            System.out.print("Choose books you want to borrow:");
            chooseBookIndex = reader.nextInt();
            Book chooseBook = BookDB.getBookByIndex(chooseBookIndex);
            User user = new User("wubang", Role.STUDENT);
            saveAsBorrow(user, chooseBook);
        }while (chooseBookIndex < 0 || chooseBookIndex >= BookDB.getAllBooks().size());
        System.out.print("Thank you! Enjoy the book");
    }

    public List<Book> listBooks() {
        List<Book> books = BookDB.getAllBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
        return books;
    }

    public Map<User, ArrayList<Book>> listBorrowBooks() {
        Map<User, ArrayList<Book>> borrowBooks = BorrowBookDB.getAll();
        for (User user1 : borrowBooks.keySet()) {
            ArrayList<Book> books1 = borrowBooks.get(user1);
            for (Book book : books1) {
                System.out.println("[userName:" + user1.getName() + "---bookName" + book.getName() + "]");
            }
        }
        return borrowBooks;
    }
}