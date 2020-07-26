
package jobs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class MainFrame extends JFrame{
    
        private DB db = new DB();
        private JTextField nTextField;
        private JTextField pTextField;
        private JTextField aDateTextField;
        private JButton okButton;
        private ControlPanel cPanel;
        private TablePanel tPanel;
        private JFrame frame;
        
    
        public MainFrame(){
        super("Jobs Applications");
        
        ArrayList<JobsPojo> list = db.getAllJobs();
        
        tPanel = new TablePanel(list);
        cPanel = new ControlPanel();
        nTextField = cPanel.getNameTextField();
        pTextField = cPanel.getPositionTextField();
        aDateTextField = cPanel.getApplicationDateTextField();
        okButton = cPanel.getOkButton();
        
        setSize(750, 450);
        setMinimumSize( new Dimension(650, 450));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());

        
       
        add(cPanel, BorderLayout.WEST);
        add(new JScrollPane(tPanel), BorderLayout.CENTER);
        
        okButtonPressed();
       
       
        tPanel.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int column = e.getColumn();
                int row = e.getFirstRow();
                
                if (e.getType() == TableModelEvent.UPDATE) {
                   int changedRow = (int) tPanel.getModel().getValueAt(row, 0);
                   int changedColumn = column;
                   String newData = tPanel.getModel().getValueAt(row, column).toString();
                   System.out.println(changedColumn);
                   
                   db.update(changedRow, changedColumn, newData);
            }
        }    
});
       
      
       tPanel.getRemoveItem().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                    deleteFromDatabase();
                 }          
        });
    
       aDateTextField.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
             if (e.getKeyCode() == KeyEvent.VK_ENTER) {
               okButton.doClick();
            }
         }
      });

    }
        
        private void loadTextFields(){
            if(!nTextField.getText().isEmpty() && !pTextField.getText().isEmpty() && !aDateTextField.getText().isEmpty()){
                Object rowData[] = new Object[7];
                
                db.addJob(nTextField.getText(), pTextField.getText(), aDateTextField.getText(), "", "", "");
                cPanel.clearTextFieldsContent();
                
                JobsPojo lastJob = db.getLastJob();
                
                rowData[0] = lastJob.getId();
                rowData[1] = lastJob.getName();
                rowData[2] = lastJob.getPosition();
                rowData[3] = lastJob.getApplicationDate();
                rowData[4] = Boolean.parseBoolean(lastJob.getResponse());
                rowData[5] = Boolean.parseBoolean(lastJob.getInterview());
                rowData[6] = Boolean.parseBoolean(lastJob.getJobOffer());
                
                tPanel.addData(rowData);
            } else {
                JOptionPane.showMessageDialog(frame, "Data Missing.", "Missing", JOptionPane.WARNING_MESSAGE);
            }
        } 
        
        private void okButtonPressed(){
        okButton.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
                
                loadTextFields();
                
            }
        });
    }
       
        private void deleteFromDatabase(){
            int rowChanged = tPanel.getChangedRow();
            
                db.delete(rowChanged);
        }   
}
