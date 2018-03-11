package objects;

public class Student {

    private String fio;
    private int student_id;

    public Student (int student_id, String fio) {
        this.student_id = student_id;
        this.fio = fio;
    }

    public int getStudent_id () {
        return student_id;
    }

    public void setStudent_id (int student_id) {
        this.student_id = student_id;
    }

    public String getFio () {
        return fio;
    }

    public  void setFio (String fio) {
        this.fio = fio;
    }
}
