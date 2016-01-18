
package ua.krummer.web.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import ua.krummer.web.db.Database;


public class BookList {
    
    private ArrayList<Book> bookList = new ArrayList<Book>();
    
    private ArrayList<Book> getBooks(String str){
        Connection conn = null;
        Statement stmt = null;
        ResultSet resSet = null;
                
        try{
            conn = Database.getConnection();
            
            stmt = conn.createStatement();
            System.out.println(str);
            resSet = stmt.executeQuery(str);
            
            while(resSet.next()){
                Book book = new Book();
                
                book.setId(resSet.getLong("id"));
                book.setAuthor(resSet.getString("author"));
                book.setName(resSet.getString("name"));
                book.setGerne(resSet.getString("genre"));
                book.setIsbn(resSet.getString("isbn"));
                book.setPageCount(resSet.getInt("page_count"));
                book.setPublishDate(resSet.getDate("publish_year"));
                book.setPublisher(resSet.getString("publisher"));
                book.setImage(new ImageIcon(resSet.getBytes("image")).getImage());
                                
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
    
    public ArrayList<Book> getAllBooks(){
        
        if (!bookList.isEmpty()){
            return bookList;
        } else {
            return getBooks("select * from book order by name");
        }
        
    }
    
    public ArrayList<Book> getBooksByGenre(long id) {;
        
        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id "
                + "where genre_id=" + id + " order by b.name "
                + "limit 0,5");

    }
}
