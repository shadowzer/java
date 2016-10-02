import java.util.Arrays;

public class Queue {
    private Task[] queue;
    private int itemsQuantity = 0;
    private boolean isSorted = false;

    public Queue() {
        queue = new Task[32];
    }

    public boolean addTask(Task task) {
        if (isUniqueID(task.getID()) == false)
            return false;
        if (isOverflow()) {
            Task[] newQueue = new Task[queue.length * 2];
            System.arraycopy(queue, 0, newQueue, 0, itemsQuantity);
            queue = newQueue;
        }
        queue[itemsQuantity++] = task;
        isSorted = false;
        return true;
    }

    public Task topTask() {
        //получить задачу с наивысшим приоритетом из очереди (без ее удаления из очереди)
        if (isSorted == false) {
            sort();
            isSorted = true;
        }
        return queue[itemsQuantity - 1];
    }

    public Task popTask() {
        //извлечь задачу с наивысшим приоритетом  из очереди
        if (isSorted == false) {
            sort();
            isSorted = true;
        }
        Task ans = queue[--itemsQuantity];
        queue[itemsQuantity] = null;
        return ans;
    }

    public Task popTaskWeight(int weightMax) {
        //извлечь задачу с наивысшим приоритетом, трудоемкость которой не превышает заданное значение
        if (isSorted == false) {
            sort();
            isSorted = true;
        }
        Task ans;
        for (int i = itemsQuantity - 1; i >= 0; --i) {
            if (queue[i].getWeight() <= weightMax) {
                ans = queue[i];
                queue[i] = null;
                itemsQuantity--;
                sort();
                return ans;
            }
        }
        return null;
    }

    public boolean deleteTask(int ID) {
        //удалить задачу по ее уникальному номеру из очереди
        for (int i = 0; i < itemsQuantity; ++i) {
            if (queue[i].getID() == ID) {
                isSorted = false;
                itemsQuantity--;
                return true;
            }
        }
        return false;
    }

    public Task getTask(int ID) {
        //получить задачу по ее уникальному номеру из очереди (без ее удаления из очереди)
        for (int i = 0; i < itemsQuantity; ++i) {
            if (queue[i].getID() == ID)
                return queue[i];
        }
        return null;
    }

    public Task removeTask(int ID) {
        //извлечь задачу по ее уникальному номеру из очереди
        Task ans;
        for (int i = 0; i < itemsQuantity; ++i) {
            if (queue[i].getID() == ID) {
                ans = queue[i];
                isSorted = false;
                itemsQuantity--;
                return ans;
            }
        }
        return null;
    }

    public boolean setTaskPriorityWithID(int ID, int priority) {
        //изменить приоритет задачи по ее уникальному номеру
        for (int i = 0; i < itemsQuantity; ++i) {
            if (queue[i].getID() == ID) {
                queue[i].setPriority(priority);
                isSorted = false;
                return true;
            }
        }
        return false;
    }

    public int calcTotalWeight() {
        //вычислить суммарную трудоемкость задач, находящихся в очереди
        int ans = 0;
        for (int i = 0; i < itemsQuantity; ++i) {
            ans += queue[i].getWeight();
        }
        return ans;
    }

    public void clear() {
        //очистить очередь (удалить все задачи)
        for (int i = itemsQuantity - 1; i >= 0; --i) {
            queue[i] = null;
            itemsQuantity--;
        }
    }

    public Task[] toSortArray() {
        //вернуть все содержимое очереди в виде массива задач, отсортированных по приоритетам
        if (isSorted == false) {
            sort();
            isSorted = true;
        }
        return queue;
    }

    private boolean isUniqueID(int ID) {
        for (int i = 0; i < itemsQuantity; ++i) {
            if (queue[i].getID() == ID)
                return false;
        }
        return true;
    }

    private void sort() {
        Arrays.sort(queue);
    }

    private boolean isOverflow() {
        if (itemsQuantity == queue.length)
            return true;
        else
            return false;
    }
}
