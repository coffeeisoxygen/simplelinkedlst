package com.coffecode;

import javax.swing.SwingUtilities;

import com.coffecode.context.LinkedListContext;
import com.coffecode.view.MainGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LinkedListContext context = new LinkedListContext();
            MainGUI mainGUI = new MainGUI(context);
            mainGUI.createAndShowGUI();
        });
    }
}