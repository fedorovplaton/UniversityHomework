/**
 * MyDBMS has been created by moldybizkit on 09.03.2018.
 */

import java.util.*;

class Doc{
    private String name;
    private String surname;
    private String date;
    private boolean sex;
    private int salary;
    public int id;

    public Doc(){
    }

    public Doc(String name,
               String surname,
               String date,
               boolean sex,
               int salary){
        this.name=name;
        this.surname=surname;
        this.date=date;
        this.sex=sex;
        this.salary=salary;
    }
    public void read(Scanner in){
        this.name=in.next();
        this.surname=in.next();
        this.date=in.next();
        if (in.next().equalsIgnoreCase("male")){
            this.sex=true;
        }
        this.salary=in.nextInt();
    }

    public void print(){
        if (this.sex){
            System.out.println(name+" "+surname+" "+date+" male "+" "+salary+" "+id);
        } else{
            System.out.println(name+" "+surname+" "+date+" female "+" "+salary+" "+id);
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

    public Pacient(){
        this.doc_id=-1;
    }

    public Pacient(String name,
                   String surname,
                   String date,
                   boolean sex,
                   int weight){
        this.name=name;
        this.surname=surname;
        this.date=date;
        this.sex=sex;
        this.weight=weight;;
    }


    public void read(Scanner in){
        this.name=in.next();
        this.surname=in.next();
        this.date=in.next();
        if (in.next().equalsIgnoreCase("male")){
            this.sex=true;
        }
        this.weight=in.nextInt();
    }

    public void print(){
        if (this.sex){
            System.out.println(name+" "+surname+" "+date+" male "+" "+weight+" "+id+" "+doc_id);
        } else{
            System.out.println(name+" "+surname+" "+date+" female "+" "+weight+" "+id+" "+doc_id);
        }
    }
}

class ID{
    private int doc;
    private int pacient;

    public ID(){

    }

    public ID(int i,int j){
        this.doc=i;
        this.pacient=j;
    }

}


public class MyDBMS {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vector pacients=new Vector();
        Vector doctors=new Vector();
        Vector ids=new Vector();
        String s=in.next();
        int i=0;
        int j=0;
        while (!s.equalsIgnoreCase("quit")){
            if (s.equalsIgnoreCase("create")){
                s=in.next();
                if (s.equalsIgnoreCase("table")) {
                }
            }
            if (s.equalsIgnoreCase("add")){
                s=in.next();
                if (s.equalsIgnoreCase("patient")){
                    Pacient pacient=new Pacient();
                    pacient.read(in);
                    pacient.doc_id=in.nextInt();
                    pacient.id=i;
                    ID id=new ID(pacient.doc_id,pacient.id);
                    ids.addElement(id);
                    pacients.addElement(pacient);
                    pacient=(Pacient)pacients.elementAt(i);
                    pacient.print();
                    i++;
                } else if (s.equalsIgnoreCase("doctor")){
                    Doc doc=new Doc();
                    doc.read(in);
                    doc.id=j;
                    doctors.addElement(doc);
                    doc=(Doc)doctors.elementAt(j);
                    doc.print();
                    j++;
                } else {
                    Pacient pacient=new Pacient();
                    pacient.read(in);
                    pacient.doc_id=j;
                    pacient.id=i;
                    ID id=new ID(pacient.doc_id,pacient.id);
                    pacients.addElement(pacient);
                    pacient=(Pacient)pacients.elementAt(i);
                    pacient.print();
                    i++;
                    Doc doc=new Doc();
                    doc.read(in);
                    doc.id=j;
                    doctors.addElement(doc);
                    doc=(Doc)doctors.elementAt(j);
                    doc.print();
                    j++;
                }

            }
            s=in.next();
        }
    }

}