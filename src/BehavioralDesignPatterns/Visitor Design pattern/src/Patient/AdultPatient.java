package Patient;

import Visitor.Visitor;

public class AdultPatient implements Patient {

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
}
