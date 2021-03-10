public class Monom implements Comparable<Monom>{
    private double coef;
    private int putere;

    Monom(double coef, int putere) {
        if(coef==0)
            this.putere = 0;
        else{this.putere = putere;}
        this.coef = coef;
    }

    public double getCoef() {
        return coef;
    }

    public int getPutere() {
        return putere;
    }

    @Override
    public String toString() {
        if(putere==0 && coef==0)
            return "";
        if (putere == 0)
            return coef + "";
        if (putere == 1 && coef == 1 )
            return "x";
        if(putere==1 && coef==-1)
            return "-x";
        if(putere ==1 && coef!=1)
            return coef+"x";
        if (putere > 0 && coef != 1)
            return coef + "x^" + putere;
        return "x^"+putere;
    }

    public int compareTo(Monom m) {
        return m.putere-this.putere;
    }
}
