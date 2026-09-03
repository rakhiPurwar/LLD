package splitwise.models;

public class ExpenseMetadata {
    private final String name;
    private final String notes;
    private final String imgUrl;

    public ExpenseMetadata(String name, String notes, String imgUrl) {
        this.name = name;
        this.notes = notes;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }
    public String getNotes() {return notes;}
    public String getImgUrl() { return imgUrl; };

}
