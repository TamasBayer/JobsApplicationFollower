
package jobs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ControlPanel extends JPanel {
    
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel positionLabel;
    private JTextField positionTextField;
    private JLabel applicationDateLabel;
    private JTextField applicationDateTextField;
    private JButton okButton;
    
    public ControlPanel(){
        
        nameLabel = new JLabel("Employer Name: ");
        nameTextField = new JTextField(10);
        positionLabel = new JLabel("Position: ");
        positionTextField = new JTextField(10);
        applicationDateLabel = new JLabel("Application Date");
        applicationDateTextField = new JTextField(10);
        okButton = new JButton("Ok");
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        Border innerBorder = BorderFactory.createTitledBorder("Control Table");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        layoutComponents(); 
    }
    
     private void layoutComponents(){
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        /////////// First row /////////////////
        
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0;
        
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameTextField, gc);
        
        /////////// Second row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(positionLabel, gc);
        
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(positionTextField, gc);
        
        /////////// Third row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0 , 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(applicationDateLabel, gc);
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(applicationDateTextField, gc);
        
        /////////// Fourth row /////////////////
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty =  0;
        
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 9);
        gc.anchor = GridBagConstraints.LINE_END;
        add(okButton, gc);
     }

     public void clearTextFieldsContent(){
            nameTextField.setText(null);
            positionTextField.setText(null);
            applicationDateTextField.setText(null);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getPositionTextField() {
        return positionTextField;
    }

    public JTextField getApplicationDateTextField() {
        return applicationDateTextField;
    }

    public JButton getOkButton() {
        return okButton;
    }  
}
