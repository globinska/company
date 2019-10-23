package corp.io.file;

import corp.exception.DataExportException;
import corp.exception.DataImportException;
import corp.model.Corporation;

import java.io.*;

public class SerializableFileMenager implements FileMenager{
    private static final String FILE_NAME = "Corporation.o";


    @Override
    public Corporation importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Corporation) ois.readObject();
        } catch (FileNotFoundException e){
            throw new DataImportException("No file "+FILE_NAME+" found");
        } catch (IOException e){
            throw new DataImportException("Error while reading "+FILE_NAME+" file");
        } catch (ClassNotFoundException e){
            throw new DataImportException("Data type error in "+FILE_NAME+" file");
        }

    }


    @Override
    public void exportData(Corporation corporation) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(corporation);
        } catch (FileNotFoundException e) {
            throw new DataExportException("No file " + FILE_NAME+" found");
        } catch (IOException e) {
            throw new DataExportException("Error while writing data to " + FILE_NAME+" file");
        }
    }
}