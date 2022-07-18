package hotel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HotelGUI extends JFrame implements ActionListener, WindowListener {
    AdminFrame adminFrame;
    ReceptionFrame receptionFrame;
    AdminRoomModel roomModel;
    ReceptionRoomModel receptionRoomModel;

    public HotelGUI() throws IOException {
        roomModel = new AdminRoomModel();
        roomModel.load();

        receptionRoomModel = new ReceptionRoomModel();

        JPanel firstPanel = new JPanel();

        firstPanel.setLayout(new GridLayout(1, 2));

        adminFrame = new AdminFrame(roomModel);
        receptionFrame = new ReceptionFrame(receptionRoomModel);

        add(firstPanel);

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(this);

        JButton receptionButton = new JButton("Reception");
        receptionButton.addActionListener(this);

        firstPanel.add(adminButton);
        firstPanel.add(receptionButton);

        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Admin":
                adminFrame.setSize(800, 600);
                adminFrame.setVisible(true);
                receptionFrame.setVisible(false);
                break;
            case "Reception":
                receptionRoomModel.setRooms(roomModel.getRooms());
                receptionRoomModel.fireTableDataChanged();
                receptionFrame.setSize(800, 600);
                receptionFrame.setVisible(true);
                adminFrame.setVisible(false);
                break;
            default:
                System.out.println("Unreachable code in actionePerformed in HostGUI.");
        }
        // setVisible(false);

    }

    public static void main(String[] args) throws IOException {
        HotelGUI window = new HotelGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("The Best Hotel In The World");
        window.setSize(400, 300);
        window.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            roomModel.save();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
