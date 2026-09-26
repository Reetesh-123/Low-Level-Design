package FlyweightFactory;

import Flyweight.ConcreteParticleType;
import Flyweight.ParticleType;

import java.util.HashMap;
import java.util.Map;

// FlyweightFactory: creates and caches flyweights by their intrinsic state
public class ParticleTypeFactory {
    private final Map<String, ParticleType> cache = new HashMap<>();

    public ParticleType getParticleType(String color, String sprite) {
        String key = color + "-" + sprite;
        return cache.computeIfAbsent(key, k -> {
            System.out.println("Creating new ParticleType for " + key);
            return new ConcreteParticleType(color, sprite);
        });
    }

    public int typeCount() {
        return cache.size();
    }
}
