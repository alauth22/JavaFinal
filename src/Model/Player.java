package Model;

public class Player {
    private int id; // ID of the player (unique for each player)
    private String name; // Name of the player
    private String achievement; // Achievement the player has earned

    // Constructor to initialize the Player object with id, name, and achievement
    public Player(int id, String name, String achievement) {
        this.id = id;
        this.name = name;
        this.achievement = achievement;
    }

    // Getter method for id
    public int getId() {
        return id;
    }

    // Setter method for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for achievement
    public String getAchievement() {
        return achievement;
    }

    // Setter method for achievement
    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    // Method to return a string representation of the Player object
    @Override
    public String toString() {
        return "Player{id=" + id + ", name='" + name + "', achievement='" + achievement + "'}";
    }
}

