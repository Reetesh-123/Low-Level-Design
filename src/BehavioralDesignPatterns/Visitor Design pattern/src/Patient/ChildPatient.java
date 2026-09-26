package Patient;

import Visitor.Visitor;

public class ChildPatient implements Patient {

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
