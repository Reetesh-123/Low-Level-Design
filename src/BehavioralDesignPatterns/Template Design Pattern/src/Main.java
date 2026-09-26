//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import ConcreteClass.Coffee;
import ConcreteClass.Tea;

void main() {

    Tea tea = new Tea();
    Coffee coffee = new Coffee();

    tea.prepareRecipe();
    IO.println("\n");

    coffee.prepareRecipe();
}
