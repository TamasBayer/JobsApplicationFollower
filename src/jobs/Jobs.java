
package jobs;

import javax.swing.SwingUtilities;

public class Jobs {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }   
}
