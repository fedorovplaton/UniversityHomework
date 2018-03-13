/**
 * MyDBMS has been created by moldybizkit on 09.03.2018.
 */

import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.nio.*;


class Doc {
    public String name;
    public String surname;
    public String date;
    public boolean sex;
    public int salary;
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

    public void file_read(Scanner in) {

        this.name = in.next();
        this.surname = in.next();
        this.date = in.next();
        if (in.next().equalsIgnoreCase("male")) {
            this.sex = true;
        }
        this.salary = in.nextInt();
        this.id = in.nextInt();
    }

    public void print() {
        if (this.sex) {
            System.out.print(name + " " + surname + " " + date + " male " + " " + salary + " " + id + " ");
        } else {
            System.out.print(name + " " + surname + " " + date + " female " + " " + salary + " " + id + " ");
        }
    }

    public void println() {
        if (this.sex) {
            System.out.println(name + " " + surname + " " + date + " male " + " " + salary + " " + id);
        } else {
            System.out.println(name + " " + surname + " " + date + " female " + " " + salary + " " + id);
        }
    }

    public void printf(File file) {
        String text;
        if (this.sex) {
            text = (name + " " + surname + " " + date + " male " + " " + salary + " " + id +"\n");
        } else {
            text = (name + " " + surname + " " + date + " female " + " " + salary + " " + id +"\n");
        }
        String filePath = file.getAbsolutePath();
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can't print doc in file");
        }
    }

}

class Patient {
    public String name;
    public String surname;
    public String date;
    public boolean sex;    //in case male sex=true, else sex=false ^^
    public int weight;
    public int id;
    public int doc_id;

    public Patient() {
        this.doc_id = -1;
    }

    public Patient(String name,
                   String surname,
                   String date,
                   boolean sex,
                   int weight) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.sex = sex;
        this.weight = weight;
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

    public void file_read(Scanner in) {

        this.name = in.next();
        this.surname = in.next();
        this.date = in.next();
        if (in.next().equalsIgnoreCase("male")) {
            this.sex = true;
        }
        this.weight = in.nextInt();
        this.id = in.nextInt();
        this.doc_id = in.nextInt();
    }

    public void print() {
        if (this.sex) {
            System.out.print(name + " " + surname + " " + date + " male " + " " + weight + " " + id + " " + doc_id + " ");
        } else {
            System.out.print(name + " " + surname + " " + date + " female " + " " + weight + " " + id + " " + doc_id + " ");
        }
    }

    public void println() {
        if (this.sex) {
            System.out.println(name + " " + surname + " " + date + " male " + " " + weight + " " + id + " " + doc_id);
        } else {
            System.out.println(name + " " + surname + " " + date + " female " + " " + weight + " " + id + " " + doc_id);
        }
    }

    public void printf(File file) {
        String text;
        if (this.sex) {
            text = name + " " + surname + " " + date + " male " + " " + weight + " " + id + " " + doc_id+"\n"  ;
        } else {
            text =name + " " + surname + " " + date + " female " + " " + weight + " " + id + " " + doc_id+"\n" ;
        }
        String filePath = file.getAbsolutePath();
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can't print patient in file");
        }
    }
}

class ID {
    public int doc;
    public int patient;

    public ID() {

    }

    public ID(int i, int j) {
        this.doc = i;
        this.patient = j;
    }

    public void file_read(Scanner in) {
        this.doc = in.nextInt();
        this.patient = in.nextInt();
    }

    public void printf(File file) {
        String text =doc + " " + patient+"\n" ;
        String filePath = file.getAbsolutePath();
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Can't print id in file");
        }
    }

}


public class MyDBMS {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vector patients = new Vector();
        Vector doctors = new Vector();
        Vector ids = new Vector();

        System.out.println("Hi! That's myDBMS. You can create/open/delete your database or table. \n" +
                "Remember following rules:\n" +
                "1) This DB provides two types of tables: patients and doctors.\n" +
                "2) Patients' table contains 7 columns:\n" +
                "name(string), surname(string), date(string), sex(male/female), weight(int) enter when add and \n" +
                "id(int) and doc_id(int) enter when ask.\n" +
                "3) Doctors' table contains 5 columns\n" +
                "name(string), surname(string), date(string), sex(male/female), salary(int)\n" +
                "4) When you enter a find condition divide characters with space (except '!=' symbol)\n" +
                "5) You can find tuples that have equal(=) or non-equal(!=) names, surnames, have defined sex,\n" +
                "id or doc_id or have weight or salary more(>), less(<), equal(=) or non-equal(!=) you need.\n" +
                "6) If you want stop add tuples, enter stop, if you want quit enter quit(works not always)\n" +
                "That's all. Good luck!\n" +
                "Enter command(create/open/delete)");
        String s = in.next();
        String file;
        int i = 0;
        int j = 0;
        while (!s.equalsIgnoreCase("quit")) {
            if (s.equalsIgnoreCase("create")) {                 //Creating a database or a table
                System.out.println("Enter a type of creating object(database/table)");
                s = in.next();
                if (s.equalsIgnoreCase("database")) {           //Creating a database with or without table
                    System.out.println("Enter the name of the new database");
                    s = in.next();
                    File database = new File("C:\\DATABASES\\" + s);
                    if (database.exists()) {
                        System.out.println("Database already exists");
                    } else if (database.mkdirs()) {
                        System.out.println("Database " + s + " created successfully");
                        File id = new File("C:\\DATABASES\\" + database.getName() +File.separator +"ID.txt");
                        try {
                            id.createNewFile();
                        } catch (IOException ex) {
                            System.out.println("Can't create ID.txt");
                        }
                    } else {
                        System.out.println("Unexpected error, please enter 'quit'");
                    }
                    s = in.next();
                    if (s.equalsIgnoreCase("table")) {          //With table if true
                        System.out.println("Enter the name of the new table");
                        s = in.next();
                        File table = new File("C:\\DATABASES\\" + database.getName() + File.separator + s + ".txt");
                        if (table.exists()) {
                            System.out.println("Table already exists");
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
                        System.out.println("Table already exists");
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
                    System.out.println("Database " + s + " doesn't exist");
                    s = in.next();
                } else {
                    System.out.println("Enter the name of the table. It must be patients' table!");
                    s = in.next();
                    File table1 = new File("C:\\DATABASES\\" + database.getName() + File.separator + s + ".txt");
                    try {
                        Scanner sc1 = new Scanner(table1);
                        System.out.println("Enter the name of doctors' table");
                        s = in.next();
                        File table2 = new File("C:\\DATABASES\\" + database.getName() + File.separator + s + ".txt");
                        try {
                            Scanner sc2 = new Scanner(table2);
                            File Id = new File("C:\\DATABASES\\" + database.getName() + File.separator + "ID.txt");
                            try {
                                Scanner sc3 = new Scanner(Id);
                                while (sc1.hasNext()) {
                                    Patient patient = new Patient();
                                    patient.file_read(sc1);
                                    patients.addElement(patient);
                                    i=patient.id;
                                }
                                while (sc2.hasNext()) {
                                    Doc doc = new Doc();
                                    doc.file_read(sc2);
                                    doctors.addElement(doc);
                                    j=doc.id;
                                }
                                while (sc3.hasNext()) {
                                    ID id = new ID();
                                    id.file_read(sc3);
                                    ids.addElement(id);
                                }
                                System.out.println("Enter next command(add/find)");
                                s = in.next();
                                if (s.equalsIgnoreCase("add")) {
                                    while (!s.equalsIgnoreCase("stop")) {
                                        System.out.println("Enter the type of the table(patients/doctors) or no in case you want add both");
                                        s = in.next();
                                        if (s.equalsIgnoreCase("patients")) {
                                            i++;
                                            Patient patient = new Patient();
                                            patient.read(in);
                                            System.out.println("Enter doctor's Id");
                                            patient.doc_id = in.nextInt();
                                            patient.id = i;
                                            patient.printf(table1);
                                            patients.addElement(patient);
                                            ID id = new ID(patient.doc_id, patient.id);
                                            id.printf(Id);
                                            ids.addElement(id);
                                            patient.println();
                                        } else if (s.equalsIgnoreCase("doctors")) {
                                            j++;
                                            Doc doc = new Doc();
                                            doc.read(in);
                                            doc.id = j;
                                            doctors.addElement(doc);
                                            doc.printf(table2);
                                            doc.println();
                                        } else if (s.equalsIgnoreCase("no")){
                                            j++;
                                            i++;
                                            Patient patient = new Patient();
                                            patient.read(in);
                                            patient.doc_id = j;
                                            patient.id = i;
                                            patient.printf(table1);
                                            ID id = new ID(patient.doc_id, patient.id);
                                            id.printf(Id);
                                            ids.addElement(id);
                                            patients.addElement(patient);
                                            Doc doc = new Doc();
                                            doc.read(in);
                                            doc.id = j;
                                            doc.printf(table2);
                                            doctors.addElement(doc);
                                            patient.print();
                                            doc.println();
                                        }
                                        s = in.next();
                                    }
                                    s = in.next();
                                }
                                if (s.equalsIgnoreCase("find")) {
                                    System.out.println("Enter the type of the table(patients/doctors)");
                                    s = in.next();
                                    String ch;
                                    String act;
                                    String cond;
                                    int intcond;
                                    int m = 0;
                                    if (s.equalsIgnoreCase("patients")) {
                                        System.out.println("Enter an action(print/delete)");
                                        act = in.next();
                                        if (act.equalsIgnoreCase("print")) {
                                            System.out.println("Enter a column");
                                            s = in.next();
                                            if (s.equalsIgnoreCase("name")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                Patient patient;
                                                cond = in.next();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.name.equals(cond)) && (ch.equals("="))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    } else if ((!patient.name.equals(cond)) && (ch.equals("!="))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("surname")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                Patient patient;
                                                cond = in.next();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.surname.equals(cond)) && (ch.equals("="))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    } else if ((!patient.surname.equals(cond)) && (ch.equals("!="))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("sex")) {
                                                System.out.println("Enter a condition(male/female)");
                                                Patient patient;
                                                cond = in.next();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.sex) && (cond.equals("male"))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    } else if ((!patient.sex) && (cond.equals("female"))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("date")) {
                                                System.out.println("Do it by yourself!");
                                            } else if (s.equalsIgnoreCase("weight")) {
                                                System.out.println("Enter a condition(=/!=/>/<)");
                                                ch = in.next();
                                                Patient patient;
                                                intcond = in.nextInt();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.weight == intcond) && (ch.equals("="))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    } else if ((patient.weight != intcond) && (ch.equals("!="))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    } else if ((patient.weight > intcond) && (ch.equals(">"))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    } else if ((patient.weight < intcond) && (ch.equals("<"))) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("id")) {
                                                System.out.println("Enter a condition(Id number)");
                                                Patient patient;
                                                intcond = in.nextInt();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if (patient.id == intcond) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("doc_id")) {
                                                System.out.println("Enter a condition(Id number)");
                                                Patient patient;
                                                intcond = in.nextInt();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if (patient.doc_id == intcond) {
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        patient.print();
                                                        doc.println();
                                                    }
                                                }
                                            } else {
                                                System.out.println("Incorrect query");
                                            }
                                        } else if (act.equalsIgnoreCase("delete")) {
                                            try {
                                                PrintWriter pw = new PrintWriter(table1);
                                                pw.print("");
                                                pw.close();
                                            } catch (FileNotFoundException e) {
                                                System.out.println("Some problems");
                                            }
                                            try {
                                                PrintWriter pw = new PrintWriter(Id);
                                                pw.print("");
                                                pw.close();
                                            } catch (FileNotFoundException e) {
                                                System.out.println("Some problems");
                                            }
                                            System.out.println("Enter a column");
                                            s = in.next();
                                            if (s.equalsIgnoreCase("name")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                Patient patient;
                                                cond = in.next();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.name.equals(cond)) && (ch.equals("="))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else if ((!patient.name.equals(cond)) && (ch.equals("!="))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else {
                                                        patient.printf(table1);
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("surname")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                Patient patient;
                                                cond = in.next();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.surname.equals(cond)) && (ch.equals("="))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else if ((!patient.surname.equals(cond)) && (ch.equals("!="))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else {
                                                        patient.printf(table1);
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("sex")) {
                                                System.out.println("Enter a condition(male/female)");
                                                Patient patient;
                                                cond = in.next();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.sex) && (cond.equals("male"))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else if ((!patient.sex) && (cond.equals("female"))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else {
                                                        patient.printf(table1);
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("date")) {
                                                System.out.println("Do it by yourself!");
                                                for (int o = 0; o < patients.size(); o++) {
                                                    Patient patient = (Patient) patients.elementAt(o);
                                                    patient.printf(table1);
                                                }
                                                for (int o = 0; o < ids.size(); o++) {
                                                    ID id = (ID) ids.elementAt(o);
                                                    id.printf(Id);
                                                }
                                            } else if (s.equalsIgnoreCase("weight")) {
                                                System.out.println("Enter a condition(=/!=/>/<)");
                                                ch = in.next();
                                                Patient patient;
                                                intcond = in.nextInt();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if ((patient.weight == intcond) && (ch.equals("="))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else if ((patient.weight != intcond) && (ch.equals("!="))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else if ((patient.weight > intcond) && (ch.equals(">"))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else if ((patient.weight < intcond) && (ch.equals("<"))) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else {
                                                        patient.printf(table1);
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("id")) {
                                                System.out.println("Enter a condition(Id number)");
                                                Patient patient;
                                                intcond = in.nextInt();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if (patient.id == intcond) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else {
                                                        patient.printf(table1);
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("doc_id")) {
                                                System.out.println("Enter a condition(Id number)");
                                                Patient patient;
                                                intcond = in.nextInt();
                                                for (int k = 0; k < patients.size(); k++) {
                                                    patient = (Patient) patients.elementAt(k);
                                                    if (patient.doc_id == intcond) {
                                                        m = 0;
                                                        ID id = (ID) ids.elementAt(m);
                                                        while (patient.id != id.patient) {
                                                            id.printf(Id);
                                                            m++;
                                                            id = (ID) ids.elementAt(m);
                                                        }
                                                        patient.print();
                                                        m = 0;
                                                        Doc doc = (Doc) doctors.elementAt(m);
                                                        while (patient.doc_id != doc.id) {
                                                            m++;
                                                            doc = (Doc) doctors.elementAt(m);
                                                        }
                                                        doc.println();
                                                        patients.removeElementAt(k);
                                                        k--;
                                                        ids.removeElementAt(m);
                                                    } else {
                                                        patient.printf(table1);
                                                    }
                                                }
                                            } else {
                                                System.out.println("Incorrect query");
                                                for (int o = 0; o < patients.size(); o++) {
                                                    Patient patient = (Patient) patients.elementAt(o);
                                                    patient.printf(table1);
                                                }
                                                for (int o = 0; o < ids.size(); o++) {
                                                    ID id = (ID) ids.elementAt(o);
                                                    id.printf(Id);
                                                }
                                            }
                                        } else {
                                            System.out.println("Incorrect query");
                                        }

                                    } else if (s.equalsIgnoreCase("doctors")) {
                                        System.out.println("Enter an action(print/delete)");
                                        act = in.next();
                                        if (act.equalsIgnoreCase("print")) {
                                            System.out.println("Enter a column");
                                            s = in.next();
                                            if (s.equalsIgnoreCase("name")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                cond = in.next();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.name.equals(cond) && ch.equals("=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    } else if ((!doc.name.equals(cond)) && ch.equals("!=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("surname")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                cond = in.next();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.surname.equals(cond) && ch.equals("=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    } else if ((!doc.surname.equals(cond)) && ch.equals("!=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("date")) {
                                                System.out.println("Do it by yourself!");
                                            } else if (s.equalsIgnoreCase("sex")) {
                                                System.out.println("Enter a condition(male/female)");
                                                ch = in.next();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.sex && ch.equals("male")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    } else if ((!doc.sex) && ch.equals("female")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("salary")) {
                                                System.out.println("Enter a condition(=/!=/>/<)");
                                                ch = in.next();
                                                intcond = in.nextInt();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if ((doc.salary == intcond) && ch.equals("=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    } else if ((doc.salary != intcond) && ch.equals("!=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    } else if ((doc.salary > intcond) && ch.equals(">")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    } else if ((doc.salary < intcond) && ch.equals("<")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    }
                                                }
                                            } else if (s.equalsIgnoreCase("id")) {
                                                System.out.println("Enter a condition(Id number)");
                                                intcond = in.nextInt();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if ((doc.id == intcond)) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {
                                                System.out.println("Incorrect query");
                                            }
                                        } else if (act.equalsIgnoreCase("delete")) {

                                            try {
                                                PrintWriter pw = new PrintWriter(table1);
                                                pw.print("");
                                                pw.close();
                                            } catch (FileNotFoundException e) {
                                                System.out.println("Some problems");
                                            }
                                            try {
                                                PrintWriter pw = new PrintWriter(table2);
                                                pw.print("");
                                                pw.close();
                                            } catch (FileNotFoundException e) {
                                                System.out.println("Some problem");
                                            }
                                            try {
                                                PrintWriter pw = new PrintWriter(Id);
                                                pw.print("");
                                                pw.close();
                                            } catch (FileNotFoundException e) {
                                                System.out.println("Some problems");
                                            }
                                            System.out.println("Enter a column");
                                            s = in.next();
                                            if (s.equalsIgnoreCase("name")) {
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                cond = in.next();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.name.equals(cond) && ch.equals("=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else if ((!doc.name.equals(cond)) && ch.equals("!=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else {
                                                        doc.printf(table2);
                                                    }
                                                }
                                            }else if (s.equalsIgnoreCase("surname")){
                                                System.out.println("Enter a condition(=/!=)");
                                                ch = in.next();
                                                cond = in.next();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.surname.equals(cond) && ch.equals("=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else if ((!doc.surname.equals(cond)) && ch.equals("!=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else {
                                                        doc.printf(table2);
                                                    }
                                                }
                                            }else if(s.equalsIgnoreCase("date")){
                                                System.out.println("Do it by yourself!");

                                                for (int o = 0; o < patients.size(); o++) {
                                                    Patient patient = (Patient) patients.elementAt(o);
                                                    patient.printf(table1);
                                                }
                                                for (int o = 0; o < doctors.size(); o++) {
                                                    Doc doc = (Doc) doctors.elementAt(o);
                                                    doc.printf(table2);
                                                }
                                                for (int o = 0; o < ids.size(); o++) {
                                                    ID id = (ID) ids.elementAt(o);
                                                    id.printf(Id);
                                                }
                                            }else if(s.equalsIgnoreCase("sex")){
                                                System.out.println("Enter a condition(male/female)");
                                                cond = in.next();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.sex && cond.equals("male")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else if ((!doc.sex) && cond.equals("female")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else {
                                                        doc.printf(table2);
                                                    }
                                                }
                                            }else if (s.equalsIgnoreCase("salary")){
                                                System.out.println("Enter a condition(=/!=/>/<)");
                                                ch = in.next();
                                                intcond = in.nextInt();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if ((doc.salary==intcond) && ch.equals("=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else if ((doc.salary!=intcond) && ch.equals("!=")) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    }else if ((doc.salary>intcond)&&ch.equals(">")){
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    }else if ((doc.salary<intcond)&&ch.equals("<")){
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;
                                                    } else {
                                                        doc.printf(table2);
                                                    }
                                                }
                                            }else if (s.equalsIgnoreCase("id")){
                                                System.out.println("Enter a condition(Id number)");
                                                intcond = in.nextInt();
                                                Doc doc;
                                                for (int k = 0; k < doctors.size(); k++) {
                                                    doc = (Doc) doctors.elementAt(k);
                                                    if (doc.id==intcond) {
                                                        ID id;
                                                        for (int p = 0; p < ids.size(); p++) {
                                                            id = (ID) ids.elementAt(p);
                                                            if (doc.id == id.doc) {
                                                                Patient patient;
                                                                int o = 0;
                                                                patient = (Patient) patients.elementAt(o);
                                                                while ((id.patient != patient.id)) {
                                                                    patient.printf(table1);
                                                                    o++;
                                                                    patient = (Patient) patients.elementAt(o);
                                                                }
                                                                doc.print();
                                                                patient.println();
                                                                patients.removeElementAt(o);
                                                                ids.removeElementAt(p);
                                                                p--;
                                                            } else {
                                                                id.printf(Id);
                                                            }
                                                        }
                                                        doctors.removeElementAt(k);
                                                        k--;

                                                    } else {
                                                        doc.printf(table2);
                                                    }
                                                }
                                            }else {
                                                System.out.println("Incorrect query");
                                                for (int o = 0; o < patients.size(); o++) {
                                                    Patient patient = (Patient) patients.elementAt(o);
                                                    patient.printf(table1);
                                                }
                                                for (int o = 0; o < doctors.size(); o++) {
                                                    Doc doc = (Doc) doctors.elementAt(o);
                                                    doc.printf(table2);
                                                }
                                                for (int o = 0; o < ids.size(); o++) {
                                                    ID id = (ID) ids.elementAt(o);
                                                    id.printf(Id);
                                                }
                                            }

                                        } else {
                                            System.out.println("Incorrect query");
                                        }

                                    } else {
                                        System.out.println("Incorrect query");
                                    }
                                }
                            } catch (FileNotFoundException e3) {
                                System.out.println("Can't find ids");
                            }
                        } catch (FileNotFoundException e2) {
                            System.out.println("Table " + table2.getName() + " does not exist");
                        }

                    } catch (FileNotFoundException e1) {
                        System.out.println("Table " + table1.getName() + " does not exist");
                    }
                }
                s = in.next();
            }
            if (s.equalsIgnoreCase("delete")) {             //Deleting a database or a table
                System.out.println("Enter a type of deleting object(database/table)");
                s = in.next();
                if (s.equalsIgnoreCase("database")) {           //Deleting a database
                    System.out.println("Enter the name of the database");
                    s = in.next();
                    File database = new File("C:\\DATABASES\\" + s);
                    if (!database.exists()) {
                        System.out.println("Database doesn't exists");
                    } else {
                        for (File file1 : new File("C:\\DATABASES\\" + s).listFiles())
                            if (file1.isFile()) file1.delete();
                        database.delete();
                        System.out.println("Database " + s + " deleted successfully");
                    }
                } else if (s.equalsIgnoreCase("table")) {      //Deleting only a table
                    System.out.println("Enter the name of the database, where you want to delete a table");
                    s = in.next();
                    System.out.println("Enter the name of the table");
                    file = in.next();
                    File table = new File("C:\\DATABASES\\" + s + File.separator + file + ".txt");
                    if (!table.exists()) {
                        System.out.println("Table doesn't exist");
                    } else {
                        try {
                            table.delete();
                            System.out.println("Table " + file + " deleted successfully");
                        } catch (SecurityException e) {
                            System.out.println("Unexpected error, please enter 'quit'");
                        }
                    }
                }
            }
            patients.removeAllElements();
            doctors.removeAllElements();
            ids.removeAllElements();
            s = in.next();
            if (s.equalsIgnoreCase("quit")) {
                System.out.println("I was pleased to work with you, goodbye!");
            }
        }
    }

}