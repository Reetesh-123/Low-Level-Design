import Client.Particle;
import FlyweightFactory.ParticleTypeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ParticleTypeFactory factory = new ParticleTypeFactory();
        String[] colors = {"Red", "Green", "Blue"};
        String[] sprites = {"Circle", "Spark"};

        List<Particle> particles = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String color = colors[random.nextInt(colors.length)];
            String sprite = sprites[random.nextInt(sprites.length)];
            var particleType = factory.getParticleType(color, sprite);

            Particle particle = new Particle(particleType, random.nextFloat() * 100,
                    random.nextFloat() * 100, random.nextFloat() * 10);
            particles.add(particle);
        }

        for (Particle particle : particles) {
            particle.draw();
        }

        System.out.println("Total particles created: " + particles.size());
        System.out.println("Total ParticleType flyweights created: " + factory.typeCount());
    }
}
