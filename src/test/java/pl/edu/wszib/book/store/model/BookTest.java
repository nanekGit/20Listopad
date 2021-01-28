package pl.edu.wszib.book.store.model;

import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;

public class BookTest {

    @Test
    public void cloneTest(){
        Book book = new Book();
        book.setId(5);
        book.setPrice(5.5);
        book.setPieces(55);
        book.setIsbn("5555");
        book.setAuthor("5");
        book.setTitle("55");

        Book book2=book.clone();

        Assert.assertEquals(book.getId(),book2.getId());
        Assert.assertEquals(book.getPrice(),book2.getPrice(),0.01);
        Assert.assertEquals(book.getPieces(),book2.getPieces());
        Assert.assertEquals(book.getIsbn(),book2.getIsbn());
        Assert.assertEquals(book.getAuthor(),book2.getAuthor());
        Assert.assertEquals(book.getTitle(),book2.getTitle());

        Assert.assertNotSame(book,book2);
    }


}
