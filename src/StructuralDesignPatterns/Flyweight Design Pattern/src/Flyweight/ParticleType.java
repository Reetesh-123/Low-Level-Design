package Flyweight;

// Flyweight: holds intrinsic state (shared across many particles)
public interface ParticleType {
    void draw(float x, float y, float velocity);
}
