import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static JTextField paymentMethodInput, prodPriceInput, prodNameInput ;
    private static JLabel prodName, prodPrice, paymentMethod;;                   
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();
    private static double totalPrice = 0;
    private static int p = 20;

    public static void main(String[] args) {  
        frame.setSize(500, 200);
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
            frame.setSize(1000, 300); //re-render

            prodName = new JLabel("Product name:");
            prodName.setBounds(10, 20, 130, 25);
            panel.add(prodName);

            prodNameInput = new JTextField();
            prodNameInput.setBounds(110, 20, 165, 25);
            panel.add(prodNameInput);

            prodPrice = new JLabel("Product price:");
            prodPrice.setBounds(10, 50, 130, 25);
            panel.add(prodPrice);
 
            prodPriceInput = new JTextField();
            prodPriceInput.setBounds(110, 50, 165, 25);
            panel.add(prodPriceInput);
                        
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
            
            frame.setSize(1000, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);

            startButton = new JButton("Start Purchasing Session");
            startButton.setBounds(10, 80, 200, 25);
            startButton.addActionListener(new CashRegisterGUI());
            panel.add(startButton);

            frame.setVisible(true);
        }
            
        else if (e.getSource() == enterButton) {    //sadasdsadasd 
            p += 15;
            
            frame.setSize(1001, 300); //re-render
            totalPrice += Double.parseDouble(prodPriceInput.getText());
 
            JLabel column1;
            column1 = new JLabel("Product Name");
            column1.setBounds(400, 15, 200, 25);
            panel.add(column1);
            
            JLabel column2;
            column2 = new JLabel("Product Price");
            column2.setBounds(550, 15, 200, 25);
            panel.add(column2);
                   
            JLabel ticketProdName;
            ticketProdName = new JLabel();
            ticketProdName.setBounds(400, p, 200, 25);
            ticketProdName.setText(prodNameInput.getText());
            panel.add(ticketProdName);

            JLabel ticketProdPrice;
            ticketProdPrice = new JLabel();
            ticketProdPrice.setBounds(550, p, 130, 25);
            ticketProdPrice.setText(prodPriceInput.getText());
            panel.add(ticketProdPrice);
      
            frame.setSize(1002, 300); //re-render
   
            prodPriceInput.setText("");
            prodNameInput.setText("");
        }
        
        else if (e.getSource() == checkoutButton) {   
            frame.setSize(1003, 300); //re-render

            panel.remove(enterButton);
            panel.remove(prodName);
            panel.remove(prodPrice);
            panel.remove(prodNameInput);
            panel.remove(prodPriceInput);
            panel.remove(checkoutButton);
          
            JLabel price;
            price = new JLabel();
            price.setBounds(10, 20, 130, 25);
            price.setText("Total Price: " + totalPrice);
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
            if (paymentMethodInput.getText().equals("Cash") || 
                    paymentMethodInput.getText().equals("Debit") || 
                            paymentMethodInput.getText().equals("Credit"))
            {                
                panel.remove(authorizeButton);
                panel.remove(paymentMethodInput);
                panel.remove(paymentMethod);
                panel.remove(cancelSessionButton);

                JLabel reciept;
                reciept = new JLabel("Reciept");
                reciept.setBounds(490, 0, 200, 25);
                panel.add(reciept);
            
                frame.setSize(1004, 300); //re-render

                JLabel authMessage;
                authMessage = new JLabel();
                authMessage.setBounds(10, 40, 250, 25);

                if (paymentMethodInput.getText().equals("Cash")){
                    authMessage.setText("Payment authorized using cash");
                }

                else if (paymentMethodInput.getText().equals("Debit")){
                    authMessage.setText("Payment authorized using Debit Card");
                }
                
                else if (paymentMethodInput.getText().equals("Credit")){
                    authMessage.setText("Payment authorized using Credit Card");
                }

                panel.add(authMessage);

                endSessionButton = new JButton("End Session");
                endSessionButton.setBounds(10, 70, 120, 25);
                endSessionButton.addActionListener(new CashRegisterGUI());
                panel.add(endSessionButton);
            }
            else {
                frame.setSize(1005, 300); //re-render

                JLabel errorMessage;
                errorMessage = new JLabel("Please enter correct payment method");
                errorMessage.setBounds(10, 100, 250, 25);
                panel.add(errorMessage);
            }
        }
        
        else if (e.getSource() == endSessionButton) {
            p = 20;
            panel.removeAll();
            frame.setSize(1000, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);

            startButton = new JButton("Start Purchasing Session");
            startButton.setBounds(10, 80, 200, 25);
            startButton.addActionListener(new CashRegisterGUI());
            panel.add(startButton);
            
            frame.setVisible(true);
        }            
        frame.setVisible(true);       
    }  
}

