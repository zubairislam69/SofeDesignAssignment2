import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author zubxx
 */
 
public class CashRegisterGUI implements ActionListener {
    private static JButton startButton, enterButton, checkoutButton, 
            authorizeButton, endSessionButton, cancelSessionButton ;    
    private static JTextField paymentMethodInput, barcodeInput ;
    private static JLabel prodName, prodPrice, paymentMethod, barcode;
    private static JLabel barcodeError = new JLabel();
    private static JLabel errorMessage = new JLabel();
    private static JLabel price = new JLabel();
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();
    
    private static int p = 20;
    private static double totalPrice = 0;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");

    public static void main(String[] args) {  
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        panel.setLayout(null);
      
        startButton = new JButton("Start Purchasing Session");
        startButton.setBounds(10, 40, 200, 25);
        startButton.addActionListener(new CashRegisterGUI());
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

            barcode = new JLabel("Barcode:");
            barcode.setBounds(10, 20, 130, 25);
            panel.add(barcode);
            
            barcodeInput = new JTextField();
            barcodeInput.setBounds(80, 20, 165, 25);
            panel.add(barcodeInput);
       
            enterButton = new JButton("Enter");
            enterButton.setBounds(10, 80, 80, 25);
            enterButton.addActionListener(new CashRegisterGUI());
            panel.add(enterButton);

            checkoutButton = new JButton("Checkout");
            checkoutButton.setBounds(100, 80, 100, 25);
            checkoutButton.addActionListener(new CashRegisterGUI());
            panel.add(checkoutButton);     
            
            cancelSessionButton = new JButton("Cancel Session");
            cancelSessionButton.setBounds(10, 200, 160, 25);
            cancelSessionButton.addActionListener(new CashRegisterGUI());
            panel.add(cancelSessionButton);    
        }
        
        else if (e.getSource() == cancelSessionButton) {
            p = 20;
            panel.removeAll();
            totalPrice = 0;
            
            frame.setSize(1001, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);

            startButton = new JButton("Start Purchasing Session");
            startButton.setBounds(10, 80, 200, 25);
            startButton.addActionListener(new CashRegisterGUI());
            panel.add(startButton);

            frame.setVisible(true);
        }
            
        else if (e.getSource() == enterButton) {
            
            prodPrice = new JLabel();
            prodName = new JLabel();
            
      
            barcodeError.setBounds(10, 100, 250, 25);
            panel.add(barcodeError);

            frame.setSize(1002, 500); //re-render

            switch (barcodeInput.getText()) {
                case "123" -> {
                    barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Apples");
                    panel.add(prodName);
                    prodPrice.setText("4.47");
                    panel.add(prodPrice);                     
                }
                case "456" -> { 
                    barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Banana");
                    panel.add(prodName);
                    prodPrice.setText("0.40");
                    panel.add(prodPrice);
                }
                case "789" -> { 
                    barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Raspberries");
                    panel.add(prodName);
                    prodPrice.setText("2.97");
                    panel.add(prodPrice);
                }
                case "012" -> {
                    barcodeError.setText("");
                    p += 15;
                    prodName.setBounds(400, p, 130, 25);
                    prodPrice.setBounds(550, p, 130, 25);

                    prodName.setText("Blueberries");
                    panel.add(prodName);
                    prodPrice.setText("5.97");
                    panel.add(prodPrice);
                }                   
                default -> {
                    barcodeError.setText("Unknown Product");
                    frame.setSize(1002, 500); //re-render
                }
            }
            
            barcodeInput.setText("");

                        
                        
            if (!prodPrice.getText().equals("")) {
                totalPrice += Double.parseDouble(prodPrice.getText());
            }

           frame.setSize(1003, 500); //re-render  
        }
        
        else if (e.getSource() == checkoutButton) {   
            frame.setSize(1004, 500); //re-render

            panel.remove(enterButton);
            panel.remove(checkoutButton);
            panel.remove(barcodeInput);
            panel.remove(barcode);
            panel.remove(barcodeError);
       
            price.setBounds(10, 20, 130, 25);
            price.setText("Total Price: " + decfor.format(totalPrice));
            panel.add(price);
                     
            paymentMethod = new JLabel("Add Payment Method (Cash, Debit, or Credit): ");
            paymentMethod.setBounds(10, 40, 300, 25);
            panel.add(paymentMethod);

            paymentMethodInput = new JTextField();
            paymentMethodInput.setBounds(10, 70, 165, 25);
            panel.add(paymentMethodInput);
       
            authorizeButton = new JButton("Authorize");
            authorizeButton.setBounds(10, 130, 100, 25);
            authorizeButton.addActionListener(new CashRegisterGUI());
            panel.add(authorizeButton);        
        }
               
        else if (e.getSource() == authorizeButton) {
            
            frame.setSize(1005, 500); //re-render
            errorMessage.setBounds(10, 100, 250, 25);
            errorMessage.setText("Please enter correct payment method");
                
            if (paymentMethodInput.getText().equals("Cash") || 
                    paymentMethodInput.getText().equals("Debit") || 
                            paymentMethodInput.getText().equals("Credit"))
            {                
                panel.remove(authorizeButton);
                panel.remove(paymentMethodInput);
                panel.remove(paymentMethod);
                panel.remove(cancelSessionButton);
                panel.remove(barcodeError);
                panel.remove(errorMessage);
                                
                JLabel reciept;
                reciept = new JLabel("Reciept");
                reciept.setBounds(490, 0, 200, 25);
                panel.add(reciept);
         
                price.setBounds(480, p+50, 130, 25);
    
                JLabel authMessage;
                authMessage = new JLabel();
                authMessage.setBounds(10, 40, 250, 25);
                
                switch (paymentMethodInput.getText()) {
                    case "Cash" -> authMessage.setText("Payment authorized using cash");
                    case "Debit" -> authMessage.setText("Payment authorized using Debit Card");
                    case "Credit" -> authMessage.setText("Payment authorized using Credit Card");
                }

                panel.add(authMessage);

                endSessionButton = new JButton("End Session");
                endSessionButton.setBounds(10, 70, 120, 25);
                endSessionButton.addActionListener(new CashRegisterGUI());
                panel.add(endSessionButton);
            }
            else {
                frame.setSize(1006, 500); //re-render
                panel.add(errorMessage);
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

            startButton = new JButton("Start Purchasing Session");
            startButton.setBounds(10, 80, 200, 25);
            startButton.addActionListener(new CashRegisterGUI());
            panel.add(startButton);
        }        
        
        frame.setVisible(true);       
    }  
}

