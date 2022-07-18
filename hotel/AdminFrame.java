package hotel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class AdminFrame extends JFrame implements ActionListener {
    AdminRoomModel roomModel;

    public AdminFrame(AdminRoomModel roomModel) {
        this.roomModel = roomModel;
        setLayout(new FlowLayout());

        createTable();

        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        add(deleteButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        add(saveButton);

        setTitle("Welcom the best admin of the world");
    }

    private void createTable() {
        JTable table = new JTable(roomModel);
        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBounds(0, 0, 800, 600);
        add(tablePane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add":
                roomModel.addRow();
                roomModel.fireTableDataChanged();
                break;
            case "Delete":
                roomModel.deleteRow();
                roomModel.fireTableDataChanged();
                break;
            case "Save":
                try {
                    roomModel.save();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                System.out.println("Unreachabel code in AdminFrame");
        }
    }
}