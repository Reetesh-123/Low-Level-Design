package Patient;

import Visitor.Visitor;

public interface Patient {
    public void accept(Visitor visitor);
}
