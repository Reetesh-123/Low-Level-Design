package Abstraction;

import Implementor.BreatheProcessImplementor;

public abstract class LivingThings {
    BreatheProcessImplementor breatheProcessImplementor;

    public LivingThings(BreatheProcessImplementor breatheProcessImplementor){
        this.breatheProcessImplementor = breatheProcessImplementor;
    }
    public abstract void breatheProcess();
}
