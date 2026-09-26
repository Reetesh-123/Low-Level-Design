package Abstraction;

import Implementor.BreatheProcessImplementor;

public class Dog extends LivingThings {

    public Dog(BreatheProcessImplementor breatheProcessImplementor) {
        super(breatheProcessImplementor);
    }

    public void breatheProcess(){
        breatheProcessImplementor.breatheProcess();
    }
}
