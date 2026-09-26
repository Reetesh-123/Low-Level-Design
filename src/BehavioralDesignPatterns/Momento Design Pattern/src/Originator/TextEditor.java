package Originator;

import Caretaker.Caretaker;
import Memento.Memento;

public class TextEditor {
    private String text;
    private final Caretaker caretaker;

    public TextEditor(Caretaker caretaker) {
        this.text = "";
        this.caretaker = caretaker;
    }

    public void text() {
        IO.println(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void save(){
        caretaker.push(new Memento(text));
    }

    public void undo(){
        Memento memento = caretaker.pop();
        if (memento != null) {
            this.text = memento.getText();
        }
    }
}
