public class Task {
    private int ID;
    private int priority;
    private int weight; // трудоемкость
    private String description = new String();

    public Task(int ID, int priority, int weight, String description) {
        this.ID = ID;
        this.priority = priority;
        this.weight = weight;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public int getPriority() {
        return priority;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int compareTo(Object obj) {
        Task tmp = (Task)obj;
        if (this.priority <  tmp.getPriority())
            return -1;
        else if (this.priority > tmp.getPriority())
            return 1;
        else if (this.ID > tmp.getID()) // if priorities equal
            return 1;
        else
            return -1;
        /*else                  1 level compare
            return 0;*/
    }
}
