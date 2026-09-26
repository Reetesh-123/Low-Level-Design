package Flyweight;

// ConcreteFlyweight: intrinsic state shared by every particle of this type
public class ConcreteParticleType implements ParticleType {
    private final String color;
    private final String sprite;

    public ConcreteParticleType(String color, String sprite) {
        this.color = color;
        this.sprite = sprite;
    }

    @Override
    public void draw(float x, float y, float velocity) {
        System.out.println("Drawing " + color + " " + sprite + " particle at (" + x + ", " + y
                + ") with velocity " + velocity);
    }
}
