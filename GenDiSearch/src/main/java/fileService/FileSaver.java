package fileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class FileSaver {
    private String folder;
    private String fileName;
    private HashMap<String,HashMap<String,String >> statistics;

    public FileSaver(String folder, String fileName, HashMap<String, HashMap<String,String >> statistics){
        this.folder=folder;
        this.fileName=fileName;
        this.statistics=statistics;
    }

    public String getFolder() {
        return folder;
    }

    public String getFileName() {
        return fileName;
    }

    public HashMap<String, HashMap<String,String >> getStatistics() {
        return statistics;
    }

    public void save(){
        File stat=new File(folder+File.separator+fileName+".fasta");
        if (stat.exists()){
            System.out.println("File already exists");
        } else {
            try {
                if (stat.createNewFile()) {
                    System.out.println("File created successfully");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            try (FileWriter writer = new FileWriter(stat, false)) {
                Set<String> files = statistics.keySet();

                for (String file : files) {
                    HashMap<String, String> sequences = statistics.get(file);

                    Set<String> keys = sequences.keySet();

                    writer.write(file + ":\n");

                    for (String key : keys) {
                        if (!key.equals("")){
                            writer.write(key + " :: " + sequences.get(key) + "\n");
                        }
                    }
                }

                writer.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
