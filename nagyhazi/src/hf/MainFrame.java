package hf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;


public class MainFrame extends JFrame {
    private final String felhasznalo = "admin";
    private final String jelszo = "asd123";

    public MainFrame() {
        super("Autó kölcsönző nyilvántartás");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(960, 560);
        setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        ImageIcon autoicon = new ImageIcon( "autoicon.png");
        ImageIcon berloicon = new ImageIcon( "person.png");
        ImageIcon berlesekicon = new ImageIcon( "document.png");

        AutoPanel autoPanel = new AutoPanel();
        BerloPanel berlokPanel = new BerloPanel();
        BerlesPanel berlesekPanel = new BerlesPanel(autoPanel.getAutokTable(), berlokPanel.getBerlokTable());
        JPanel uresPanel = new JPanel();
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0,0,960,560);
        tp.addTab("Autók", autoicon, autoPanel);
        tp.addTab("Bérlők", berloicon, berlokPanel);
        tp.addTab("Bérlések", berlesekicon, uresPanel);
        this.add(tp);

        JFrame frame = new JFrame("Hitelesítés");
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        frame.setSize(300, 120);
        frame.setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Felhasználó név:");
        JTextField tfUser = new JTextField(20);
        lblUser.setLabelFor(tfUser);
        JLabel lblPassword = new JLabel("Jelszó:");
        final JPasswordField pfPassword = new JPasswordField(20);
        lblPassword.setLabelFor(pfPassword);
        JButton btnLogin = new JButton("Bejelentkezés");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,2));
        JPanel empty = new JPanel();
        panel.add(lblUser);
        panel.add(tfUser);
        panel.add(lblPassword);
        panel.add(pfPassword);
        panel.add(empty);
        panel.add(btnLogin);
        frame.getContentPane().add(panel);
        AtomicBoolean authenticated = new AtomicBoolean(false);

        tp.addChangeListener(e -> {
            if(tp.getSelectedIndex()==2 && !authenticated.get()) {
                frame.setVisible(true);
                btnLogin.addActionListener( ae -> {
                    String password = new String(pfPassword.getPassword());
                    if(tfUser.getText().matches(felhasznalo) && password.matches(jelszo)) {
                        tp.setComponentAt(2,berlesekPanel);
                        frame.setVisible(false);
                        authenticated.set(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Rossz felhasználó név vagy jelszó","Üzenet", JOptionPane.ERROR_MESSAGE);
                    }
                });;
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("autok.txt"));
                    oos.writeObject(AutokTable.autok);
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("berlok.txt"));
                    oos.writeObject(BerlokTable.berlok);
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("berlesek.txt"));
                    oos.writeObject(BerlesekTable.berlesek);
                    oos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

