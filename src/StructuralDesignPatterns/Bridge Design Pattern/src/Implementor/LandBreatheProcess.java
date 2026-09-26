package Implementor;

public class LandBreatheProcess implements BreatheProcessImplementor {
    @Override
    public void breatheProcess() {
        IO.println("breath through nose");
        IO.println("Inhale Oxygen");
        IO.println("exhale carbondioxide");
    }
}
