//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Abstraction.Dog;
import Abstraction.Fish;
import Abstraction.LivingThings;
import Implementor.LandBreatheProcess;
import Implementor.WaterBreatheProcess;

void main() {
        LandBreatheProcess landBreatheProcess = new LandBreatheProcess();
        WaterBreatheProcess waterBreatheProcess = new WaterBreatheProcess();

        LivingThings Dog = new Dog(landBreatheProcess);
        Dog.breatheProcess();

        IO.println("\n");

        LivingThings Fish = new Fish(waterBreatheProcess);
        Fish.breatheProcess();
}
