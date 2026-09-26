package Patient;

import Visitor.Visitor;

public class SeniorPatient implements Patient {

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
