/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import com.opensymphony.xwork2.inject.Scope;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import model.Book;

public class BookDaoImpl implements BookDao{
    private static final ConcurrentMap<String, Book> data = new ConcurrentHashMap<String, Book>();;
    private Connection conn=null;
    private PreparedStatement ps=null;
    //通过构造方法取得数据库连接
    public BookDaoImpl(Connection conn){
        this.conn=conn;
    }

    
    public BookDaoImpl() {}
    public void getConnection() throws Exception{
        DBConnection db =  new DBConnection();
        conn = db.getConnection();
    }
    
    public Collection<Book> getBooks() throws Exception{
        data.clear();
        getConnection();
        String sql = "select * from book;";
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Book book = new Book();
            book.setIsbn(rs.getString("isbn"));
            book.setTitle(rs.getString("title"));
            book.setPrice(rs.getDouble("price"));
            data.put(book.getIsbn(), book);
        }
        ps.close();
        conn.close();
        return data.values();
    }
    
    public Book getBook(String isbn) throws Exception{
        return data.get(isbn);
    }
    
    public boolean storeBook(Book book, String flag) throws Exception{
        getConnection();
        String sql;
        boolean flag1 = false;
//        int count = 0;
            if(flag.equals("edit")){
                sql = "update book set title=?, price=? where isbn=?;";
                ps = conn.prepareStatement(sql);
                ps.setString(1, book.getTitle());
                ps.setDouble(2, book.getPrice());
                ps.setString(3, book.getIsbn());
                ps.executeUpdate();  
                ps.close();
            }else{
                    if(findByIsbn(book.getIsbn()) == false){
                        getConnection();
                        sql = "insert into book(isbn, title, price) values(?, ?, ?);";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1, book.getIsbn());
                        ps.setString(2, book.getTitle());
                        ps.setDouble(3, book.getPrice());
                        ps.executeUpdate();  
                        ps.close();
                    }else{
                        flag1 = true;
                    }

            }

            
            conn.close();
            return flag1;
    }
        
    public void removeBook(String isbn) throws Exception{
        getConnection();
        String sql = "delete from book where isbn=?;";
        ps = conn.prepareStatement(sql);
        ps.setString(1, isbn);
        ps.executeUpdate();
        conn.close();
        ps.close();
    }
    
    public void removeBooks(String[] isbns) throws Exception{

        getConnection();
        String sql = "delete from book where isbn=?;";
        ps = conn.prepareStatement(sql);
        for(String i : isbns) {
            ps.setString(1, i);
            ps.executeUpdate();
        }

        conn.close();
        ps.close();
    }

    @Override
    public boolean findByIsbn(String isbn) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        getConnection();
        boolean flag = false;
        String sql = "select * from book where isbn=?;";
        ps = conn.prepareStatement(sql);
        ps.setString(1, isbn);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            flag = true;
        }
        conn.close();
        ps.close();
        rs.close();
        return flag;
    }
    

}