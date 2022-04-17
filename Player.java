public class Player {
    private String name;
    private String color;
    private char disc;

    public Player(String name, String color, char disc) {
        this.name = name;
        this.color = color;
        this.disc = disc;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public char getDisc() {
        return disc;
    }
}
