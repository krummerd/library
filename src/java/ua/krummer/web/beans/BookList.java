
package ua.krummer.web.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.krummer.web.db.Database;


public class BookList {
    
    private ArrayList<Book> bookList = new ArrayList<Book>();
    
    private ArrayList<Book> getBooks(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet resSet = null;
                
        try{
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            resSet = stmt.executeQuery("select * from book order by name");
            
            while(resSet.next()){
                Book book = new Book();
                
                book.setName(resSet.getString("name"));
                book.setGerne(resSet.getString("genre"));
                book.setIsbn(resSet.getString("isbn"));
                book.setPageCount(resSet.getInt("page_count"));
                book.setPublishDate(resSet.getDate("publish_date"));
                book.setPublisher(resSet.getString("publisher"));
                
                bookList.add(book);
            }
            
        } catch(SQLException e){
                Logger.getLogger(BookList.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{
                if (resSet == null){
                    resSet.close();
                }
                if (stmt == null){
                    stmt.close();
                }
                if (conn == null){
                    conn.close();
                }
            } catch (Exception e){
                Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return bookList;
        
    }
    
    public ArrayList<Book> getBookList(){
        
        if (!bookList.isEmpty()){
            return bookList;
        } else {
            return getBooks();
        }
        
    }
}
