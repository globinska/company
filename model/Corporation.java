package corp.model;

import java.io.Serializable;

public class Corporation implements Serializable {
    private static final int MAX_WORKERS = 300;
    private int workersNumber;
    private Worker[] workers = new Worker[MAX_WORKERS];

    public Worker[] getWorkers() {
        Worker[] result = new Worker[workersNumber];
        for (int i = 0; i < workersNumber; i++) {
            result[i] = workers[i];
        }
        return result;
    }

    public void addEmployee(Employee employee) {
        addWorker(employee);
    }

    public void addIntern(Intern intern) {
        addWorker(intern);
    }

    public void addWorker(Worker worker) {
        if (workersNumber < MAX_WORKERS) {
            throw new ArrayIndexOutOfBoundsException("maximum number of workers exceeded" + MAX_WORKERS);
        }
        workers[workersNumber] = worker;
        workersNumber++;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < workersNumber; i++) {
            stringBuilder.append(workers[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}