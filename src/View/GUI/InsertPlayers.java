/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.GUI;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Utente
 */
public class InsertPlayers extends javax.swing.JFrame {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JButton clearPlayerNameButton;
    private javax.swing.JLabel insertNameLabel;
    private javax.swing.JPanel insertNamePanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable playerNameTable;
    private javax.swing.JButton quitPlayerButton;
    private javax.swing.JButton startGamePlayerButton;
    private javax.swing.JTextField writeNameTextField;
    // End of variables declaration//GEN-END:variables

    List<String> names = new LinkedList<>();

    /**
     * Creates new form InsertPlayers
     */
    public InsertPlayers() {
        initComponents();
    }

    // getter e setter
    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public javax.swing.JButton getStartGamePlayerButton() {
        return startGamePlayerButton;
    }

    public javax.swing.JTable getPlayerNameTable() {
        return playerNameTable;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clearPlayerNameButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerNameTable = new javax.swing.JTable();
        insertNamePanel = new javax.swing.JPanel();
        insertNameLabel = new javax.swing.JLabel();
        writeNameTextField = new javax.swing.JTextField();
        addPlayerButton = new javax.swing.JButton();
        quitPlayerButton = new javax.swing.JButton();
        startGamePlayerButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 1980, 1080));
        setMinimumSize(new java.awt.Dimension(730, 561));
        setPreferredSize(new java.awt.Dimension(730, 561));
        setSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(null);

        clearPlayerNameButton.setBackground(new java.awt.Color(218, 177, 137));
        clearPlayerNameButton.setFont(new java.awt.Font("Cambria Math", 0, 17)); // NOI18N
        clearPlayerNameButton.setForeground(new java.awt.Color(255, 255, 255));
        clearPlayerNameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/image/clear.png"))); // NOI18N
        clearPlayerNameButton.setText("Clear");
        clearPlayerNameButton
                .setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                        new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        clearPlayerNameButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearPlayerNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPlayerNameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(clearPlayerNameButton);
        clearPlayerNameButton.setBounds(300, 440, 80, 30);

        jScrollPane1.setBorder(new javax.swing.border.MatteBorder(null));

        playerNameTable.setAutoCreateRowSorter(true);
        playerNameTable.setBackground(new java.awt.Color(218, 177, 137));
        playerNameTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Name"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] types = new Class[] {
                    java.lang.String.class
            };

            @SuppressWarnings("rawtypes")
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        playerNameTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        playerNameTable.setCellSelectionEnabled(true);
        playerNameTable.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        playerNameTable.setOpaque(false);
        jScrollPane1.setViewportView(playerNameTable);
        playerNameTable.getColumnModel().getSelectionModel()
                .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(190, 250, 300, 140);

        insertNamePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "INSERT PLAYER'S NAME",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Cambria Math", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        insertNamePanel.setAutoscrolls(true);
        insertNamePanel.setOpaque(false);
        insertNamePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        insertNameLabel.setFont(new java.awt.Font("Cambria Math", 0, 16)); // NOI18N
        insertNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        insertNameLabel.setText("Insert name :");
        insertNamePanel.add(insertNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        writeNameTextField.setBackground(java.awt.SystemColor.control);
        writeNameTextField.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        writeNameTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        writeNameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        writeNameTextField.setOpaque(true);
        writeNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeNameTextFieldActionPerformed(evt);
            }
        });
        insertNamePanel.add(writeNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 150, -1));

        addPlayerButton.setBackground(new java.awt.Color(218, 177, 137));
        addPlayerButton.setFont(new java.awt.Font("Cambria Math", 0, 17)); // NOI18N
        addPlayerButton.setForeground(new java.awt.Color(255, 255, 255));
        addPlayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/image/plus.png"))); // NOI18N
        addPlayerButton.setText("Add");
        addPlayerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        addPlayerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });
        insertNamePanel.add(addPlayerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 80, 30));

        getContentPane().add(insertNamePanel);
        insertNamePanel.setBounds(200, 100, 280, 130);

        quitPlayerButton.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        quitPlayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/image/logout.png"))); // NOI18N
        quitPlayerButton.setText("QUIT");
        quitPlayerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        quitPlayerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitPlayerButtonActionPerformed(evt);
            }
        });
        getContentPane().add(quitPlayerButton);
        quitPlayerButton.setBounds(20, 440, 80, 30);

        startGamePlayerButton.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        startGamePlayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/image/play.png"))); // NOI18N
        startGamePlayerButton.setText("START GAME");
        startGamePlayerButton
                .setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                        new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        startGamePlayerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(startGamePlayerButton);
        startGamePlayerButton.setBounds(570, 440, 140, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/image/Players.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-170, 0, 890, 520);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void writeNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_writeNameTextFieldActionPerformed

    }// GEN-LAST:event_writeNameTextFieldActionPerformed

    // restituisco l'input dei nomi dei Players
    public List<String> getNamePlayers() {

        for (int i = 0; i < playerNameTable.getRowCount(); i++) {
            this.names.add((String) playerNameTable.getValueAt(i, 0));
        }

        return names;
    }

    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addPlayerButtonActionPerformed

        if (writeNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your name!!", "Empty name",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (playerNameTable.getRowCount() < 4) {
                DefaultTableModel model = (DefaultTableModel) playerNameTable.getModel();
                model.addRow(new Object[] { writeNameTextField.getText() });
                writeNameTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Max number of players reached", "Full",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }// GEN-LAST:event_addPlayerButtonActionPerformed

    private void quitPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_quitPlayerButtonActionPerformed
        new StartGui().setVisible(true);
        this.dispose();
    }// GEN-LAST:event_quitPlayerButtonActionPerformed

    private void clearPlayerNameButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clearPlayerNameButtonActionPerformed
        int row = playerNameTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                    "No row is selected. Please select one row!",
                    "Select row", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel model = (DefaultTableModel) playerNameTable.getModel();
            model.removeRow(row);
        }

    }// GEN-LAST:event_clearPlayerNameButtonActionPerformed
}
