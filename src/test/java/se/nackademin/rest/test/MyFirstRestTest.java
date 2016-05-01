/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.rest.test;

import org.junit.Test;
import static com.jayway.restassured.path.json.JsonPath.*;
import com.jayway.restassured.response.Response;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author administrator
 */
public class MyFirstRestTest {

    public MyFirstRestTest() {
    }

    @Test
    public void testFetchBook() {
        Response response = new BookOperations().getBook(3);
        System.out.println("Status code: " + response.getStatusCode());
        String title = response.body().jsonPath().getString("book.title");
        System.err.println("Title: " + title);
        assertEquals("Status code should be 200", 200, response.statusCode());
        assertEquals("Status code should be 'Guards! Guards!'", "Guards! Guards!", title);

    }

    @Test
    public void testFetchInvalidBookGives404() {
        Response response = new BookOperations().getBook(235978);
        System.out.println("Status code: " + response.getStatusCode());
        assertEquals("Status code should be 404", 404, response.statusCode());

    }

    @Test
    public void testAddNewBook() {
        BookOperations bookOperations = new BookOperations();
        Response postResponse = bookOperations.createRandomBook();
        assertEquals("Status code should be 201", 201, postResponse.statusCode());

        String expectedDescription = from(bookOperations.getLatestJsonString()).getString("book.description");
        String expectedIsbn = from(bookOperations.getLatestJsonString()).getString("book.isbn");
        int expectedNumberOfPages = from(bookOperations.getLatestJsonString()).getInt("book.nbOfPage");        
        String expectedTitle = from(bookOperations.getLatestJsonString()).getString("book.title");

        Response getResponse = new BookOperations().getAllBooks();        
        String fetchedDescription = getResponse.jsonPath().getString("books.book[-1].description");
        String fetchedIsbn = getResponse.jsonPath().getString("books.book[-1].isbn");
        int fetchedNnbOfPage = getResponse.jsonPath().getInt("books.book[-1].nbOfPage");        
        String fetchedTitle = getResponse.jsonPath().getString("books.book[-1].title");       
        assertEquals(expectedDescription, fetchedDescription);
        assertEquals(expectedIsbn, fetchedIsbn);   
        assertEquals(expectedNumberOfPages, fetchedNnbOfPage);       
        assertEquals(expectedTitle, fetchedTitle);
    }
    
    
        @Test
    public void testDeleteBook() {
        
        BookOperations bookOperations = new BookOperations();
        
        Response postResponse = bookOperations.createRandomBook();
        assertEquals("Status code should be 201", 201, postResponse.statusCode());
  
        Response getResponse = bookOperations.getAllBooks();
        int fetchedId = getResponse.jsonPath().getInt("books.book[-1].id");
        
        Response getDeleteResponse = bookOperations.deleleBook(fetchedId);
        assertEquals("Delete method should return 204",204,getDeleteResponse.getStatusCode());
        
        Response getDeletedBookResponse = bookOperations.getBook(fetchedId);
        assertEquals("Delete method should return 404",404,getDeletedBookResponse.getStatusCode());        
    }
}