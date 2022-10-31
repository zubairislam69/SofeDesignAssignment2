
package purchase;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author zubxx
 */
public class CashRegister {
    JTextField paymentMethodInput = new JTextField();
    JLabel paymentMethod = new JLabel("Add Payment Method (Cash, Debit, or Credit): ");                     
    JButton authorizeButton = new JButton("Authorize");
    JLabel errorMessage = new JLabel();
    JLabel authMessage = new JLabel();
}
