/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;
import java.util.Collection;

import dao.BookDaoImpl;
import model.Book;

 import com.opensymphony.xwork2.ActionSupport;
import dao.BookDao;
import java.util.logging.Level;
import java.util.logging.Logger;
 

public class BookAction extends ActionSupport {
    
    private String isbn;
    private String[] isbns;
    private Book book;
    private Collection<Book> books;
    private final BookDaoImpl dao; 
    private String flag;
    public BookAction() {
        dao = new BookDaoImpl();
    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String[] getIsbns() {
        return isbns;
    }

    public void setIsbns(String[] isbns) {
        this.isbns = isbns;
    }
        
    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public String load() {

        try {
            book = dao.getBook(isbn);
        } catch (Exception ex) {
            Logger.getLogger(BookAction.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        return SUCCESS;
    }

    public String list() {
        try {
            books = dao.getBooks();
        } catch (Exception ex) {
            Logger.getLogger(BookAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }
        
    public String store() {
        try {
            boolean flag1 = dao.storeBook(book, flag);
            if(flag1){
                return INPUT;
            }
        } catch (Exception ex) {
            Logger.getLogger(BookAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }
    
    public String remove() {
        if(null != isbn) {
            try {
                dao.removeBook(isbn);
            } catch (Exception ex) {
                Logger.getLogger(BookAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                dao.removeBooks(isbns);
            } catch (Exception ex) {
                Logger.getLogger(BookAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return SUCCESS;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }


    
}