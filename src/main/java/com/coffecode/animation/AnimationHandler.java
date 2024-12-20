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
        int nodeWidth = 150;
        int nodeHeight = 70;
        int spacing = 50;

        if (nodes.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("Linked List Kosong", x, y);
            g.drawRect(x, y + 20, nodeWidth, nodeHeight);
            g.drawString("Head", x + 60, y + 40);
            g.drawString("Tail", x + 60, y + 60);
            g.drawString("Null", x + 60, y + 80);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Head", x - 30, y + 35);
            g.drawLine(x - 10, y + 35, x, y + 35);

            for (Node node : nodes) {
                Data data = node.getData();
                g.setColor(Color.BLACK);
                g.drawRect(x, y, nodeWidth, nodeHeight);
                g.drawString("NIM: " + data.getNim(), x + 10, y + 20);
                g.drawString("Nama: " + data.getNama(), x + 10, y + 35);
                g.drawString("Nilai: " + data.getNilai(), x + 10, y + 50);

                if (node.getBerikutnya() != null) {
                    g.drawString("Next: ", x + 10, y + 65);
                    g.drawLine(x + nodeWidth, y + nodeHeight / 2, x + nodeWidth + spacing, y + nodeHeight / 2);
                    g.drawLine(x + nodeWidth + spacing - 5, y + nodeHeight / 2 - 5, x + nodeWidth + spacing, y + nodeHeight / 2);
                    g.drawLine(x + nodeWidth + spacing - 5, y + nodeHeight / 2 + 5, x + nodeWidth + spacing, y + nodeHeight / 2);
                } else {
                    g.drawString("Next: Null", x + 10, y + 65);
                }

                x += nodeWidth + spacing;
            }

            g.setColor(Color.BLACK);
            g.drawString("Tail", x - nodeWidth - spacing + 60, y + nodeHeight + 20);
            g.drawLine(x - nodeWidth - spacing + 60, y + nodeHeight + 10, x - nodeWidth - spacing + 60, y + nodeHeight);
        }
    }
}
