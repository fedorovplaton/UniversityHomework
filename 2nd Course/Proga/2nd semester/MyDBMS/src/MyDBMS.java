/**
 * MyDBMS has been created by moldybizkit on 09.03.2018.
 */

import java.util.*;
import java.io.*;


class Doc {
    private String name;
    private String surname;
    private String date;
    private boolean sex;
    private int salary;
    public int id;

    public Doc() {
    }

    public Doc(String name,
               String surname,
               String date,
               boolean sex,
               int salary) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.sex = sex;
        this.salary = salary;
    }

    public void read(Scanner in) {
        this.name = in.next();
        this.surname = in.next();
        this.date = in.next();
        if (in.next().equalsIgnoreCase("male")) {
            this.sex = true;
        }
        this.salary = in.nextInt();
    }

    public void file_read(Scanner in){

        this.name = in.next();
        this.surname = in.next();
        this.date = in.next();
        if (in.next().equalsIgnoreCase("male")) {
            this.sex = true;
        }
        this.salary = in.nextInt();
        this.id=in.nextInt();
    }

    public void print() {
        if (this.sex) {
            System.out.println(name + " " + surname + " " + date + " male " + " " + salary + " " + id);
        } else {
            System.out.println(name + " " + surname + " " + date + " female " + " " + salary + " " + id);
        }
    }

}

class Pacient {
    private String name;
    private String surname;
    private String date;
    private boolean sex;    //in case male sex=true, else sex=false ^^
    private int weight;
    public int id;
    public int doc_id;

    public Pacient() {
        this.doc_id = -1;
    }

    public Pacient(String name,
                   String surname,
                   String date,
                   boolean sex,
                   int weight) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.sex = sex;
        this.weight = weight;
        ;
    }


    public void read(Scanner in) {
        this.name = in.next();
        this.surname = in.next();
        this.date = in.next();
        if (in.next().equalsIgnoreCase("male")) {
            this.sex = true;
        }
        this.weight = in.nextInt();
    }

    public void file_read(Scanner in){

        this.name = in.next();
        this.surname = in.next();
        this.date = in.next();
        if (in.next().equalsIgnoreCase("male")) {
            this.sex = true;
        }
        this.weight = in.nextInt();
        this.id=in.nextInt();
        this.doc_id=in.nextInt();
    }

    public void print() {
        if (this.sex) {
            System.out.println(name + " " + surname + " " + date + " male " + " " + weight + " " + id + " " + doc_id);
        } else {
            System.out.println(name + " " + surname + " " + date + " female " + " " + weight + " " + id + " " + doc_id);
        }
    }
}

class ID {
    private int doc;
    private int pacient;

    public ID() {

    }

    public ID(int i, int j) {
        this.doc = i;
        this.pacient = j;
    }

    public void file_read(Scanner in){
        this.doc=in.nextInt();
        this.pacient=in.nextInt();
    }

}



public class MyDBMS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vector pacients = new Vector();
        Vector doctors = new Vector();
        Vector ids = new Vector();
        String s = in.next();
        String file;
        int i = 0;
        int j = 0;
        while (!s.equalsIgnoreCase("quit")) {
            if (s.equalsIgnoreCase("create")) {                 //Creating a database or a table
                s = in.next();
                if (s.equalsIgnoreCase("database")) {           //Creating a database with or without table
                    System.out.println("Enter the name of the new database");
                    s = in.next();
                    File database = new File("C:\\DATABASES\\" + s);
                    if (database.exists()) {
                        System.out.println("Database is already exists");
                    } else if (database.mkdirs()) {
                        System.out.println("Databases" + s + " created successfully");
                    } else {
                        System.out.println("Unexpected error, please enter 'quit'");
                    }
                    File id=new File("C:\\DATABASES\\"+database.getName()+"ID.txt");
                    try{
                        id.createNewFile();
                    } catch(IOException ex){

                    }
                    s = in.next();
                    if (s.equalsIgnoreCase("table")) {          //With table if true
                        System.out.println("Enter the name of the new table");
                        s = in.next();
                        File table = new File("C:\\DATABASES\\" + database.getName() + File.separator + s + ".txt");
                        if (table.exists()) {
                            System.out.println("Table is already exist");
                        } else {
                            try {
                                table.createNewFile();
                                System.out.println("Table " + s + " created successfully");
                            } catch (IOException e) {
                                System.out.println("Unexpected error, please enter 'quit'");
                            }
                        }
                        s = in.next();
                    }
                } else if (s.equalsIgnoreCase("table")) {      //Creating only a table
                    System.out.println("Enter the name of the database, where you want to create a table");
                    s = in.next();
                    System.out.println("Enter the name of the new table");
                    file = in.next();
                    File table = new File("C:\\DATABASES\\" + s + File.separator + file + ".txt");
                    if (table.exists()) {
                        System.out.println("Table is already exist");
                    } else {
                        try {
                            table.createNewFile();
                            System.out.println("Table " + file + " created successfully");
                        } catch (IOException e) {
                            System.out.println("Unexpected error, please enter 'quit'");
                        }
                    }
                    s = in.next();
                }
            }
            if (s.equalsIgnoreCase("open")) {                   //Opening a database
                System.out.println("Enter the name of database");
                s = in.next();
                File database = new File("C:\\DATABASES\\" + s);
                if (!database.exists()) {
                    System.out.println("Database " + s + " doesn't exists");
                } else {
                    System.out.println("Enter the name of the table");
                    s = in.next();
                    File table1 = new File("C:\\DATABASES\\" + database.getName() + File.separator + s + ".txt");
                    try {
                        Scanner sc1 = new Scanner(table1);
                        System.out.println("Enter the type of the table(pacient/doctor)");
                        s = in.next();
                        if (s.equalsIgnoreCase("pacient")) {
                            System.out.println("Enter the name of doctor's table");
                            s=in.next();
                            File table2=new File("C:\\DATABASES\\" + database.getName() + File.separator + s + ".txt");
                            try{
                                Scanner sc2=new Scanner(table2);
                                File Id=new File("C:\\DATABASES\\"+database.getName()+File.separator+"ID.txt");
                                try{
                                    Scanner sc3=new Scanner(Id);
                                    while (sc1.hasNextLine()){
                                        Pacient pacient=new Pacient();
                                        pacient.file_read(sc1);
                                        pacients.addElement(pacient);
                                    }
                                    while (sc2.hasNextLine()){
                                        Doc doc=new Doc();
                                        doc.file_read(sc2);
                                        doctors.addElement(doc);
                                    }
                                    while (sc3.hasNextLine()){
                                        ID id=new ID();
                                        id.file_read(sc3);
                                        ids.addElement(id);
                                    }
                                } catch(FileNotFoundException e3){
                                    System.out.println("Can't find ids");
                                }
                            } catch (FileNotFoundException e2){
                                System.out.println("Table " + table2.getName() + " does not exists");
                            }

                        } else if (s.equalsIgnoreCase("doctor")) {

                        } else {
                            System.out.print("Incorrect query");
                        }
                    } catch (FileNotFoundException e1) {
                        System.out.println("Table " + table1.getName() + " does not exists");
                    }
                }
            }
            if (s.equalsIgnoreCase("add")) {
                s = in.next();
                if (s.equalsIgnoreCase("patient")) {
                    Pacient pacient = new Pacient();
                    pacient.read(in);
                    pacient.doc_id = in.nextInt();
                    pacient.id = i;
                    pacients.addElement(pacient);
                    ID id = new ID(pacient.doc_id, pacient.id);
                    ids.addElement(id);
                    pacient = (Pacient) pacients.elementAt(i);
                    pacient.print();
                    i++;
                } else if (s.equalsIgnoreCase("doctor")) {
                    Doc doc = new Doc();
                    doc.read(in);
                    doc.id = j;
                    doctors.addElement(doc);
                    doc = (Doc) doctors.elementAt(j);
                    doc.print();
                    j++;
                } else {
                    Pacient pacient = new Pacient();
                    pacient.read(in);
                    pacient.doc_id = j;
                    pacient.id = i;
                    ID id = new ID(pacient.doc_id, pacient.id);
                    pacients.addElement(pacient);
                    pacient = (Pacient) pacients.elementAt(i);
                    pacient.print();
                    i++;
                    Doc doc = new Doc();
                    doc.read(in);
                    doc.id = j;
                    doctors.addElement(doc);
                    doc = (Doc) doctors.elementAt(j);
                    doc.print();
                    j++;
                }
                s = in.next();
            }
        }
    }

}