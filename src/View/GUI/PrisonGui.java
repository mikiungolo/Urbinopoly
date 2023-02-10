package View.GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Utente
 */
public class PrisonGui extends javax.swing.JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton rollPrisonButton;
    private javax.swing.JButton cautionPrisonButton;
    private javax.swing.JButton cardPrisonButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form PrisonGui
     */
    public PrisonGui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rollPrisonButton = new javax.swing.JButton();
        cautionPrisonButton = new javax.swing.JButton();
        cardPrisonButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rollPrisonButton.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        rollPrisonButton.setForeground(new java.awt.Color(102, 102, 102));
        rollPrisonButton.setText("Roll your luck");
        rollPrisonButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        rollPrisonButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.add(rollPrisonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, 40));

        cautionPrisonButton.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        cautionPrisonButton.setForeground(new java.awt.Color(51, 0, 0));
        cautionPrisonButton.setText("Pay € 125");
        cautionPrisonButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        cautionPrisonButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cautionPrisonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 99, 40));

        cardPrisonButton.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        cardPrisonButton.setForeground(new java.awt.Color(255, 51, 0));
        cardPrisonButton.setText("Use card");
        cardPrisonButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED,
                new java.awt.Color(204, 204, 204), new java.awt.Color(0, 0, 0), null, null));
        cardPrisonButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(cardPrisonButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 110, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/GUI/image/PrisonGui.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // getter e setter
    public javax.swing.JButton getRollPrisonButton() {
        return rollPrisonButton;
    }

    public void setRollPrisonButton(javax.swing.JButton rollPrisonButton) {
        this.rollPrisonButton = rollPrisonButton;
    }

    public javax.swing.JButton getCautionPrisonButton() {
        return cautionPrisonButton;
    }

    public void setCautionPrisonButton(javax.swing.JButton cautionPrisonButton) {
        this.cautionPrisonButton = cautionPrisonButton;
    }

    public javax.swing.JButton getCardPrisonButton() {
        return cardPrisonButton;
    }

    public void setCardPrisonButton(javax.swing.JButton cardPrisonButton) {
        this.cardPrisonButton = cardPrisonButton;
    }
}