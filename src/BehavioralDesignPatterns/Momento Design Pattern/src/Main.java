//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import Caretaker.Caretaker;
import Originator.TextEditor;

void main() {
    Caretaker caretaker = new Caretaker();
    TextEditor textEditor = new TextEditor(caretaker);

    textEditor.setText("Hello");
    textEditor.save();
    textEditor.setText("Hello Reetesh!");

    textEditor.text();
    textEditor.undo();
    textEditor.text();
}
