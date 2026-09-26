package AbstractClass;

public abstract class Beverage {

    public final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public void boilWater(){
        IO.println("Boiling water");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }

    protected abstract void brew();
    protected abstract void addCondiments();
}
