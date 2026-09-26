package Abstraction;

import Implementor.BreatheProcessImplementor;

public class Fish extends LivingThings{

    public Fish(BreatheProcessImplementor breatheProcessImplementor) {
        super(breatheProcessImplementor);
    }
    public void breatheProcess(){
        breatheProcessImplementor.breatheProcess();
    }
}
