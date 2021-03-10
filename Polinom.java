import java.util.ArrayList;
import java.util.List;

public class Polinom {
    private List<Monom> a;         //lista de monoame din polinom
    private List<Monom> aa;      //copie pentru lista a. o folosesc pentru a parcurge for each, din metoda reduce
    private int nrMon;          //numarul de monoame din polinom
    private String[] s;


    public Polinom(String r) {
        a= new ArrayList<Monom>();
        aa= new ArrayList<Monom>();
        r=r.replace("-","+-");
        s = r.split("\\+");
        nrMon=0;
    }

    //aici creez polinomul
    public void creazaMinus(String s1){s1 = s1.replace("-", "");                          //elimin semnul -
        if (s1.length() == 1 || s1.contains("x")==false) {                                                  //daca avem doar coeficient fara x sau daua avem doar x
            CharSequence sr = s1.subSequence(0, 1);                                                         //iau valoarea intregului
            String sr1 = (String) sr;
            if (sr1.compareTo("x") == 0) {
                Monom m1 = new Monom(-1,1);
                this.add(m1);
            } else {  double x = Double.parseDouble(s1);                                                          // aici vad valoarea coeficientului
                Monom m2= new Monom(-x,0);
                this.add(m2);}
        }else {  CharSequence sr = s1.subSequence(0, 1);                       //iau valoarea intregului         // e de forma -coef*x sau -x^putere sau -coef*x^putere
            String sr1 = (String) sr;
            if (sr1.compareTo("x") == 0){                                                                     //e de forma -x^putere
                CharSequence sputere=s1.subSequence(2,s1.length());
                int x= Integer.parseInt((String)sputere);
                Monom m3 = new Monom(-1,x);
                this.add(m3);
            }else if(s1.lastIndexOf("x")!=(s1.length()-1)){                                     //e de forma coef*x^putere
                int pozx = s1.lastIndexOf("x");
                CharSequence coef = s1.subSequence(0,pozx);
                double x= Double.parseDouble((String)coef);
                CharSequence sputere2=s1.subSequence(pozx+2,s1.length());
                int y = Integer.parseInt((String)sputere2);
                Monom m4 = new Monom(-x,y);
                this.add(m4);
            }else{ CharSequence coef= s1.subSequence(0,s1.length()-1);                               //e de forma -coeficient*x
                double coefr = Double.parseDouble((String)coef);
                Monom m6 = new Monom(-coefr, 1);
                this.add(m6);} }
    }

    public void creazaPlus(String s1) {
        if (s1.length() == 1 || s1.contains("x") == false) {                                                  //daca avem doar coeficient fara x sau daua avem doar x
            CharSequence sr = s1.subSequence(0, 1);                                                         //iau valoarea intregului
            String sr1 = (String) sr;
            if (sr1.compareTo("x") == 0) {                                                                  //daca stringul curent e de forma x
                Monom m1 = new Monom(1, 1);
                this.add(m1);
            } else { double x = Double.parseDouble(s1);                                                          // aici vad valoarea coeficientului
                Monom m2 = new Monom(x, 0);
                this.add(m2); }
        } else { CharSequence sr = s1.subSequence(0, 1);       //iau valoarea intregului      // e de forma -coef*x sau -x^putere sau -coef*x^putere
            String sr1 = (String) sr;
            if (sr1.compareTo("x") == 0) {                                                                     //e de forma -x^putere
                CharSequence sputere = s1.subSequence(2, s1.length());
                int x = Integer.parseInt((String) sputere);
                Monom m3 = new Monom(1, x);
                this.add(m3);
            } else if (s1.lastIndexOf("x") != (s1.length() - 1)) {                                     //e de forma coef*x^putere
                int pozx = s1.lastIndexOf("x");
                CharSequence coef = s1.subSequence(0, pozx);
                double x = Double.parseDouble((String) coef);
                CharSequence sputere2 = s1.subSequence(pozx + 2, s1.length());
                int y = Integer.parseInt((String) sputere2);
                Monom m4 = new Monom(x, y);
                this.add(m4);
            } else { CharSequence coef = s1.subSequence(0, s1.length() - 1);                                   //e de forma -coeficient*x
                double coefr = Double.parseDouble((String) coef);
                Monom m6 = new Monom(coefr, 1);
                this.add(m6); }
            }
    }

    //aici creez polinomul folosind s
    public void creaza() {
        for (String s1 : s) {
            if (s1.length() != 0) {                                     //verific daca nu e null (dupa ce fac split)
                if (s1.startsWith("-")) {                       //daca incepe cu minus folosim metoda pentru minus
                    creazaMinus(s1);
                } else {
                    creazaPlus(s1);
                }
            }
        }
    }

    //adaugare de monoame. folosit in metoda creeaza. folosesc copia aa, pt reducere
     public void add(Monom m){
        a.add(m);
        aa.add(m);
        nrMon++;
    }

    public void setA() {
        this.a.clear();
    }

    public List<Monom> getA() {
        return a;
    }

    public int getNrMon() {
        int a=nrMon;
        return a;
    }

    public void removeM(int i){
        a.remove(i);
        nrMon--;
    }

    public Monom getMonom(int poz){
        return a.get(poz);
    }


    public void removeMonomNull(){
        for(Monom m:aa){
            if(m.getPutere()==0 && m.getCoef()==0){
                a.remove(m);
                nrMon--;
        }
        }
    }

    public String[] getSir() {

        return s;
    }

    public int getGrad() {
        int aux=0;
        for (Monom m : a) {
            if(m.getPutere()>aux)
                aux=m.getPutere();
        }
        return aux;
    }

    public String toString(){
        String r="";
        for(Monom m:a){
            if(m.getCoef()>0)
                r=r+"+"+m.toString();
            else r=r+m.toString();
        }
        return r;
    }
}
