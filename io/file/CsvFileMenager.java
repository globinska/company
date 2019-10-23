package corp.io.file;

import corp.exception.DataExportException;
import corp.exception.DataImportException;
import corp.exception.InvalidDataException;
import corp.io.file.FileMenager;
import corp.model.Corporation;
import corp.model.Employee;
import corp.model.Intern;
import corp.model.Worker;

import java.io.*;
import java.util.Scanner;

public class CsvFileMenager implements FileMenager {
    private static final String FILE_NAME = "Corporation.csv";

    @Override
    public Corporation importData() {
        Corporation corporation = new Corporation();
        try (Scanner fileReader = new Scanner(new File(FILE_NAME))){
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                Worker worker = createObjectFromString(line);
                corporation.addWorker(worker);
            }
        } catch (FileNotFoundException e ){
            throw new DataImportException("No file "+FILE_NAME+" found");
        }
        return corporation;
    }

    @Override
    public void exportData(Corporation corporation){
        Worker[] workers = corporation.getWorkers();
        try (FileWriter fileWriter = new FileWriter(FILE_NAME);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            for (Worker worker : workers){
                bufferedWriter.write(worker.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e){
            throw new DataExportException("Error while writing data to " + FILE_NAME+" file");
        }

    }
    private Worker createObjectFromString(String csvText){
        String[]split = csvText.split(";");
        String type = split[0];
        if (Employee.TYPE.equals(type)){
            return createEmployee(split);
        }else if(Intern.TYPE.equals(type)){
            return createIntern(split);
        }
        throw new InvalidDataException("Data type - "+type+" - unknown");
    }

    private Employee createEmployee(String[] data){
        String name = data[1];
        String surname = data[2];
        String position = data[3];
        String function = data[4];
        int level = Integer.valueOf(data[5]);
        return new Employee(name, surname, position, function, level);
    }

    private Intern createIntern(String[] data){
        String name = data[1];
        String surname = data[2];
        String position = data[3];
        String univeristyName = data[4];
        int yearOfStudies = Integer.valueOf(data[5]);
        return new Intern(name, surname, position, univeristyName, yearOfStudies);
    }

}

