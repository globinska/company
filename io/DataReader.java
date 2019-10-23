package corp.io;

import corp.model.Employee;
import corp.model.Intern;

import java.util.Scanner;

public class DataReader {
    private Scanner input = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer){
        this.printer = printer;
    }

    public String getString() {
        return input.nextLine();
    }

    public void close(){
        input.close();
    }

    public int getInt(){
        try {
            return input.nextInt();
        } finally {
            input.nextLine();
        }
    }

    public Employee readAndCreateEmployee(){
        System.out.println("Name:");
        String name = input.nextLine();
        System.out.println("Surname:");
        String surname = input.nextLine();
        System.out.println("Position:");
        String position = input.nextLine();
        System.out.println("Function:");
        String function = input.nextLine();
        System.out.println("Menagement level:");
        int level = input.nextInt();
        input.nextLine();

        return new Employee(name, surname, position, function, level);
    }

    public Intern readAndCreateIntern(){
        System.out.println("Name:");
        String name = input.nextLine();
        System.out.println("Surname:");
        String surname = input.nextLine();
        System.out.println("Position:");
        String position = input.nextLine();
        System.out.println("Name of the University:");
        String universityName = input.nextLine();
        System.out.println("Year of studies:");
        int yearOfStudies = input.nextInt();
        input.nextLine();

        return new Intern(name, surname, position, universityName, yearOfStudies);
    }
}
