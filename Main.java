import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static java.util.Collections.sort;

/**
 * Clasa de UI care contine atat definitia elementelor grafice
 * cat si definitia Listenerilor si tratarea lor independenta in clase anonime
 * echivalent View + controller pentru fiecare element grafic
 */

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JButton button = new JButton("OK");
    private JButton buttonp = new JButton("+");
    private JButton buttonm = new JButton("-");
    private JButton buttoninm = new JButton("*");
    private JButton buttonimp = new JButton("/");
    private JButton buttonder = new JButton("( )'");
    private JButton buttoninter = new JButton("`int`");
    private JTextField textR = new JTextField(20);
    private JTextField textQ = new JTextField(20);
    private JLabel labe1 = new JLabel("Rezultat");
    private JLabel labe2 = new JLabel("");
    private JTextField labelprim = new JTextField(20);
    private JTextField labeldoi = new JTextField(20);
    private JLabel labeR = new JLabel("R");
    private JLabel labeQ = new JLabel("Q");
    private  Operatii op =  new Operatii();

    public Main(String name) {
        super(name);
        c.gridx = 0;
        c.gridy = 0;
        pane.add(labeR, c);
        c.gridx = 1;
        c.gridy = 0;
        pane.add(textR, c);
        c.gridx = 0;
        c.gridy = 1;
        pane.add(labeQ, c);
        c.gridx = 1;
        c.gridy = 1;
        pane.add(textQ, c);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(buttonp, c);
        c.gridx = 1;
        c.gridy = 2;
        pane.add(buttonm,c );

        c.gridx = 2;
        c.gridy = 2;
        pane.add(buttoninm,c );

        c.gridx = 0;
        c.gridy = 3;
        pane.add(buttonimp, c);
        c.gridx = 1;
        c.gridy = 3;
        pane.add(buttonder,c );

        c.gridx = 2;
        c.gridy = 3;
        pane.add(buttoninter,c );

        c.gridx = 1;
        c.gridy = 4;
       pane.add(labelprim, c);
        c.gridx = 0;
        c.gridy = 4;
        pane.add(labe1, c);

        c.gridx = 1;
        c.gridy = 5;
        pane.add(labeldoi, c);

        c.gridx = 0;                            //pt rest
        c.gridy = 5;
        pane.add(labe2, c);

        buttonp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String s = textR.getText();
                String q = textQ.getText();
                Polinom R = new Polinom(s);
                Polinom Q = new Polinom(q);
                R.creaza();
                Q.creaza();
                sort(R.getA());
                sort(Q.getA());
                if (R.getNrMon() != 0 && Q.getNrMon() != 0) {
                    Polinom P = op.aduna(R, Q);
                    P = op.reduce(P);
                    labelprim.setText(P.toString());
                    labeldoi.setText("");
                    labe2.setText("");
                } else if(R.getNrMon() != 0){
                    R=op.reduce(R);
                    labelprim.setText(R.toString());
                }else if(Q.getNrMon() != 0){
                    Q=op.reduce(Q);
                    labelprim.setText(Q.toString());
                } else{labelprim.setText("0"); }
            }
        });

        buttonm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String s = textR.getText();
                Polinom R = new Polinom(s);
                String q = textQ.getText();
                Polinom Q = new Polinom(q);
                R.creaza();
                Q.creaza();
                sort(R.getA());
                sort(Q.getA());
                if (R.getNrMon() != 0 && Q.getNrMon() != 0) {
                    Polinom P = op.scade(R, Q);
                    P = op.reduce(P);
                    labeldoi.setText("");
                    labe2.setText("");
                    labelprim.setText(P.toString());
                }else if(R.getNrMon() != 0){
                    R=op.reduce(R);
                    labelprim.setText(R.toString());
                }else if(Q.getNrMon() != 0){
                    Q=op.scade(R,Q);
                    Q=op.reduce(Q);
                    labelprim.setText(Q.toString()); }
                else {
                    labelprim.setText("0");
                }
            }
        });

        buttoninm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String s = textR.getText();
                Polinom R = new Polinom(s);
                String q = textQ.getText();
                Polinom Q = new Polinom(q);
                R.creaza();
                Q.creaza();
                sort(R.getA());
                sort(Q.getA());
                if (R.getNrMon()!=0 && Q.getNrMon()!=0) {
                    R = op.reduce(R);
                    Q = op.reduce(Q);
                    Polinom P = op.inmulteste(R, Q);
                    P = op.reduce(P);
                    labeldoi.setText("");
                    labe2.setText("");
                    labelprim.setText(P.toString());
                }else{ labelprim.setText("0");}
                }
        });

        buttonder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String s = textR.getText();
                Polinom R = new Polinom(s);
                String q = textQ.getText();
                Polinom Q = new Polinom(q);
                R.creaza();
                Q.creaza();
                if (R.getNrMon()!=0) {
                    Polinom P = op.deriveaza(R, Q);
                    P = op.reduce(P);
                    labe2.setText("");
                    labeldoi.setText("");
                    if (Q.getNrMon() != 0) {
                        labelprim.setText("Invalid! Scrie doar in polinomul P");
                    } else {
                        labelprim.setText(P.toString());
                    }
                }else{labelprim.setText("Invalid!");}
            }
        });
        buttoninter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String s = textR.getText();
                Polinom R = new Polinom(s);
                String q = textQ.getText();
                Polinom Q = new Polinom(q);
                R.creaza();
                Q.creaza();
                if (R.getNrMon() != 0) {
                    Polinom P = op.integreaza(R, Q);
                    P = op.reduce(P);
                    labeldoi.setText("");
                    labe2.setText("");
                    if (Q.getNrMon() != 0) {
                        labelprim.setText("Invalid! Scrie doar in polinomul P");
                    } else {
                        labelprim.setText(P.toString());
                    }
                }else{  labelprim.setText("Invalid!");}
            }
        });

        buttonimp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String s = textR.getText();
                Polinom R = new Polinom(s);
                String q = textQ.getText();
                Polinom Q = new Polinom(q);
                R.creaza();
                Q.creaza();
                if (Q.getNrMon() != 0) {
                    R = op.reduce(R);
                    Q = op.reduce(Q);
                    Polinom P = op.imparte(R, Q);
                    P = op.reduce(P);
                    Polinom Rest = new Polinom("");
                    Rest = op.getRest();
                    labe2.setText("Rest");
                    labelprim.setText(P.toString());
                    if (Rest != null)
                        labeldoi.setText(Rest.toString());
                }else { labelprim.setText("Nu se poate imparti la 0!");}
            }
        });
        this.add(pane);
    }

    public static void main(String args[]) {
        JFrame frame = new Main("Calculator Polinomial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
