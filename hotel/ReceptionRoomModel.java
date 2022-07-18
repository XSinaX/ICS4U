package hotel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ReceptionRoomModel extends AbstractTableModel {
    ArrayList<Room> rooms;
    String[] columns = { "Room Number", "Booked", "Guest Name" };

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int getRowCount() {
        return rooms.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Room room = rooms.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return room.booked;
            case 2:
                return room.guestName;
        }
        return "-";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public void checkIn(int roomNumber, String guestName) {
        rooms.get(roomNumber).guestName = guestName;
        rooms.get(roomNumber).booked = true;
    }

    public void checkOut(int roomNumber) {
        rooms.get(roomNumber).booked = false;
        rooms.get(roomNumber).guestName = "available";
    }
}
