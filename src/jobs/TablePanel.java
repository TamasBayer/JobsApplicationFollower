
package jobs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel {
    
    private int row;
    private int changedRow;
    private int changedColumn;
    private String newData;
    private JPopupMenu popup;
    private JMenuItem removeItem;
    private String[] colNames = {"ID", "Name", "Position", "Application Date", "Response", "Interview", "Job Offer"};
    
     private DefaultTableModel model = new DefaultTableModel(){
    
         @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return int.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
         
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }
    
     };
    private JTable jTable = new JTable(model);
    
    public TablePanel(ArrayList<JobsPojo> list){
        
               
        setLayout(new BorderLayout());
        
        add(new JScrollPane(jTable), BorderLayout.CENTER);
        
        fillTableWithData(list);
        
           
        jTable.getColumnModel().getColumn(0).setMinWidth(0);
        jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable.getColumnModel().getColumn(0).setWidth(0);

        jTable.getColumnModel().getColumn(3).setMinWidth(100);
        jTable.getColumnModel().getColumn(3).setMaxWidth(100);
           
        popup = new JPopupMenu();
        
        removeItem = new JMenuItem("Delet row");
        popup.add(removeItem);
        
        row = 0;
        
        jTable.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                row = jTable.rowAtPoint(e.getPoint());
                
                jTable.getSelectionModel().setSelectionInterval(row, row);
                   
                if(e.getButton() == MouseEvent.BUTTON3){
                    popup.show(jTable, e.getX(), e.getY());
                    changedRow = (int) model.getValueAt(row, 0);
                    
               }               
            }
        });
        
        removeItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                model.removeRow(jTable.getSelectedRow());                
                 }
        });           
    }

    public void addData(Object rowData[]){
        
        model.addRow(rowData);
    }
    
    private void fillTableWithData(ArrayList<JobsPojo> list){
        
        Object rowData[] = new Object[7];
       for(int i = 0; i < list.size(); i++ ){
           rowData[0] = list.get(i).getId();
           rowData[1] = list.get(i).getName();
           rowData[2] = list.get(i).getPosition();
           rowData[3] = list.get(i).getApplicationDate();
           rowData[4] = Boolean.parseBoolean(list.get(i).getResponse());
           rowData[5] = Boolean.parseBoolean(list.get(i).getInterview());
           rowData[6] = Boolean.parseBoolean(list.get(i).getJobOffer());
        
           model.addRow(rowData);
           }; 
    }

    
    public DefaultTableModel getModel() {
        return model;
    }

    public int getChangedRow() {
        return changedRow;
    }

    public JMenuItem getRemoveItem() {
        return removeItem;
    }

    public int getChangedColumn() {
        return changedColumn;
    }

    public String getNewData() {
        return newData;
    }
}
