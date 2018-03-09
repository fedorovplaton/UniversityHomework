/**
 * Created by Тимур on 08.03.2018.
 */

import java.util.*;

class Doc{
    private String name;
    private String date;
    private boolean sex;
    private int salary;

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
        this.weight=weight;
        this.id=id;
    }

    public void read(Scanner in){
        this.name=in.next();
        this.date=in.next();
        String s=in.next();
        if (s.equalsIgnoreCase("male")){
            this.sex=true;
        }
        this.weight=in.nextInt();
        this.id=in.nextInt();
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
        for (int i=0;i<3;i++){

            Pacient My=new Pacient();
            My.read(in);
            pacients.addElement(My);
            My=(Pacient)pacients.elementAt(i);
            My.print();
        }
    }

}
