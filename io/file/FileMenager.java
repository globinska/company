package corp.io.file;

import corp.model.Corporation;

public interface FileMenager {
    Corporation importData();
    void exportData(Corporation corporation);
}
