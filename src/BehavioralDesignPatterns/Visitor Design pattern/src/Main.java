import Patient.AdultPatient;
import Patient.ChildPatient;
import Patient.Patient;
import Patient.SeniorPatient;
import Visitor.BillingVisitor;
import Visitor.DiagnosisVisitor;
import Visitor.Visitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Patient> patients = List.of(
                new ChildPatient(),
                new AdultPatient(),
                new SeniorPatient()
        );
        Visitor diagnosisVisitor = new DiagnosisVisitor();
        Visitor billingVisitor = new BillingVisitor();

        for (Patient patient : patients) {
            patient.accept(diagnosisVisitor);
            patient.accept(billingVisitor);
        }
    }
}
