package com.coffecode.node;

import com.coffecode.data.Data;

public class Node {
    private Data data;
    private Node berikutnya;

    public Node(Data data) {
        this.data = data;
        this.berikutnya = null;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Node getBerikutnya() {
        return berikutnya;
    }

    public void setBerikutnya(Node berikutnya) {
        this.berikutnya = berikutnya;
    }
}