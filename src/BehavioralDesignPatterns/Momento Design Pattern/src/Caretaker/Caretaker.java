package Caretaker;

import java.util.ArrayList;
import java.util.List;

import Memento.Memento;

public class Caretaker {
    private final List<Memento> history;

    public Caretaker(){
        history = new ArrayList<>();
    }

    public void push(Memento memento){
        history.add(memento);
    }

    public Memento pop(){
        if(!history.isEmpty()){
            return history.removeLast();
        }
        return null;
    }
}
