package s180859.bauge.christopher.cookingapplication_v1;

/**
 * Created by Christopher on 17/11/2015.
 */
public class Receipt {
    int id;
    String name, portions, amount, difficulty, cooktime, description;
    String[] contains;

    // Empty constructor
    public Receipt() {
    }

    public Receipt(int id, String name, String[] contains, String portions, String amount, String difficulty, String cooktime, String description) {
        this.id = id;
        this.name = name;
        this.contains = contains;
        this.portions = portions;
        this.amount = amount;
        this.difficulty = difficulty;
        this.cooktime = cooktime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getContains() {
        return contains;
    }

    public void setContains(String[] contains) {
        this.contains = contains;
    }

    public String getPortions() {
        return portions;
    }

    public void setPortions(String portions) {
        this.portions = portions;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCooktime() {
        return cooktime;
    }

    public void setCooktime(String cooktime) {
        this.cooktime = cooktime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
