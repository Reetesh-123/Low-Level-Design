package Implementor;

public class WaterBreatheProcess implements BreatheProcessImplementor{

    @Override
    public void breatheProcess() {
        IO.println("breath through gills");
        IO.println("Inhale Oxygen");
        IO.println("exhale carbondioxide");
    }
}
