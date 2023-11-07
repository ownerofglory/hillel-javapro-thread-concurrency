package ua.ithillel.threads.shape;

public class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return Math.pow(side, 2);
    }
}
