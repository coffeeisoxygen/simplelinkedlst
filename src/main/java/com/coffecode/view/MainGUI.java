package com.coffecode.view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        nimField = new JTextField(10);
        namaField = new JTextField(10);
        nilaiField = new JTextField(10);

        // Setup controller and handler
        tableModel = new DefaultTableModel(new String[] { "NIM", "Nama", "Nilai" }, 0);
        handler = new LinkedListHandler(context.getLinkedList(), nimField, namaField, nilaiField, tableModel);

        // Setup panels
        add(createLeftBottomPanel(), BorderLayout.WEST);
        add(createCanvasPanel(), BorderLayout.CENTER);

        // Setup action listeners
        setupActionListeners();
    }

    private JPanel createLeftBottomPanel() {
        JPanel leftBottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel kontrol (NIM, Nama, Nilai, tombol)
        JPanel controlPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(new JLabel("NIM:"), gbc);
        gbc.gridx = 1;
        controlPanel.add(nimField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        controlPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        controlPanel.add(namaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        controlPanel.add(new JLabel("Nilai:"), gbc);
        gbc.gridx = 1;
        controlPanel.add(nilaiField, gbc);

        // Tombol-tombol
        JPanel buttonPanel = new JPanel();
        JButton addButton = handler.getAddButton();
        JButton findButton = handler.getFindButton();
        JButton deleteButton = handler.getDeleteButton();
        JButton clearButton = handler.getClearButton();

        buttonPanel.add(addButton);
        buttonPanel.add(findButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        controlPanel.add(buttonPanel, gbc);

        // Panel kosong untuk tabel
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tablePanel.add(tableScroll, BorderLayout.CENTER);

        // Menambahkan panel kontrol dan tabel ke kiri bawah
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftBottomPanel.add(controlPanel, gbc);

        gbc.gridy = 1;
        gbc.gridheight = 1;
        leftBottomPanel.add(tablePanel, gbc);

        return leftBottomPanel;
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
            context.getAnimationController().updateNodes();
            canvas.repaint();
        });
    }

    public void createAndShowGUI() {
        setVisible(true);
    }
}
