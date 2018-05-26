# Package `diseases`

## What should Your class include
  1. Fields:
    * `private FileReader` from package fileService (reads informaion from file)
    * `private HashMap<String, HashMap<String,String>>` (contains statistics, keys of external map are paths of files with sequences, 
    keys of internal map are identifier of the sequence)
  2. Methods:
    * Cosnructor that only uses `FileReader` object
    * Constructor that uses `String downloadPath` (absolute path where file from [NCBI](https://www.ncbi.nlm.nih.gov/nuccore) will be
    saved) and `String driverPath` (absolute path to chromedriver.exe)
    * Method that finds your disease
### Huntington.java
  * `public Huntington(String downloadPath, String driverPath)` downloads file from [NCBI](https://www.ncbi.nlm.nih.gov/nuccore)
  * `public Huntington(FileReader file)` simple counstructor
  * `public void find()` finds a huntington disease in sequences in files and saves statistics in statistics map
