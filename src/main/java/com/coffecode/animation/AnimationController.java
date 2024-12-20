package com.coffecode.animation;

import java.util.ArrayList;
import java.util.List;

import com.coffecode.linked.LinkedList;
import com.coffecode.node.Node;

public class AnimationController {
    private LinkedList linkedList;
    private List<Node> nodes;

    public AnimationController(LinkedList linkedList) {
        this.linkedList = linkedList;
        this.nodes = new ArrayList<>();
        initializeNodes();
    }

    private void initializeNodes() {
        updateNodes();
    }

    public void updateNodes() {
        nodes.clear();
        Node current = linkedList.getHead();
        while (current != null) {
            nodes.add(current);
            current = current.getBerikutnya();
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
