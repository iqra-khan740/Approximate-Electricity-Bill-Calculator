import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LightGui extends JFrame implements ActionListener {
    private Main main;
    JButton button,button2;
    JLabel label1, label2, label3, labelImage;
    JTextField textField1, textField2;

    public LightGui (Main main) {
        this.main = main;
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(4, 1));
        JPanel bottomPanel = new JPanel(new FlowLayout());

        label1 = new JLabel("Amount of Hours Light is used: ");
        label2 = new JLabel("Amount of Watts Your Light is: ");
        label3 = new JLabel(" ");

        textField1 = new JTextField(10);
        textField2 = new JTextField(10);

        button = new JButton("Calculate");
        button2 = new JButton("Choose Another Appliance");
        button2.addActionListener(this);
        button.addActionListener(this);

        try {
            ImageIcon icon = new ImageIcon("D:\\Java Programs\\ELECTRICITY BILL CALCULATOR\\src\\pngwing.com(3).png"); // Replace with your image file path
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            labelImage = new JLabel(scaledIcon);
            bottomPanel.add(labelImage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        topPanel.add(label1);
        topPanel.add(textField1);
        topPanel.add(label2);
        topPanel.add(textField2);
        topPanel.add(button);
        topPanel.add(button2);
        topPanel.add(label3);

        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setTitle("Light");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                String Hours = textField1.getText();
                String Watts = textField2.getText();

                double hours = Double.parseDouble(Hours);
                double watts = Double.parseDouble(Watts);
                double kwh;
                double unitprice=0;
                double bill;
                if (hours >= 1 && hours <= 24) {
                    kwh = ((watts * hours) * 30) / 1000;

                    if (kwh >= 1 && kwh <= 50) {
                        unitprice = 4.81;
                    } else if (kwh > 50 && kwh <= 100) {
                        unitprice = 7.87;
                    } else if (kwh > 100 && kwh <= 200) {
                        unitprice = 10.54;
                    } else if (kwh > 200 && kwh <= 300) {
                        unitprice = 12.89;
                    } else if (kwh > 300 && kwh <= 700) {
                        unitprice = 21.88;
                    } else if (kwh > 700) {
                        unitprice = 24.93;
                    }
                    bill = kwh * unitprice;
                    label3.setText("Your Approximate Monthly Electricity Bill will be: " + bill);
                }
                else
                {
                    label3.setText("Please Enter Valid amount of Hours ");
                }
            }
            catch (Exception exception)
            {
                label3.setText("Invalid Input");
            }
        }
        else if(e.getSource() == button2)
        {
            dispose();
            main.showApplianceSelection();
        }
    }
}