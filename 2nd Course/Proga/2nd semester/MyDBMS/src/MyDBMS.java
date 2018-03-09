/**
 * MyDBMS has been created by moldybizkit on 09.03.2018.
 */

import java.util.*;

class Doc{
    private String name;
    private String date;
    private boolean sex;
    private int salary;
    private int id;

    public Doc(){
    }

    public Doc(String name,
               String date,
               boolean sex,
               int salary){
        this.name=name;
        this.date=date;
        this.sex=sex;
        this.salary=salary;
    }

    public void set_id(int i){
        this.id=i;
    }

    public void read(Scanner in){
        this.name=in.next();
        this.date=in.next();
        if (in.next().equalsIgnoreCase("male")){
            this.sex=true;
        }
        this.salary=in.nextInt();
    }

    public void print(){
        if (this.sex){
            System.out.println(name+" "+date+" male "+" "+salary+" "+id);
        } else{
            System.out.println(name+" "+date+" female "+" "+salary+" "+id);
        }
    }

}

class Pacient {
    private String name;
    private String date;
    private boolean sex;    //in case male sex=true, else sex=false ^^
    private int weight;
    private int id;

    public Pacient(){

    }

    public Pacient(String name,
                   String date,
                   boolean sex,
                   int weight,
                   int id){
        this.name=name;
        this.date=date;
        this.sex=sex;
        this.weight=weight;;
    }

    public void set_id(int i){
        this.id=i;
    }

    public void read(Scanner in){
        this.name=in.next();
        this.date=in.next();
        if (in.next().equalsIgnoreCase("male")){
            this.sex=true;
        }
        this.weight=in.nextInt();
    }

    public void print(){
        if (this.sex){
            System.out.println(name+" "+date+" male "+" "+weight+" "+id);
        } else{
            System.out.println(name+" "+date+" female "+" "+weight+" "+id);
        }
    }
}


public class MyDBMS {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Vector pacients=new Vector();
        Vector doctors=new Vector();
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
                    pacient.set_id(i);
                    pacients.addElement(pacient);
                    pacient=(Pacient)pacients.elementAt(i);
                    pacient.print();
                    i++;
                } else if (s.equalsIgnoreCase("doctor")){
                    Doc doc=new Doc();
                    doc.read(in);
                    doc.set_id(j);
                    doctors.addElement(doc);
                    doc=(Doc)doctors.elementAt(j);
                    doc.print();
                    j++;
                } else {
                    Pacient pacient=new Pacient();
                    pacient.read(in);
                    pacient.set_id(i);
                    pacients.addElement(pacient);
                    pacient=(Pacient)pacients.elementAt(i);
                    pacient.print();
                    i++;
                    Doc doc=new Doc();
                    doc.read(in);
                    doc.set_id(j);
                    doctors.addElement(doc);
                    doc=(Doc)doctors.elementAt(j);
                    doc.print();
                    j++;
                }

            }
            s=in.next();
        }

        /*for (int i=0;i<3;i++){

            Pacient pacient=new Pacient();
            pacient.read(in);
            pacients.addElement(pacient);
            pacient=(Pacient)pacients.elementAt(i);
            pacient.print();

            Doc doc=new Doc();
            doc.read(in);
            doctors.addElement(doc);
            doc=(Doc)doctors.elementAt(i);
            doc.print();
        }*/
    }

}