package FactoryPattern;

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactoryObj=new ShapeFactory();
        Shape shapeObject=shapeFactoryObj.getShape("Circle");
        shapeObject.draw();
    }
}
