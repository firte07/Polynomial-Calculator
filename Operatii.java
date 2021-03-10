import static java.util.Collections.sort;
public class Operatii {
    private Polinom Rest;

    public Polinom reduce(Polinom Z){
        sort(Z.getA());
        Polinom ZR = new Polinom("");
        int i=1, j=0;
        Monom m= Z.getMonom(0);
        ZR.add(m);
        while(i<Z.getNrMon()){
            if(Z.getMonom(i).getPutere()==ZR.getMonom(j).getPutere()){
                Monom m2= new Monom(Z.getMonom(i).getCoef()+ZR.getMonom(j).getCoef(),Z.getMonom(i).getPutere());
                ZR.removeM(ZR.getNrMon() - 1);
                ZR.add(m2);
                i++;
            }
            else{
                ZR.add(Z.getMonom(i));
                j++;
                i++;
            }
        }
        return ZR;
    }

    public Polinom aduna(Polinom R, Polinom Q) {
        Polinom P = new Polinom("0");
        int nrMonR = R.getNrMon(), nrMonQ = Q.getNrMon();
        int i = 0, j = 0;
        while (nrMonQ > j && nrMonR > i) {
            int putereR = R.getMonom(i).getPutere();
            int putereQ = Q.getMonom(j).getPutere();
            if (putereR == putereQ) {
                Monom m0 = new Monom(R.getMonom(i).getCoef() + Q.getMonom(j).getCoef(), putereQ);
                P.add(m0);
                i += 1;
                j += 1;
            } else if (putereQ > putereR) {
                Monom m0 = new Monom(Q.getMonom(j).getCoef(), putereQ);
                P.add(m0);
                j += 1;
            } else {
                Monom m0 = new Monom(R.getMonom(i).getCoef(), putereR);
                P.add(m0);
                i += 1; }
        }while (i < nrMonR) {
            Monom m0 = new Monom(R.getMonom(i).getCoef(), R.getMonom(i).getPutere());
            P.add(m0);
            i += 1;
        } while (j < nrMonQ) {
            Monom m0 = new Monom(Q.getMonom(j).getCoef(),Q.getMonom(j).getPutere());
            P.add(m0);
            j += 1; }
        return P;
    }

    public Polinom scade(Polinom R, Polinom Q) {
        Polinom P = new Polinom("0");
        int nrMonR = R.getNrMon(), nrMonQ = Q.getNrMon();
        int i = 0, j = 0;
        while (nrMonQ > j && nrMonR > i) {
            int putereR = R.getMonom(i).getPutere(), putereQ = Q.getMonom(j).getPutere();
            if (putereR == putereQ) {
                Monom m0 = new Monom(R.getMonom(i).getCoef() - Q.getMonom(j).getCoef(), putereQ);
                P.add(m0);
                i += 1;
                j += 1;
            } else if (putereQ > putereR) {
                double coefP = -Q.getMonom(j).getCoef();
                Monom m0 = new Monom(coefP, putereQ);
                P.add(m0);
                j += 1;
            } else { double coefP = R.getMonom(i).getCoef();
                Monom m0 = new Monom(coefP, putereR);
                P.add(m0);
                i += 1; }
        } while (i < nrMonR) {
            Monom m0 = new Monom(R.getMonom(i).getCoef(), R.getMonom(i).getPutere());
            P.add(m0);
            i += 1;
        } while (j < nrMonQ) {
            Monom m0 = new Monom(-Q.getMonom(j).getCoef(), Q.getMonom(j).getPutere());
            P.add(m0);
            j += 1; }
        return P;
    }

    public Polinom inmulteste(Polinom R, Polinom Q) {
        Polinom P = new Polinom("");
        int nrMonR, nrMonQ;
        nrMonR = R.getNrMon();
        nrMonQ = Q.getNrMon();
        int i = 0;
        int j = 0;
        while (nrMonQ > j) {
            int putereQ = Q.getMonom(j).getPutere();
            double coefQ = Q.getMonom(j).getCoef();
            while (nrMonR > i) {
                int putereR = R.getMonom(i).getPutere();
                double coefR = R.getMonom(i).getCoef();
                Monom m0 = new Monom(coefQ * coefR, putereQ + putereR);
                P.add(m0);
                i += 1;
            }
            j += 1;
            i = 0;
        }
        return P;
    }

    public Polinom deriveaza(Polinom R, Polinom Q) {
        Polinom P = new Polinom("0");
        int nrMonR, i = 0;
        nrMonR = R.getNrMon();
        while (i < nrMonR) {
            int putereR = R.getMonom(i).getPutere();
            double coefR = R.getMonom(i).getCoef();
            if (putereR == 0) {
                System.out.print("");   //in caz ca e puterea 0
            } else {
                Monom m = new Monom(coefR * putereR, putereR - 1);
                P.add(m);
            }
            i++;
        }
        return P;
    }

    public Polinom integreaza(Polinom R, Polinom Q) {
        Polinom P = new Polinom("0");
        int nrMonR, i = 0;
        nrMonR = R.getNrMon();
        while (i < nrMonR) {
            int putereR = R.getMonom(i).getPutere();
            double coefR = R.getMonom(i).getCoef();
            Monom m = new Monom(coefR / (putereR + 1), putereR + 1);
            P.add(m);
            i++;
        }
        return P;
    }

    public Polinom imparte(Polinom R, Polinom Q) {
        Polinom P = new Polinom("");
        int  i = 0;
        while (R.getGrad() >= Q.getGrad()) {
            R.removeMonomNull();
            Monom m = new Monom(R.getMonom(0).getCoef() / Q.getMonom(0).getCoef(),
                    R.getMonom(0).getPutere() - Q.getMonom(0).getPutere());
            P.add(m);
            Polinom PA = new Polinom("");   //copia pt inmultit, inmultesc doar monomul curent
            PA.add(m);
            Polinom aux = new Polinom("");
            aux = inmulteste(Q, PA);
            R = this.scade(R, aux);
            i++;
        }
        R.removeMonomNull();
        if(R.getNrMon()!=0){
            int ii=0;
            Rest= new Polinom("");
            while(ii<R.getNrMon()){
                Monom m3= new Monom(R.getMonom(ii).getCoef(),R.getMonom(ii).getPutere());
                Rest.add(m3);
                ii++;
            }
        }
        return P;
    }

    public Polinom getRest() {
        return Rest;
    }
}
