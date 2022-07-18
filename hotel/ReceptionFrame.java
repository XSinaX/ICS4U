package hotel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

class ReceptionFrame extends JFrame implements ActionListener {
    ReceptionRoomModel receptioonRoomModel;
    JTextField checkinName;
    JTextField checkInNumber;
    JTextField checkoutNumber;

    public ReceptionFrame(ReceptionRoomModel receptioonRoomModel) {
        this.receptioonRoomModel = receptioonRoomModel;
        setLayout(new FlowLayout());
        createTable();
        addcheckInCheckOut();
    }

    private void addcheckInCheckOut() {
        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(0, 2));

        JLabel checkInTitle = new JLabel("Check In Section");
        JLabel checkOutTitle = new JLabel("Check Out Section");
        JLabel nameLabel = new JLabel("Guest Name");
        JLabel roomNumberLabel = new JLabel("Room Number");
        JLabel checkOutNumberLabel = new JLabel("Check out Number");

        checkinName = new JTextField();
        checkinName.addActionListener(this);
        checkInNumber = new JTextField();
        checkInNumber.addActionListener(this);
        checkoutNumber = new JTextField();
        checkoutNumber.addActionListener(this);

        JButton checkINButton = new JButton("Check In");
        checkINButton.addActionListener(this);
        JButton checkOutButton = new JButton("Check Out");
        checkOutButton.addActionListener(this);

        panel.add(checkInTitle);
        panel.add(new JPanel());

        panel.add(roomNumberLabel);
        panel.add(checkInNumber);

        panel.add(nameLabel);
        panel.add(checkinName);

        panel.add(checkINButton);
        panel.add(new JPanel());

        panel.add(checkOutTitle);
        panel.add(new JPanel());

        panel.add(checkOutNumberLabel);
        panel.add(checkoutNumber);

        panel.add(checkOutButton);

    }

    private void createTable() {
        JTable table = new JTable(receptioonRoomModel);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBounds(0, 0, 800, 600);
        add(tablePane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Check Out":
                receptioonRoomModel.checkOut(Integer.parseInt(checkoutNumber.getText()) - 1);
                receptioonRoomModel.fireTableDataChanged();
                break;
            case "Check In":
                receptioonRoomModel.checkIn(
                        Integer.parseInt(checkInNumber.getText()) - 1,
                        checkinName.getText());
                receptioonRoomModel.fireTableDataChanged();
                break;
            default:
                System.out.println("Unreachabel code in ReceptionFrame");
        }
    }
}