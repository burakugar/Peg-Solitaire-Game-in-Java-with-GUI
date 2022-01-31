
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class InitilazePanel extends JFrame {

    private JPanel jContentPane = null;

    public InitilazePanel() {
        super();
        initialize();
    }

    private void initialize() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("JFrame");
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);

            JPanel panel = new JPanel();

            panel.setBounds(61, 11, 81, 140);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            jContentPane.add(panel);

            JCheckBox c1 = new JCheckBox("Human Game");
            panel.add(c1);
            c1 = new JCheckBox("Computer Game");
            panel.add(c1);

        }
        return jContentPane;
    }
}
