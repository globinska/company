package corp.app;

import corp.exception.DataExportException;
import corp.exception.DataImportException;
import corp.exception.InvalidDataException;
import corp.exception.NoSuchOptionException;
import corp.io.ConsolePrinter;
import corp.io.DataReader;
import corp.io.file.FileMenager;
import corp.io.file.FileMenagerBuilder;
import corp.model.Corporation;
import corp.model.Employee;
import corp.model.Intern;
import corp.model.Worker;

import java.util.InputMismatchException;

public class CorporationControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileMenager fileMenager;

    private Corporation corporation;

    CorporationControl(){
        fileMenager = new FileMenagerBuilder(printer,dataReader).build();
        try{
            corporation = fileMenager.importData();
            printer.printLine("Data imported from the file");
        } catch (DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("New database initialized");
            corporation = new Corporation();
        }
    }
    void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_EMPLOYEE:
                    addEmployee();
                    break;
                case PRINT_EMPLOYEE:
                    printEmployee();
                    break;
                case ADD_INTERN:
                    addIntern();
                    break;
                case PRINT_INTERN:
                    printIntern();
                    break;
                case EXIT:
                    System.out.println("Bye bye!");
                default:
                    printer.printLine("There is no such option, try again!");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", choose once again:");
            } catch (InputMismatchException ignored) {
                printer.printLine("Inserted value is not a number, try again:");
            }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("wybierz opcję");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void addEmployee() {
        try {
            Employee employee = dataReader.readAndCreateEmployee();
            corporation.addWorker(employee);
        } catch (InputMismatchException e){
            printer.printLine("We couldn't create the worker, improper data");
        } catch (ArrayIndexOutOfBoundsException a){
            printer.printLine("Wrong value, try once again");
        }
    }

    private void printEmployee() {
        Worker[] workers = corporation.getWorkers();
        printer.printEmployees(workers);
    }

    private void addIntern() {
        try{
            Intern intern = dataReader.readAndCreateIntern();
            corporation.addWorker(intern);
        } catch (InputMismatchException e){
            printer.printLine("We couldn't create the worker, improper data");
        } catch (ArrayIndexOutOfBoundsException a) {
            printer.printLine("Wrong value, try once again");
        }
    }
    private void printIntern() {
        Worker[] workers = corporation.getWorkers();
        printer.printInterns(workers);
    }

    private void exit() {
        try{
            fileMenager.exportData(corporation);
            printer.printLine("Succesful data export!");
        } catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        dataReader.close();
        printer.printLine("See ya!");
    }

    private enum Option{
        EXIT(0, " - close the application"),
        ADD_EMPLOYEE(1, " - register an employee"),
        PRINT_EMPLOYEE(2, " - show the information about employees"),
        ADD_INTERN(3," - register an intern"),
        PRINT_INTERN(4," - show the information about interns");

        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }
        @Override
        public String toString() {
            return value +" "+ description;
        }
        public static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchOptionException("There is no "+option+ " option!");

            }
        }
    }
}

