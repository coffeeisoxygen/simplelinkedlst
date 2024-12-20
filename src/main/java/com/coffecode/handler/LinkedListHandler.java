package com.coffecode.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.coffecode.data.Data;
import com.coffecode.linked.LinkedList;
import com.coffecode.node.Node;

public class LinkedListHandler implements ActionListener {
    private LinkedList linkedList;
    private final JTextField nimField;
    private final JTextField namaField;
    private final JTextField nilaiField;
    private final DefaultTableModel tableModel;
    private final JButton addButton;
    private final JButton findButton;
    private final JButton deleteButton;
    private final JButton clearButton;

    private static final String ISI_DATA = "Isi Data";
    private static final String FIND_DATA = "Find Data";
    private static final String DELETE_DATA = "Hapus Data";
    private static final String CLEAR_ALL = "Hapus Semua";

    public LinkedListHandler(LinkedList linkedList, JTextField nimField, JTextField namaField,
            JTextField nilaiField, DefaultTableModel tableModel) {
        this.linkedList = linkedList;
        this.nimField = nimField;
        this.namaField = namaField;
        this.nilaiField = nilaiField;
        this.tableModel = tableModel;
        this.addButton = new JButton(ISI_DATA);
        this.findButton = new JButton(FIND_DATA);
        this.deleteButton = new JButton(DELETE_DATA);
        this.clearButton = new JButton(CLEAR_ALL);

        addButton.setActionCommand(ISI_DATA);
        findButton.setActionCommand(FIND_DATA);
        deleteButton.setActionCommand(DELETE_DATA);
        clearButton.setActionCommand(CLEAR_ALL);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ISI_DATA -> handleIsiData();
            case FIND_DATA -> handleFindData();
            case DELETE_DATA -> handleHapusData();
            case CLEAR_ALL -> handleClearAll();
            default ->
                JOptionPane.showMessageDialog(null, "Unknown action command!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleIsiData() {
        String nim = nimField.getText();
        String nama = namaField.getText();
        String nilaiText = nilaiField.getText();

        if (nim.isEmpty() || nama.isEmpty() || nilaiText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int nilai = Integer.parseInt(nilaiText);
                if (linkedList.findData(nim)) {
                    JOptionPane.showMessageDialog(null, "NIM sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    linkedList.isiData(nim, nama, nilai);
                    updateTableData(); // Refresh table
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Nilai harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleFindData() {
        String nim = JOptionPane.showInputDialog(null, "Masukkan NIM untuk mencari data:");
        if (nim != null && !nim.isEmpty()) {
            boolean found = linkedList.findData(nim);
            if (found) {
                String[] data = getDataByNim(nim);
                JOptionPane.showMessageDialog(null,
                        "Data ditemukan:\nNIM: " + data[0] + "\nNama: " + data[1] + "\nNilai: " + data[2]);
            } else {
                JOptionPane.showMessageDialog(null, "Data dengan NIM " + nim + " tidak ditemukan.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Field NIM harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleHapusData() {
        String nim = JOptionPane.showInputDialog(null, "Masukkan NIM untuk menghapus data:");
        if (nim != null && !nim.isEmpty()) {
            boolean deleted = linkedList.hapusData(nim);
            if (deleted) {
                JOptionPane.showMessageDialog(null, "Data dengan NIM " + nim + " berhasil dihapus.");
                updateTableData(); // Refresh table after deletion
            } else {
                JOptionPane.showMessageDialog(null, "Data dengan NIM " + nim + " tidak ditemukan.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Field NIM harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleClearAll() {
        linkedList = new LinkedList();
        updateTableData(); // Refresh table after clearing all data
    }

    // Fungsi untuk memperbarui data di JTable
    public void updateTableData() {
        // Clear table before updating
        tableModel.setRowCount(0);

        // Ambil data dari linked list dan masukkan ke dalam tabel
        String[][] data = getAllData();
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }

    public String[] getDataByNim(String nim) {
        Node penunjuk = linkedList.getHead();
        while (penunjuk != null) {
            Data d = penunjuk.getData();
            if (d.getNim().equals(nim)) {
                return new String[] { d.getNim(), d.getNama(), String.valueOf(d.getNilai()) };
            }
            penunjuk = penunjuk.getBerikutnya();
        }
        return new String[0];
    }

    public String[][] getAllData() {
        int size = linkedList.size();
        String[][] data = new String[size][3];
        Node penunjuk = linkedList.getHead();
        int index = 0;
        while (penunjuk != null) {
            Data d = penunjuk.getData();
            data[index][0] = d.getNim();
            data[index][1] = d.getNama();
            data[index][2] = String.valueOf(d.getNilai());
            penunjuk = penunjuk.getBerikutnya();
            index++;
        }
        return data;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }
}