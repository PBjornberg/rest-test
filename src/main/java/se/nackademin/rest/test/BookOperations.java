
package se.nackademin.rest.test;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;

public class BookOperations {
    private static final String BASE_URL = "http://localhost:8080/librarytest/rest/";
    private String jsonString = "";
    public Response getBook(int id){
        String resourceName = "books/"+id;
        return given().accept(ContentType.JSON).get(BASE_URL + resourceName);
    }
    public Response createRandomBook(){
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String isbn = UUID.randomUUID().toString();     
        String resourceName = "books";
        String postBodyTemplate = "{\n"
                + "\"book\":\n"
                + "  {\n"
                + "    \"description\":\"%s\",\n"
                + "    \"isbn\":\"%s\",\n"
                + "    \"nbOfPage\":%s,\n"
                + "    \"title\":\"%s\"\n"
                + "  }\n"
                + "}";
        String postBody = String.format(postBodyTemplate,description,isbn,""+new Random().nextInt(500),title);
        jsonString = postBody;
        return given().contentType(ContentType.JSON).body(postBody).post(BASE_URL + resourceName);
    }
    
    public String getLatestJsonString(){
        return jsonString;
    }
    
    public Response getAllBooks(){
        String resourceName = "books";
        return given().accept(ContentType.JSON).get(BASE_URL + resourceName).prettyPeek();    
    }
    
    public Response deleleBook(int id){
        String deleteResourceName = BASE_URL+"books/"+id;      
        return delete(deleteResourceName);
    }
}
