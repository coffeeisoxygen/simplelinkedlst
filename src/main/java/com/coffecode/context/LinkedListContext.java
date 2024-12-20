package com.coffecode.context;

import com.coffecode.animation.AnimationController;
import com.coffecode.animation.AnimationHandler;
import com.coffecode.linked.LinkedList;

public class LinkedListContext {
    private LinkedList linkedList;
    private AnimationController animationController;
    private AnimationHandler animationHandler;

    public LinkedListContext() {
        this.linkedList = new LinkedList();
        this.animationController = new AnimationController(linkedList);
        this.animationHandler = new AnimationHandler(animationController.getNodes());
    }

    public LinkedList getLinkedList() {
        return linkedList;
    }

    public AnimationController getAnimationController() {
        return animationController;
    }

    public AnimationHandler getAnimationHandler() {
        return animationHandler;
    }
}