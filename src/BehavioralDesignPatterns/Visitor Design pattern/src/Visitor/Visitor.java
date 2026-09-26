package Visitor;

import Patient.AdultPatient;
import Patient.ChildPatient;
import Patient.SeniorPatient;

public interface Visitor {
    public void visit(ChildPatient childPatient);
    public void visit(AdultPatient adultPatient);
    public void visit(SeniorPatient seniorPatient);
}
