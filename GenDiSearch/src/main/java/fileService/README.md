# Package `fileService`

## `FileDownloader.java`

  1. Fields:
      * `private String query` query to search string at [NCBI](https://www.ncbi.nlm.nih.gov/nuccore)
      * `private String downloadPath` absolute path where downloaded file will be saved
      * `privateString driverPath` absolute path to chromedriver.exe
  2. Methods:
      * `public FileDownloader` constructor
      * `public void genbankDownload()` downloads file
      
## `FileSaver.java`

  1. Fields:
      * `private String folder` absolute path of directory, where file will be saved
      * `private String fileName` name of saving file
      * `private HashMap<String,HashMap<String,String >> statistics` information about found diseases
  2. Methods:
      * `public FileSaver` constructor
      * `public void save()` saves file in `folder` named as `fileName` and containing `statistics`
      
## `FileReader.java` 

  1. Fields:
      * `private ArrayList<String> files` contains list of absolute paths to files which will be read
      * `private HashMap<String, HashMap<String, String>> sequences` contains absolute paths to files 
      with sequences with their identifiers
  2. Methods:
      * `public FileReader(String filePath)` constructor that reads files from `filePath` 
      * `public FileReader(File folder)` consrtuctor that reads file that was jast downloaded
      * `private void listFiles(String filePath)` recursively finds all files in `filePath` directory
      * `private HashMap<String, String> sequenceBuilder(String filePath)` reads information from `filePath` file and saves
      in `private HashMap<String, HashMap<String, String>> sequences`
