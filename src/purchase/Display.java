
package purchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author zubxx
 */
public class Display implements ActionListener {
   
    private static JLabel prodName, prodPrice;
    
    private static JLabel price = new JLabel();
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();
    
    private static JButton startButton = new JButton("Start Purchasing Session");
    private static JButton endSessionButton = new JButton("End Session");
    private static JButton cancelSessionButton = new JButton("Cancel Session");

    private static int p = 20;
    private static double totalPrice = 0;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    private static Keyboard kybd = new Keyboard();
    private static CashRegister cashregister = new CashRegister();
        
    public static void main(String[] args) {
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);

        startButton.setBounds(10, 40, 200, 25);
        startButton.addActionListener(new Display());
        panel.add(startButton);
           
        frame.setVisible(true);  
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            panel.removeAll();
            frame.setSize(1000, 500); //re-render

            JLabel column1;
            column1 = new JLabel("Product Name");
            column1.setBounds(400, 15, 200, 25);
            panel.add(column1);
            
            JLabel column2;
            column2 = new JLabel("Product Price");
            column2.setBounds(550, 15, 200, 25);
            panel.add(column2);

            kybd.barcode.setBounds(10, 20, 130, 25);          
            panel.add(kybd.barcode);
     
            kybd.barcodeInput.setBounds(80, 20, 165, 25);
            panel.add(kybd.barcodeInput);
       
            kybd.enterButton.setBounds(10, 80, 80, 25);
            kybd.enterButton.addActionListener(new Display());
            panel.add(kybd.enterButton);

            kybd.checkoutButton.setBounds(100, 80, 100, 25);
            kybd.checkoutButton.addActionListener(new Display());
            panel.add(kybd.checkoutButton);     
            
            cancelSessionButton.setBounds(10, 200, 160, 25);
            cancelSessionButton.addActionListener(new Display());
            panel.add(cancelSessionButton);    
        }
        
        else if (e.getSource() == cancelSessionButton) {
            p = 20;
            panel.removeAll();
            totalPrice = 0;      
            frame.setSize(1001, 500);
            panel.add(startButton);
        }

        else if (e.getSource() == kybd.enterButton) {                           
            prodPrice = new JLabel();
            prodName = new JLabel();

            kybd.barcodeError.setBounds(10, 100, 250, 25);
            panel.add(kybd.barcodeError);

            frame.setSize(1002, 500); //re-render

            switch (kybd.barcodeInput.getText()) {
                case "123" -> {
                    kybd.barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Apples");
                    panel.add(prodName);
                    prodPrice.setText("4.47");
                    panel.add(prodPrice);                     
                }
                case "456" -> { 
                    kybd.barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Banana");
                    panel.add(prodName);
                    prodPrice.setText("0.40");
                    panel.add(prodPrice);
                }
                case "789" -> { 
                    kybd.barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Raspberries");
                    panel.add(prodName);
                    prodPrice.setText("2.97");
                    panel.add(prodPrice);
                }
                case "012" -> {
                    kybd.barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Blueberries");
                    panel.add(prodName);
                    prodPrice.setText("5.97");
                    panel.add(prodPrice);
                }                   
                default -> {
                    kybd.barcodeError.setText("Unknown Product");
                    frame.setSize(1002, 500); //re-render
                    System.out.println("Chicken");
                }
            }

            kybd.barcodeInput.setText("");
           
            if (!prodPrice.getText().equals("")) {
                totalPrice += Double.parseDouble(prodPrice.getText());
            }

           frame.setSize(1003, 500); //re-render  
        }
        
        else if (e.getSource() == kybd.checkoutButton) {   
            frame.setSize(1004, 500); //re-render

            panel.remove(kybd.enterButton);
            panel.remove(kybd.checkoutButton);
            panel.remove(kybd.barcodeInput);
            panel.remove(kybd.barcode);
            panel.remove(kybd.barcodeError);
       
            price.setBounds(10, 20, 130, 25);
            price.setText("Total Price: " + decfor.format(totalPrice));
            panel.add(price);
                     
            cashregister.paymentMethod.setBounds(10, 40, 300, 25);
            panel.add(cashregister.paymentMethod);

            cashregister.paymentMethodInput.setBounds(10, 70, 165, 25);
            panel.add(cashregister.paymentMethodInput);
       
            cashregister.authorizeButton.setBounds(10, 130, 100, 25);
            cashregister.authorizeButton.addActionListener(new Display());
            panel.add(cashregister.authorizeButton);        
        }
        
        
        else if (e.getSource() == cashregister.authorizeButton) {
            
            frame.setSize(1005, 500); //re-render
            cashregister.errorMessage.setBounds(10, 100, 250, 25);
            cashregister.errorMessage.setText("Please enter correct payment method");
                
            if (cashregister.paymentMethodInput.getText().equals("Cash") || 
                    cashregister.paymentMethodInput.getText().equals("Debit") || 
                            cashregister.paymentMethodInput.getText().equals("Credit"))
            {                
                panel.remove(cashregister.authorizeButton);
                panel.remove(cashregister.paymentMethodInput);
                panel.remove(cashregister.paymentMethod);
                panel.remove(cancelSessionButton);
                panel.remove(kybd.barcodeError);
                panel.remove(cashregister.errorMessage);
                                
                JLabel reciept;
                reciept = new JLabel("Reciept");
                reciept.setBounds(490, 0, 200, 25);
                panel.add(reciept);
         
                price.setBounds(480, p+50, 130, 25);

                cashregister.authMessage.setBounds(10, 40, 250, 25);
                
                switch (cashregister.paymentMethodInput.getText()) {
                    case "Cash" -> cashregister.authMessage.setText("Payment authorized using cash");
                    case "Debit" -> cashregister.authMessage.setText("Payment authorized using Debit Card");
                    case "Credit" -> cashregister.authMessage.setText("Payment authorized using Credit Card");
                }

                panel.add(cashregister.authMessage);

                endSessionButton.setBounds(10, 70, 120, 25);
                endSessionButton.addActionListener(new Display());
                panel.add(endSessionButton);
            }
            else {
                frame.setSize(1006, 500); //re-render
                panel.add(cashregister.errorMessage);
            }
        }
        
        else if (e.getSource() == endSessionButton) {
            p = 20;
            totalPrice = 0; 
            panel.removeAll();
            frame.setSize(1001, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);

            startButton.setBounds(10, 80, 200, 25);
            startButton.addActionListener(new Display());
            panel.add(startButton);
        }

        frame.setVisible(true);       
    }  
    
    
}
