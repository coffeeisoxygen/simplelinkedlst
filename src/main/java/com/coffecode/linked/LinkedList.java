package com.coffecode.linked;

import com.coffecode.data.Data;
import com.coffecode.node.Node;

public class LinkedList {
    private Node head = null;
    private Node tail = null;

    public void isiData(String nim, String nama, int nilai) {
        Data dataBaru = new Data(nim, nama, nilai);
        Node nodeBaru = new Node(dataBaru);
        if (head == null) {
            head = nodeBaru;
            tail = nodeBaru;
        } else {
            tail.setBerikutnya(nodeBaru);
            tail = nodeBaru;
        }
    }

    public void tampilkan() {
        System.out.println("Isi Linked List");
        Node penunjuk = head;
        while (penunjuk != null) {
            penunjuk.getData().infoData();
            penunjuk = penunjuk.getBerikutnya();
        }
    }

    public boolean findData(String nim) {
        Node posisi = head;
        while (posisi != null) {
            String currentNim = posisi.getData().getNim();
            if (currentNim.equals(nim)) {
                return true;
            }
            posisi = posisi.getBerikutnya();
        }
        return false;
    }

    public boolean hapusData(String nim) {
        Node pendahulu = null;
        Node posisi = head;
        while (posisi != null) {
            String currentNim = posisi.getData().getNim();
            if (currentNim.equals(nim)) {
                if (pendahulu == null) {
                    head = head.getBerikutnya();
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    pendahulu.setBerikutnya(posisi.getBerikutnya());
                    if (posisi == tail) {
                        tail = pendahulu;
                    }
                }
                return true;
            }
            pendahulu = posisi;
            posisi = posisi.getBerikutnya();
        }
        System.out.println("Data yang akan dihapus tidak ditemukan");
        return false;
    }

    public Node getHead() {
        return head;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getBerikutnya();
        }
        return count;
    }

}
