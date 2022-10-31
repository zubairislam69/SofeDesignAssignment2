
package purchase;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author zubxx
 */
public class Keyboard {
    JLabel barcode = new JLabel("Barcode:");
    JTextField barcodeInput = new JTextField();
    JButton enterButton = new JButton("Enter");
    JButton checkoutButton = new JButton("Checkout");    
    JLabel barcodeError = new JLabel();
}
