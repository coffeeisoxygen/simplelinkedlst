package com.coffecode.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.coffecode.context.LinkedListContext;
import com.coffecode.handler.LinkedListHandler;

public class MainGUI extends JFrame {
    private DefaultTableModel tableModel;
    private final JTextField nimField;
    private final JTextField namaField;
    private final JTextField nilaiField;
    private final transient LinkedListHandler handler;
    private final LinkedListContext context;
    private Canvas canvas;

    public MainGUI(LinkedListContext context) {
        this.context = context;
        setTitle("Linked List Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Inisialisasi JTextField
        nimField = new JTextField(20);
        namaField = new JTextField(20);
        nilaiField = new JTextField(20);

        // Setup controller and handler
        tableModel = new DefaultTableModel(new String[] { "NIM", "Nama", "Nilai" }, 0);
        handler = new LinkedListHandler(context.getLinkedList(), nimField, namaField, nilaiField, tableModel);

        // Setup panels
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createLeftPanel(), createCanvasPanel());
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        // Setup action listeners
        setupActionListeners();
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(createInputPanel(), BorderLayout.NORTH);
        leftPanel.add(createTablePanel(), BorderLayout.CENTER);
        return leftPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("NIM:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(nimField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Nilai:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(nilaiField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton addButton = handler.getAddButton();
        inputPanel.add(addButton, gbc);

        return inputPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tablePanel.add(tableScroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton findButton = handler.getFindButton();
        buttonPanel.add(findButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JButton deleteButton = handler.getDeleteButton();
        buttonPanel.add(deleteButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        JButton clearButton = handler.getClearButton();
        buttonPanel.add(clearButton, gbc);

        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        return tablePanel;
    }

    private JPanel createCanvasPanel() {
        canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                context.getAnimationHandler().draw(g);
            }
        };
        canvas.setBackground(Color.WHITE);
        JPanel canvasPanel = new JPanel(new BorderLayout());
        canvasPanel.add(canvas, BorderLayout.CENTER);
        return canvasPanel;
    }

    private void setupActionListeners() {
        JButton addButton = handler.getAddButton();
        JButton findButton = handler.getFindButton();
        JButton deleteButton = handler.getDeleteButton();
        JButton clearButton = handler.getClearButton();

        addButton.addActionListener(e -> {
            handler.handleIsiData();
            context.getAnimationController().updateNodes();
            canvas.repaint();
        });
        findButton.addActionListener(e -> {
            handler.handleFindData();
            context.getAnimationController().updateNodes();
            canvas.repaint();
        });
        deleteButton.addActionListener(e -> {
            handler.handleHapusData();
            context.getAnimationController().updateNodes();
            canvas.repaint();
        });
        clearButton.addActionListener(e -> {
            handler.handleClearAll();
            context.getAnimationController().clearNodes();
            canvas.repaint();
        });
    }

    public void createAndShowGUI() {
        setVisible(true);
    }
}
