package corp.io.file;

import corp.exception.NoSuchFileTypeException;
import corp.io.ConsolePrinter;
import corp.io.DataReader;

public class FileMenagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileMenagerBuilder (ConsolePrinter printer, DataReader reader){
        this.printer = printer;
        this.reader = reader;
    }
    public FileMenager build() {
        printer.printLine("Choose the data format:");
        FileType fileType = getFileType();
        switch (fileType) {
            case CSV:
                return new CsvFileMenager();
            case SERIAL:
                return new SerializableFileMenager();
            default:
                throw new NoSuchFileTypeException("Wrong data format");
        }

    }
    private FileType getFileType(){
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("nieobsługiwany typ danych, wybierz ponownie");
            }
        } while (!typeOk);
        return result;
    }
    private void printTypes(){
        for (FileType value: FileType.values()){
            printer.printLine(value.name());
        }
    }

}
