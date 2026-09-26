package Client;

import Flyweight.ParticleType;

// Context: holds extrinsic state and a reference to the shared flyweight
public class Particle {
    private final ParticleType particleType;
    private float x;
    private float y;
    private float velocity;

    public Particle(ParticleType particleType, float x, float y, float velocity) {
        this.particleType = particleType;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void draw() {
        particleType.draw(x, y, velocity);
    }
}
