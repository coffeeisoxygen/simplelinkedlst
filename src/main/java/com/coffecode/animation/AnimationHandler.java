package com.coffecode.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import com.coffecode.data.Data;
import com.coffecode.node.Node;

public class AnimationHandler {
    private List<Node> nodes;

    public AnimationHandler(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void draw(Graphics g) {
        int x = 50;
        int y = 50;
        int nodeWidth = 100;
        int nodeHeight = 50;
        int spacing = 50;

        if (nodes.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("Linked List Kosong", x, y);
            g.drawRect(x, y + 20, nodeWidth, nodeHeight);
            g.drawString("Head", x + 35, y + 40);
            g.drawString("Tail", x + 35, y + 60);
            g.drawString("Null", x + 35, y + 80);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Head", x + 35, y - 10);
            g.drawLine(x + 50, y, x + 50, y + 20);

            for (Node node : nodes) {
                Data data = node.getData();
                g.setColor(Color.BLUE);
                g.drawRect(x, y, nodeWidth, nodeHeight);
                g.drawString("NIM: " + data.getNim(), x + 10, y + 15);
                g.drawString("Nama: " + data.getNama(), x + 10, y + 30);
                g.drawString("Nilai: " + data.getNilai(), x + 10, y + 45);

                if (node.getBerikutnya() != null) {
                    g.drawLine(x + nodeWidth, y + nodeHeight / 2, x + nodeWidth + spacing, y + nodeHeight / 2);
                    g.drawLine(x + nodeWidth + spacing - 5, y + nodeHeight / 2 - 5, x + nodeWidth + spacing,
                            y + nodeHeight / 2);
                    g.drawLine(x + nodeWidth + spacing - 5, y + nodeHeight / 2 + 5, x + nodeWidth + spacing,
                            y + nodeHeight / 2);
                } else {
                    g.drawLine(x + nodeWidth, y + nodeHeight / 2, x + nodeWidth + spacing, y + nodeHeight / 2);
                    g.drawLine(x + nodeWidth + spacing - 5, y + nodeHeight / 2 - 5, x + nodeWidth + spacing,
                            y + nodeHeight / 2);
                    g.drawLine(x + nodeWidth + spacing - 5, y + nodeHeight / 2 + 5, x + nodeWidth + spacing,
                            y + nodeHeight / 2);
                    g.drawString("Null", x + nodeWidth + spacing + 10, y + nodeHeight / 2 + 5);
                }

                x += nodeWidth + spacing;
            }

            g.setColor(Color.GREEN);
            g.drawString("Tail", x - nodeWidth - spacing + 35, y + nodeHeight + 20);
            g.drawLine(x - nodeWidth - spacing + 50, y + nodeHeight, x - nodeWidth - spacing + 50, y + nodeHeight + 10);
        }
    }
}