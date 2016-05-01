package se.nackademin.rest.test.model;

public class Book {
    private int id;
    private String descrition;
    private String isbn;
    private int nbOfPage;   
    private String title;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the descrition
     */
    public String getDescrition() {
        return descrition;
    }

    /**
     * @param descrition the descrition to set
     */
    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the nbOfPage
     */
    public int getNbOfPage() {
        return nbOfPage;
    }

    /**
     * @param nbOfPage the nbOfPage to set
     */
    public void setNbOfPage(int nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
            
    
}
