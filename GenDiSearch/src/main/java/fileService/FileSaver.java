package fileService;

import java.io.File;
import java.util.HashMap;

public class FileSaver {
    private String folder;
    private String fileName;
    private HashMap<String,String> stat;

    public FileSaver(String folder, String fileName, HashMap<String, String> stat){
        this.folder=folder;
        this.fileName=fileName;
        this.stat=stat;
    }

    public String getFolder() {
        return folder;
    }

    public String getFileName() {
        return fileName;
    }

    public HashMap<String, String> getStat() {
        return stat;
    }

    /*public void save(){
        File file=new File(folder+System.sepa)
    }*/
}
