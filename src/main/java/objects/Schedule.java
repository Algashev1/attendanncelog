package objects;

public class Schedule {

    private int schedule_id;
    private String name;

    public Schedule (int schedule_id, String name) {
        this.schedule_id = schedule_id;
        this.name = name;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
