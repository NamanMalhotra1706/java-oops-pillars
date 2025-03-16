import java.util.*;

abstract class Patient{
    private String patientId;
    private String name;
    private int age;

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId + ", Name: " + name + ", Age: " + age);
    }

    public abstract double calculateBill();
}

interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

class InPatient extends Patient implements MedicalRecord {
    private double dailyRate;
    private int daysAdmitted;
    private String medicalHistory;

    public InPatient(String patientId, String name, int age, double dailyRate, int daysAdmitted) {
        super(patientId, name, age);
        this.dailyRate = dailyRate;
        this.daysAdmitted = daysAdmitted;
        this.medicalHistory = "";
    }

    @Override
    public double calculateBill() {
        return dailyRate * daysAdmitted;
    }

    @Override
    public void addRecord(String record) {
        this.medicalHistory += record + "\n";
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History for " + getName() + ":\n" + medicalHistory);
    }
}

class OutPatient extends Patient implements MedicalRecord {
    private double consultationFee;
    private String medicalHistory;

    public OutPatient(String patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
        this.medicalHistory = "";
    }

    @Override
    public double calculateBill() {
        return consultationFee;
    }

    @Override
    public void addRecord(String record) {
        this.medicalHistory += record + "\n";
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History for " + getName() + ":\n" + medicalHistory);
    }
}


public class HospitalPatientManagement {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        InPatient john = new InPatient("P001", "Kiran Rao", 45, 2000, 5);
        OutPatient jane = new OutPatient("P002", "Harmanpreet Kaur", 30, 500);

        john.addRecord("Admitted with fever, prescribed antibiotics.");
        jane.addRecord("Routine check-up, advised vitamins.");

        patients.add(john);
        patients.add(jane);

        for (Patient patient : patients) {
            patient.getPatientDetails();
            System.out.println("Total Bill: " + patient.calculateBill());
            if (patient instanceof MedicalRecord) {
                ((MedicalRecord) patient).viewRecords();
            }
            System.out.println();
        }
    }
}
