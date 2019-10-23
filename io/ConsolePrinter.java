package corp.io;

import corp.model.Employee;
import corp.model.Intern;
import corp.model.Worker;

public class ConsolePrinter {
    public void printEmployees(Worker[] workers){
        int counter = 0;
        for (Worker worker: workers){
            if (worker instanceof Employee){
                printLine(worker.toString());
                counter++;
            }
        }
        if (counter ==0){
            printLine("There is no employee registered in the company database");
        }
    }
    public void printInterns(Worker[] workers) {
        int counter = 0;
        for (Worker worker: workers){
            if (worker instanceof Intern){
                printLine(worker.toString());
                counter++;
            }
        }
        if (counter == 0){
            printLine("There is no intern registered in the company database");
        }
    }
    public void printLine(String text){
        System.out.println(text);
    }
}
