package objects;

public class Discipline {

    private String name;
    private int discipline_id;

    public Discipline (int discipline_id, String name) {
        this.discipline_id = discipline_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscipline_id() {
        return discipline_id;
    }

    public void setDiscipline_id(int discipline_id) {
        this.discipline_id = discipline_id;
    }
}
