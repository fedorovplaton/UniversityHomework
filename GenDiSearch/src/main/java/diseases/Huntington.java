package diseases;

import fileService.FileDownloader;
import fileService.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Huntington {
    private FileReader file;
    private HashMap<String, HashMap<String,String>> statistics=new HashMap<>();

    //Downloads fasta files from GenBank, which may contain a sequences with Huntington disease
    public Huntington(String downloadPath, String driverPath) throws InterruptedException, IOException {
        FileDownloader downloader = new FileDownloader("4p16.3", downloadPath, driverPath);
        downloader.genbankDownload();

        File download = new File(downloadPath);
        file = new FileReader(download);
    }

    public Huntington(FileReader file){
        this.file=file;
    }



    public FileReader getFile() {
        return file;
    }

    public HashMap<String, HashMap<String,String>> getStatistics() {
        return statistics;
    }

    //Finds a huntington disease in sequences in files and saves statistics in statistics map, where key is a file path
    public void find() {
        Set<String> files = file.getSequence().keySet();
        String sequence;

        Pattern cags=Pattern.compile("(CAG){2,}");
        Pattern cag=Pattern.compile("CAG");

        int matches=0;
        int count=0;

        for (String file:files) {

            HashMap<String,String> sequences= this.file.getSequence().get(file);
            Set<String> keys=sequences.keySet();

            HashMap<String,String> stat=new HashMap<>();

            for (String key : keys) {

                sequence = sequences.get(key);

                if (sequence == null) {
                    System.err.println("No such sequence in map :: sequence id: " + key);
                } else {
                    Matcher m = cags.matcher(sequence);
                    while (m.find()) {
                        Matcher ms = cag.matcher(m.group());
                        while (ms.find()) {
                            count++;
                        }
                        if (count > matches) {
                            matches = count;
                        }
                        count = 0;
                    }

                    if (matches < 26) {
                        stat.put(key, "There is " + matches + " CAG codons in your gene." +
                                "Classification: Normal." +
                                "Disease status: Will not be affected." +
                                "Risk to offspring: None.");
                    /*System.out.println(key+" :: "+"There is "+matches+" CAG codons in your gene." +
                            "Classification: Normal." +
                            "Disease status: Will not be affected." +
                            "Risk to offspring: None.");*/
                    } else if (matches <= 35) {
                        stat.put(key, "There is " + matches + " CAG codons in your gene." +
                                "Classification: Intermediate." +
                                "Disease status: Will not be affected." +
                                "Risk to offspring: Elevated but <<50%.");
                    /*System.out.println(key+" :: "+"There is "+matches+" CAG codons in your gene." +
                            "Classification: Intermediate." +
                            "Disease status: Will not be affected." +
                            "Risk to offspring: Elevated but <<50%.");*/
                    } else if (matches <= 39) {
                        stat.put(key, "There is " + matches + " CAG codons in your gene." +
                                "Classification: Reduced Penetrance." +
                                "Disease status: May or may not be affected." +
                                "Risk to offspring: 50%.");
                    /*System.out.println(key+" :: "+"There is "+matches+" CAG codons in your gene." +
                            "Classification: Reduced Penetrance." +
                            "Disease status: May or may not be affected." +
                            "Risk to offspring: 50%.");*/
                    } else {
                        stat.put(key, "There is " + matches + " CAG codons in your gene." +
                                "Classification: Full Penetrance." +
                                "Disease status: Will be affected." +
                                "Risk to offspring: 50%.");
                    /*System.out.println(key+" :: "+"There is "+matches+" CAG codons in your gene." +
                            "Classification: Full Penetrance." +
                            "Disease status: Will be affected." +
                            "Risk to offspring: 50%.");*/
                    }
                    matches = 0;
                    count = 0;

                }
            }

            statistics.put(file,stat);
        }
    }
}
