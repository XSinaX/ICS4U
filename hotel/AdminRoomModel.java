package hotel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AdminRoomModel extends AbstractTableModel {
    String[] columns = { "Room Number", "Num Beds", "Price in $" };
    ArrayList<Room> rooms = new ArrayList<>();

    @Override
    public int getRowCount() {
        return rooms.size();
    }

    public void addRow() {
        rooms.add(new Room());
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return room.numBeds;
            case 2:
                return room.price;
            default:
                return "-";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 1:
                room.numBeds = Integer.parseInt((String) aValue);
                break;
            case 2:
                room.price = Integer.parseInt((String) aValue);
                break;
            default:
                System.out.println("Error: no column is defined.");
        }

        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        return true;
    }

    public void deleteRow() {
        if (!rooms.isEmpty()) {
            rooms.remove(rooms.size() - 1);
        }
    }

    public void save() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter("rooms.txt"));
        for (Room room : rooms) {
            printWriter.println(room.numBeds);
            printWriter.println(room.price);
            printWriter.println(room.booked);
            printWriter.println(room.guestName);
        }
        printWriter.close();
    }

    public void load() throws IOException {
        if (!new File("rooms.txt").exists())
            return;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("rooms.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Room room = new Room();
            room.numBeds = Integer.parseInt(line);
            room.price = Integer.parseInt(bufferedReader.readLine());
            room.booked = Boolean.parseBoolean(bufferedReader.readLine());
            room.guestName = bufferedReader.readLine();
            rooms.add(room);
        }
        bufferedReader.close();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}