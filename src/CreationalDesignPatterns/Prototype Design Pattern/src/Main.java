//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws CloneNotSupportedException {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    Character character1 = new Character("Mario",100,20, 5);
    Character character2 = character1.clone();
    character2.setAttackPower(60);

    IO.println(character1.toString());
    IO.println(character2.toString());
}
