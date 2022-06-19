/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasemanager;

import database.DbManager;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author vusal
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    private Connection connection;
    public Home() {
        initComponents();
    }
    
    public Home(Connection connection){
        this.connection = connection;
        initComponents();
        setSchema();
        setResultArea();
        setButton();
    }
    
    public void setResultArea(){
        RESULT_TEXT.setEditable(false);
    }
    
    public void setButton(){
        RUN_QUERY_BTN.setBackground(Color.GREEN);
        RUN_QUERY_BTN.setOpaque(true);
        RUN_QUERY_BTN.setBorderPainted(false);
        RUN_QUERY_BTN.setForeground(Color.WHITE);
    }
 
    public void setSchema(){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Schemas");
        String query = "show schemas";
        try (PreparedStatement ps = connection.prepareStatement(query);ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                DefaultMutableTreeNode tree = new DefaultMutableTreeNode(rs.getString("Database"));
                PreparedStatement p = connection.prepareCall("SHOW TABLES FROM " + rs.getString("Database"));
                ResultSet resultSet = p.executeQuery();
                while(resultSet.next()){
                    tree.add(new DefaultMutableTreeNode(resultSet.getString("Tables_in_"+rs.getString("Database"))));
                }
                rootNode.add(tree);
            }
            DefaultTreeModel model = (DefaultTreeModel) SCHEMA_TREE.getModel();
            model.setRoot(rootNode);
            SCHEMA_TREE.setModel(model);
            
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String[] listToArray(List<String> list){
        String[] arr = new String[list.size()];
        for(int i = 0;i<list.size();i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SCHEMA_TREE = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SQL_EDITOR = new javax.swing.JEditorPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        RESULT_TABLE = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        REFRESH_BTN = new javax.swing.JButton();
        RUN_QUERY_BTN = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        RESULT_TEXT = new javax.swing.JTextArea();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setViewportView(SCHEMA_TREE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        SQL_EDITOR.setContentType("text/sql"); // NOI18N
        SQL_EDITOR.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        SQL_EDITOR.setForeground(new java.awt.Color(0, 0, 204));
        jScrollPane1.setViewportView(SQL_EDITOR);

        RESULT_TABLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(RESULT_TABLE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        REFRESH_BTN.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        REFRESH_BTN.setText("Refresh");
        REFRESH_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REFRESH_BTNActionPerformed(evt);
            }
        });

        RUN_QUERY_BTN.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        RUN_QUERY_BTN.setText("Run Query");
        RUN_QUERY_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RUN_QUERY_BTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(REFRESH_BTN, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RUN_QUERY_BTN, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(REFRESH_BTN, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(RUN_QUERY_BTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        RESULT_TEXT.setBackground(new java.awt.Color(0, 0, 0));
        RESULT_TEXT.setColumns(20);
        RESULT_TEXT.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        RESULT_TEXT.setForeground(new java.awt.Color(51, 255, 0));
        RESULT_TEXT.setLineWrap(true);
        RESULT_TEXT.setRows(5);
        jScrollPane6.setViewportView(RESULT_TEXT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void REFRESH_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REFRESH_BTNActionPerformed
        setSchema();
    }//GEN-LAST:event_REFRESH_BTNActionPerformed

    private void RUN_QUERY_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RUN_QUERY_BTNActionPerformed
        String query = SQL_EDITOR.getText();
        boolean success = executeQuery(query);
        if(success){
            RESULT_TEXT.setText("Query executed successfully!");
            RESULT_TEXT.setForeground(Color.decode("#18AD2F"));
        }else{
           RESULT_TEXT.setText("Error occur when query executing!");
           RESULT_TEXT.setForeground(Color.red); 
        }
    }//GEN-LAST:event_RUN_QUERY_BTNActionPerformed

    public boolean executeQuery(String query){
        Connection con = DbManager.getConnection();
        try{
            if(SCHEMA_TREE.getLastSelectedPathComponent() != null){
                String schema = SCHEMA_TREE.getLastSelectedPathComponent().toString();
                con = DbManager.getConnection(schema);
            }
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if(rs!=null){
             setResultTable(rs);   
            }
            con.close();
            return true;
            
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public void setResultTable(ResultSet res) throws Exception{
            RESULT_TABLE.setModel(DbUtils.resultSetToTableModel(res));
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton REFRESH_BTN;
    private javax.swing.JTable RESULT_TABLE;
    private javax.swing.JTextArea RESULT_TEXT;
    private javax.swing.JButton RUN_QUERY_BTN;
    private javax.swing.JTree SCHEMA_TREE;
    private javax.swing.JEditorPane SQL_EDITOR;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    // End of variables declaration//GEN-END:variables
}