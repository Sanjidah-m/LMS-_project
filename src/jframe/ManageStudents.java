/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;



import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jframe.DBConnection.con;

/**
 *
 * @author KIIT
 */
public class ManageStudents extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    
    String student_name,course,branch;
    int studentiD;
    DefaultTableModel model;
    
    public ManageStudents(){
        initComponents();
      setstudentDeatilstotable();
    }
   
    // to set the book details into the table
    public void setstudentDeatilstotable(){
        
       try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","Root!3152");
        Statement st = (Statement) con.createStatement();
        ResultSet rs = st.executeQuery("select * from students_details ");
        
        while(rs.next()){
            String StudentID = rs.getString("StudentID");
            String Student_name = rs.getString("name");
            String course = rs.getString("course");
            String Branch = rs.getString("branch");
            
            Object[] obj= { StudentID,Student_name,course,Branch};
            model = (DefaultTableModel) tbl_Studentdetails.getModel(); 
            model.addRow(obj);
        }
    }
    catch(ClassNotFoundException | SQLException e){
        e.printStackTrace();
    }
}
    //to add book to book detail table
    //ADD button
    public boolean addstudent(){
        boolean isAdded =false;
        studentiD= Integer.parseInt( txt_studentid.getText());
        student_name= txt_studentname.getText();
        course= combo_coursename.getSelectedItem().toString(); //selecting option from drop down option
        branch= combo_branch.getSelectedItem().toString(); // select the option and convert it into string
        
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "insert into students_details values(?,?,?,?)";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1, studentiD);
            pst.setString(2, student_name);
            pst.setString(3, course);
            pst.setString(4, branch);
            
            int rowCount = pst.executeUpdate();
            if(rowCount> 0){
                isAdded = true;
            } else{
                isAdded = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAdded;
    }
    
    
    //method to clear the table while its duplicating prev rec.
     public void clearTable(){
         DefaultTableModel model =(DefaultTableModel) tbl_Studentdetails.getModel();
         model.setRowCount(0);
     }
     
     //UPDATE button
     public boolean updatedstudent(){
        boolean isUpdated = false;
        studentiD= Integer.parseInt( txt_studentid.getText());
        student_name= txt_studentname.getText();
        course= combo_coursename.getSelectedItem().toString();
        branch= combo_branch.getSelectedItem().toString();
       
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update students_details set name = ?, course = ?, branch = ? where StudentID= ?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(4, studentiD);
            pst.setString(1, student_name);
            pst.setString(2, course);
            pst.setString(3, branch);
            
            int rowCount = pst.executeUpdate();
            if(rowCount> 0){
                isUpdated = true;
            } 
            else{
                isUpdated = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isUpdated;
     }
    
       //DELETE button
     public boolean deletestudent(){
        boolean isdelete = false;
       studentiD= Integer.parseInt( txt_studentid.getText());
        
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "delete from students_details where StudentID= ?";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1, studentiD);
           
            
            int rowCount = pst.executeUpdate();
            if(rowCount> 0){
                isdelete = true;
            } 
            else{
                isdelete = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isdelete;
     }

    
    /**
     *
     */
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        jLabel34 = new javax.swing.JLabel();
        txt_studentname = new app.bolivia.swing.JCTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        rSMaterialButtonCircle1 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        combo_branch = new javax.swing.JComboBox<>();
        combo_coursename = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Studentdetails = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("BACK");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 50));

        jLabel32.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Enter Student ID:");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        txt_studentid.setBackground(new java.awt.Color(0, 0, 204));
        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_studentid.setPlaceholder("Enter student ID");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        jPanel1.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 350, 50));

        jLabel34.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Enter Student Name:");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, -1, -1));

        txt_studentname.setBackground(new java.awt.Color(0, 0, 204));
        txt_studentname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txt_studentname.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_studentname.setPlaceholder("Enter Student Name:");
        jPanel1.add(txt_studentname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 350, 50));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 30, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Select course:");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 30, -1));

        jLabel37.setFont(new java.awt.Font("Segoe UI Historic", 0, 24)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Select Branch:");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 30, -1));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 30, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(255, 0, 0));
        rSMaterialButtonCircle1.setText("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 700, 150, 80));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 0, 0));
        rSMaterialButtonCircle2.setText("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 700, 150, 80));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 0, 0));
        rSMaterialButtonCircle3.setText("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 700, 150, 80));

        combo_branch.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CSE", "MSC", "Frictional", "Historic", "Architectural", "IT", "Electrial", "Civil" }));
        combo_branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_branchActionPerformed(evt);
            }
        });
        jPanel1.add(combo_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 580, 350, 60));

        combo_coursename.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        combo_coursename.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Btech", "Books", "BSE", "LAW" }));
        jPanel1.add(combo_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 470, 350, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 830));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 160, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 0, 102));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel2.setText("Manage Students");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        jPanel5.setBackground(new java.awt.Color(51, 0, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 490, 5));

        tbl_Studentdetails.setBackground(new java.awt.Color(204, 204, 204));
        tbl_Studentdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Course", "Branch"
            }
        ));
        tbl_Studentdetails.setColorBackgoundHead(new java.awt.Color(0, 0, 204));
        tbl_Studentdetails.setColorBordeFilas(new java.awt.Color(0, 0, 204));
        tbl_Studentdetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_Studentdetails.setColorFilasForeground1(new java.awt.Color(0, 0, 204));
        tbl_Studentdetails.setColorSelBackgound(new java.awt.Color(255, 0, 0));
        tbl_Studentdetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tbl_Studentdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tbl_Studentdetails.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tbl_Studentdetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semilight", 1, 20)); // NOI18N
        tbl_Studentdetails.setRowHeight(40);
        tbl_Studentdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_StudentdetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Studentdetails);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 1060, 290));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 1140, 830));

        setSize(new java.awt.Dimension(1724, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
    HomePage home= new HomePage();
    home.setVisible(true);
    dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost

    }//GEN-LAST:event_txt_studentidFocusLost

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
       if(deletestudent()== true){
              JOptionPane.showMessageDialog(this,"Student Deleted");
              clearTable();
              setstudentDeatilstotable();
          }else{
              JOptionPane.showMessageDialog(this,"Student detail failed to be deleted");
          } 
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
          if(addstudent()== true){
              JOptionPane.showMessageDialog(this,"Student Added");
              clearTable();
             setstudentDeatilstotable();
          }else{
              JOptionPane.showMessageDialog(this,"Student detail failed to be Added");
          }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
         if(updatedstudent()== true){
              JOptionPane.showMessageDialog(this,"Student Updated");
              clearTable();
              setstudentDeatilstotable();
          }else{
              JOptionPane.showMessageDialog(this,"Student failed to be Updated");
          }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
    System.exit(0);      
    }//GEN-LAST:event_jLabel3MouseClicked

    private void combo_branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_branchActionPerformed

    private void tbl_StudentdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_StudentdetailsMouseClicked
      int rowNo = tbl_Studentdetails.getSelectedRow();
        TableModel model = tbl_Studentdetails.getModel();
        
        txt_studentid.setText(model.getValueAt(rowNo, 0).toString());
       txt_studentname.setText(model.getValueAt(rowNo, 1).toString());
       combo_coursename.setSelectedItem(model.getValueAt(rowNo, 2).toString());
       combo_branch.setSelectedItem(model.getValueAt(rowNo, 3).toString());
       
    }//GEN-LAST:event_tbl_StudentdetailsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JComboBox<String> combo_coursename;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojerusan.RSTableMetro tbl_Studentdetails;
    private app.bolivia.swing.JCTextField txt_studentid;
    private app.bolivia.swing.JCTextField txt_studentname;
    // End of variables declaration//GEN-END:variables
}
