package goforfit.com.goforfit.models;

public class Fitness {
    private String name;
    private int image;

    public Fitness(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
