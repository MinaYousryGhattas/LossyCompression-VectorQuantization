import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mina_Yousry on 03/12/2017.
 */
public class GUI  extends JPanel implements ActionListener{
    File f = new File("cameraMan.jpg");
    int x = 2;
    int y = 2, level = 2;
    private JTextField NodeXT;
    private JTextField NodeYT;
    private JTextField LabelT;
    private JLabel NodeXL;
    private JLabel NodeYL;
    private JLabel LabelL;
    private JLabel pathl;
    private JTextField patht;
    private JButton change;
    private JButton select;
    JButton CD;

    GUI()
    {
        pathl = new JLabel("Path :");
        patht = new JTextField(f.getPath());
        change = new JButton("Change Path");
        NodeXL = new JLabel("x : ");
        NodeYL = new JLabel("y : ");
        LabelL = new JLabel("Level : ");
        NodeXT = new JTextField();
        NodeYT = new JTextField();
        LabelT = new JTextField();
        select = new JButton("Submit");
        CD = new JButton("Compression and decompression");
        setPreferredSize(new Dimension(250,250));
        setLayout(null);
        NodeXL.setBounds(10,10,100,25);
        NodeXT.setBounds(120,10,100,25);
        NodeYL.setBounds(10,45,100,25);
        NodeYT.setBounds(120,45,100,25);
        LabelL.setBounds(10,80,100,25);
        LabelT.setBounds(120,80,100,25);

        select.setBounds(120,115,100,25);
        CD.setBounds(120,150,100,25);

        pathl.setBounds(10,185,100,25);
        patht.setBounds(120,185,100,25);
        change.setBounds(120,220,100,25);
        add(NodeXL);
        add(NodeXT);
        add(NodeYL);
        add(NodeYT);
        add(LabelL);
        add(LabelT);
        add(select);
        add(CD);
        add(pathl);
        add(patht);
        add(change);

        select.addActionListener(this);
        CD.addActionListener(this);
        change.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == select)
        {
            x = Integer.parseInt(NodeXT.getText());
            y = Integer.parseInt(NodeYT.getText());
            level = Integer.parseInt(LabelT.getText());
        }
        else if(e.getSource() == CD)
        {

            try {
                Controller.Compress(x,y,level ,f.getPath());
                Controller.Decompress(x,y,"outimage.jpg");
                System.out.println("Operation is completed");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        else if (e.getSource() == change)
        {
            f = new File(patht.getText());
            patht.setText(f.getPath());
        }
    }
}
