//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
   Car.CarBuilder builder = new Car.CarBuilder();
   builder.setEngine("300cc");
   builder.setColor("blue");
   builder.setSunroof(true);
   builder.setNavigationSystem(true);

   Car car = builder.build();
   IO.println(car.toString());
}
