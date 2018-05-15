package fileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FileReader {
    private ArrayList<String> files = new ArrayList<>();
    private HashMap<String, HashMap<String, String>> sequences = new HashMap<>();

    public ArrayList<String> getFiles() {
        return files;
    }

    public HashMap<String, HashMap<String, String>> getSequence() {
        return sequences;
    }

    //Constructs FileReader object using filePath
    public FileReader(String filePath) throws IOException {
        listFiles(filePath);

        for (String file : files) {
            if (file != null) {
                sequences.put(file,sequenceBuilder(file));
            }
        }
    }

    //Constructs FileReader object from file, that was just downloaded
    public FileReader(File folder) throws IOException {
        File[] files = folder.listFiles();
        Date currentTime = new Date();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    BasicFileAttributes atr = Files.readAttributes(file1.toPath(), BasicFileAttributes.class);
                    if (currentTime.getTime() - atr.creationTime().toMillis() < 30000) {
                        this.files.add(file1.getCanonicalPath());
                        sequences.put(file1.getCanonicalPath(), sequenceBuilder(file1.getCanonicalPath()));
                    }
                }
            }

        } else {
            System.err.println("Chosen directory is empty");
        }

    }

    private void listFiles(String filePath) throws IOException {
        File directory = new File(filePath);
        if (directory.isFile()) {
            files.add(directory.getCanonicalPath());
        } else {
            File[] list = directory.listFiles();

            if (list != null) {
                for (File file : list) {
                    if (file.isFile()) {
                        files.add(file.getCanonicalPath());
                    } else {
                        listFiles(file.getCanonicalPath());
                    }
                }
            }
        }
    }

    private HashMap<String, String> sequenceBuilder(String filePath) {
        try {
            String key = "";
            String sequence = "";
            String line;
            File file = new File(filePath);
            FileInputStream fstream = new FileInputStream(file);
            HashMap<String, String> sequences = new HashMap<>();
            BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
            while (((line = in.readLine()) != null)) {
                if (line.isEmpty()) {
                    line = in.readLine();
                }
                if (line != null) {
                    if (line.charAt(0) == '>') {
                        if (key.isEmpty()) {
                            key = line.substring(1, line.indexOf(' '));
                        } else {
                            sequences.put(key, sequence);
                            key = line.substring(1, line.indexOf(' '));
                            sequence = "";
                        }
                    } else {
                        sequence = sequence.concat(line.substring(0, line.length()));
                    }
                }
            }
            sequences.put(key, sequence);
            return sequences;
        } catch (IOException e) {
            System.err.println("Can not read the file");
            return null;
        }

    }
}
