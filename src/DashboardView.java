import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by danniwu on 21/05/15.
 */
public class DashboardView {


    private JFrame frame;

    private JPanel mainpanel, panel1, panel2, panel3;

    private JLabel label1, label2;

    private JButton but1, but2;

    private JList list1;

    private JScrollPane pane1, pane2;

    private String username;

    private DefaultListModel model1;

    private DefaultTableModel model2;

    private JMenuBar menuBar;

    private JMenu menu, projectMenu, opgaveMenu, submenu;

    private JMenuItem UserMenuItem, ProjectMenuItem1,ProjectMenuItem2,ProjectMenuItem3, opgMenuItem1,opgMenuItem2,opgMenuItem3;

    private JTable mainTable;

    private DashboardModel model;

    public DashboardView(DashboardModel model) {
        this.model = model;
    }

}
