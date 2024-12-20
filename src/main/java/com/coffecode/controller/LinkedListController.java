package com.coffecode.controller;

import com.coffecode.data.Data;
import com.coffecode.linked.LinkedList;
import com.coffecode.node.Node;

public class LinkedListController {
    private LinkedList linkedList;

    public LinkedListController(LinkedList linkedList) {
        this.linkedList = linkedList;
    }

    public void addData(String nim, String nama, int nilai) {
        linkedList.isiData(nim, nama, nilai);
    }

    public boolean findData(String nim) {
        return linkedList.findData(nim);
    }

    public boolean hapusData(String nim) {
        return linkedList.hapusData(nim);
    }

    public void clearAllData() {
        linkedList = new LinkedList();
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
}