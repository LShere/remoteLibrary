/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import model.Book;

/**
 *
 * @author lenovo
 */
public interface BookDao {
    public Collection<Book> getBooks() throws Exception;
    public Book getBook(String isbn) throws Exception;
    public boolean storeBook(Book book, String flag) throws Exception;
    public void removeBook(String isbn) throws Exception;
    public void removeBooks(String[] isbns) throws Exception;
    public boolean findByIsbn(String isbn) throws Exception;
}
