import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class Main {
    public static ArrayList<JButton> buttony = new ArrayList<>();
    public static void setButtons(JPanel panel){
        new MyButton(panel, "%",0);
        new MyButton(panel, "CE",0);
        new MyButton(panel, "C",0);
        new MyButton(panel, "←",0);
        new MyButton(panel, "1/x",0);
        new MyButton(panel, "x^2",0);
        new MyButton(panel, "√x",0);
        new MyButton(panel, "÷",0);
        new MyButton(panel, "7",1);
        new MyButton(panel, "8",1);
        new MyButton(panel, "9",1);
        new MyButton(panel, "×",0);
        new MyButton(panel, "4",1);
        new MyButton(panel, "5",1);
        new MyButton(panel, "6",1);
        new MyButton(panel, "-",0);
        new MyButton(panel, "1",1);
        new MyButton(panel, "2",1);
        new MyButton(panel, "3",1);
        new MyButton(panel, "+",0);
        new MyButton(panel, "+/-",1);
        new MyButton(panel, "0",1);
        new MyButton(panel, ".",1);
        new MyButton(panel, "=",0);
    }
    public static class MyProjectSettings extends JFrame {
        MyProjectSettings(){
            this.setTitle("Kalkulator");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setSize(368,600);
            this.setVisible(true);
            ImageIcon myProjectIcon = new ImageIcon("kalkulatorICON.png");
            this.setIconImage(myProjectIcon.getImage());
            this.getContentPane().setBackground(new Color(20, 168, 224));
        }
    }
    public static class MyButton extends JButton {
        MyButton(JPanel okno, String textValue, int r){
            JButton b = new JButton(textValue);
            b.setFocusable(false);
            b.setFont(new Font("Arial", Font.PLAIN, 35));
            b.setPreferredSize(new Dimension(83, 75));
            if(r==0) b.setBackground(new Color(20, 168, 224));
            else b.setBackground(new Color(33, 126, 255));
            b.setForeground(Color.WHITE);
            b.setBorder(new LineBorder(Color.WHITE, 1, false));
            okno.add(b);
            buttony.add(b);
        }
    }
    public static String wynik = "0";
    public static String wynik1 = "";
    public static String lastCharacter = "";
    public static String wynikDzialaniaLB1 = "";
    public static float wynikDzialaniaLB = 0.0f;
    public static void licz(JButton b, JLabel lb, JLabel lb1) {
        if(wynik.equals("0")) wynik = "";
        if(wynik.length()<=15 && (!b.getText().equals("%") && !b.getText().equals("1/x") && !b.getText().equals("x^2")) && !b.getText().equals("√x") && !b.getText().equals("÷") && !b.getText().equals("×") && !b.getText().equals("-") && !b.getText().equals("+") && !b.getText().equals("=") && !b.getText().equals("CE") && !b.getText().equals("C") && !b.getText().equals("←") && !b.getText().equals("+/-")) {
            if((b.getText().equals(".") && lb.getText().charAt(lb.getText().length() - 1) == '.') || b.getText().equals(".") && lb.getText().contains(".")) return;
            else if(b.getText().equals(".") && lb.getText().charAt(lb.getText().length() - 1) == '0') { wynik="0"; lb.setText(wynik);}
            wynik += b.getText();
            lb.setText(wynik);
        }
        else {
            if(wynik.equals("")) wynik = "0";
            if(!lb1.getText().equals(""))
                lastCharacter = lb1.getText().substring(lb1.getText().length()-1);
            else lastCharacter = wynik;
            if(b.getText().equals("CE")) { wynik="0"; lb.setText(wynik); if(lb1.getText().equals("")) return; if(lb1.getText().charAt(lb1.getText().length() - 1) == '=') { wynik1 = ""; lb1.setText(wynik1); } }
            else if(b.getText().equals("←")) { wynik = wynik.substring(0, wynik.length()-1); if(wynik.isEmpty()) wynik="0"; lb.setText(wynik); if(lb.getText().equals("⁃")) { wynik="0"; lb.setText(wynik); } }
            else if(b.getText().equals("C")) { wynik="0"; lb.setText(wynik); wynik1=""; lb1.setText(wynik1); lastCharacter=""; }
            else if(b.getText().equals("%")) {
                if(lb1.getText().isEmpty()) { wynik1 = "0"; lb1.setText(wynik1); wynik="0"; return; }
                String[] liczbaPrc = new String[1];
                if(lb1.getText().contains("+")) { liczbaPrc[0] = lb1.getText().substring(0, lb1.getText().indexOf("+")-1); }
                else if(lb1.getText().contains("-")) { liczbaPrc[0] = lb1.getText().substring(0, lb1.getText().indexOf("-")-1); }
                else if(lb1.getText().contains("÷")) { liczbaPrc[0] = lb1.getText().substring(0, lb1.getText().indexOf("÷")-1); }
                else if(lb1.getText().contains("×")) { liczbaPrc[0] = lb1.getText().substring(0, lb1.getText().indexOf("×")-1); }
                if(lb1.getText().charAt(lb1.getText().length()-1)=='=') {
                    wynik1 = Float.toString(Float.parseFloat(lb.getText())*Float.parseFloat(lb.getText())/100);
                    lb1.setText(wynik1);
                    wynik = "0";
                    lb.setText(wynik1);
                }
                else if(!liczbaPrc[0].isEmpty() && lb1.getText().length()>liczbaPrc[0].length()) {
                    if(liczbaPrc[0].charAt(0)=='⁃') liczbaPrc[0] = "-" + liczbaPrc[0].substring(1, liczbaPrc[0].length());
                    if(lb.getText().charAt(0)=='⁃') lb.setText("-" + lb.getText().substring(1, lb.getText().length()));
                    wynik1 += " " + Float.toString(Float.parseFloat(liczbaPrc[0])*Float.parseFloat(lb.getText())/100);
                    lb1.setText(wynik1);
                    lb.setText(Float.toString(Float.parseFloat(liczbaPrc[0])*Float.parseFloat(lb.getText())/100));
                    wynik="0";
                }
            }
            else if(b.getText().equals("1/x")) {
                if(lb.getText().equals("0")) { wynik="0"; lb.setText(wynik); return; }
                else {
                    if(lb.getText().charAt(0) == '⁃') lb.setText("-" + lb.getText().substring(1,lb.getText().length()));
                    lb.setText(" " + Float.toString(1 / Float.parseFloat(lb.getText())));
                    if(lb1.getText().contains("+") || lb1.getText().contains("-") || lb1.getText().contains("÷") || lb1.getText().contains("×")) { }
                    else wynik1 = lb.getText();
                    lb1.setText(wynik1);
                    wynik="0";
                }
            }
            else if(b.getText().equals("√x")) {
                if(lb.getText().equals("0")) { wynik="0"; lb.setText(wynik); return; }
                else {
                    if(lb.getText().charAt(0) == '⁃') return;
                    lb.setText(" " + Float.toString((float)Math.sqrt(Float.parseFloat(lb.getText()))));
                    if(lb1.getText().contains("+") || lb1.getText().contains("-") || lb1.getText().contains("÷") || lb1.getText().contains("×")){ }
                    else wynik1 = lb.getText();
                    lb1.setText(wynik1);
                    wynik="0";
                }
            }
            else if(b.getText().equals("x^2")) {
                if(lb.getText().equals("0")) { wynik="0"; lb.setText(wynik); return; }
                else {
                    if(lb.getText().charAt(0) == '⁃') lb.setText("-" + lb.getText().substring(1,lb.getText().length()));
                    lb.setText(" " + Float.toString(Float.parseFloat(lb.getText())*Float.parseFloat(lb.getText())));
                    if(lb1.getText().contains("+") || lb1.getText().contains("-") || lb1.getText().contains("÷") || lb1.getText().contains("×")) { }
                    else wynik1 = lb.getText();
                    lb1.setText(wynik1);
                    wynik="0";
                }
            }
            else if(b.getText().equals("+/-")) {
                if(wynik.charAt(0) == '⁃' && !wynik.equals("0")) {
                    wynik = wynik.substring(1, wynik.length());
                    lb.setText(wynik);
                } else if(!wynik.equals("0")) wynik = "⁃" + wynik; lb.setText(wynik);
            }
            else if(b.getText().equals("=")){
                if(wynik1.isEmpty()) { lb1.setText(lb.getText() + " ="); wynik="0"; return;}
                String znak1 = "";
                String[] liczba = new String[1];
                if(lb1.getText().contains("+")) { znak1 = lb1.getText().substring(lb1.getText().indexOf("+"), lb1.getText().indexOf("+")+1); liczba[0] = lb1.getText().substring(0, lb1.getText().indexOf("+")-1); }
                else if(lb1.getText().contains("-")) { znak1 = lb1.getText().substring(lb1.getText().indexOf("-"), lb1.getText().indexOf("-")+1); liczba[0] = lb1.getText().substring(0, lb1.getText().indexOf("-")-1); }
                else if(lb1.getText().contains("÷")) { znak1 = lb1.getText().substring(lb1.getText().indexOf("÷"), lb1.getText().indexOf("÷")+1); liczba[0] = lb1.getText().substring(0, lb1.getText().indexOf("÷")-1); }
                else if(lb1.getText().contains("×")) { znak1 = lb1.getText().substring(lb1.getText().indexOf("×"), lb1.getText().indexOf("×")+1); liczba[0] = lb1.getText().substring(0, lb1.getText().indexOf("×")-1); }
                else liczba[0] = "";
                String drugaLiczba = lb.getText();
                wynikDzialaniaLB1 = liczba[0] + " " + znak1 + " " + drugaLiczba + " =";
                lb1.setText(wynikDzialaniaLB1);
                if(!liczba[0].isEmpty() && liczba[0].charAt(0) == '⁃') liczba[0] = "-" + liczba[0].substring(1,liczba[0].length());
                if(drugaLiczba.charAt(0) == '⁃') drugaLiczba = "-" + drugaLiczba.substring(1,drugaLiczba.length());
                if(znak1.equals("+")) wynikDzialaniaLB = Float.parseFloat(liczba[0]) + Float.parseFloat(drugaLiczba);
                else if(znak1.equals("-")) wynikDzialaniaLB = Float.parseFloat(liczba[0]) - Float.parseFloat(drugaLiczba);
                else if(znak1.equals("÷")) {
                    if(!drugaLiczba.equals("0")) wynikDzialaniaLB = Float.parseFloat(liczba[0]) / Float.parseFloat(drugaLiczba);
                    else { wynik="0"; lb.setText(wynik); wynik1= liczba[0] + " " + znak1; lb1.setText(wynik1); return; }
                }
                else if(znak1.equals("×")) wynikDzialaniaLB = Float.parseFloat(liczba[0]) * Float.parseFloat(drugaLiczba);
                else wynikDzialaniaLB = Float.parseFloat(drugaLiczba);
                wynik = Float.toString(wynikDzialaniaLB);
                if(wynik.charAt(0) == '-') { wynik = "⁃" + wynik.substring(1,wynik.length());}
                lb.setText(wynik);
            }
            else if(!lastCharacter.equals("+") && !lastCharacter.equals("-") && !lastCharacter.equals("÷") && !lastCharacter.equals("×") && !lastCharacter.equals("=")){
                if(lb1.getText().isEmpty())
                    wynik1 += wynik + " " + b.getText();
                else wynik1 += " " + b.getText();
                wynik = "0";
                lb1.setText(wynik1);
            }
            else if(lb1.getText().charAt(lb1.getText().length() - 1) == '=' && b.getText().equals("+")){ lb1.setText(lb.getText() + " +"); wynik1 = lb1.getText(); wynik="0"; }
            else if(lb1.getText().charAt(lb1.getText().length() - 1) == '=' && b.getText().equals("-")){ lb1.setText(lb.getText() + " -"); wynik1 = lb1.getText(); wynik="0"; }
            else if(lb1.getText().charAt(lb1.getText().length() - 1) == '=' && b.getText().equals("÷")){ lb1.setText(lb.getText() + " ÷"); wynik1 = lb1.getText(); wynik="0"; }
            else if(lb1.getText().charAt(lb1.getText().length() - 1) == '=' && b.getText().equals("×")){ lb1.setText(lb.getText() + " ×"); wynik1 = lb1.getText(); wynik="0"; }
        }
    }
    public static void main(String[] args) {
        MyProjectSettings okienko = new MyProjectSettings();
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(33, 126, 255));
                g.fillRect(0,105,okienko.getWidth(),105);
                g.fillRect(0,0,okienko.getWidth(),5);
                g.fillRect(0,0,5,110);
                g.fillRect(347,0,okienko.getWidth(),110);
            }
        };
        panel.setSize(okienko.getWidth(),110);
        panel.setBackground(new Color(20, 168, 224));
        panel.setLayout( new GridLayout(2, 2) );
        okienko.add(panel);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(200,200);
        buttonsPanel.setBackground(new Color(1,1,1,0));
        buttonsPanel.setLayout( new GridLayout(6, 4) );
        okienko.add(buttonsPanel, BorderLayout.SOUTH);
        setButtons(buttonsPanel);
        JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Sans-serif", Font.BOLD, 16));
        panel.add(label);
        JLabel label1 = new JLabel("0");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Sans-serif", Font.BOLD, 36));
        panel.setBorder(new EmptyBorder(0, 0, 0, 8));
        panel.add(label1);
        for(JButton b : buttony){
            b.addActionListener(e -> { licz(b, label1, label); });
        }
        okienko.setVisible(true);
    }
}