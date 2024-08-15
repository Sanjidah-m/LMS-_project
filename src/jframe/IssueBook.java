/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author KIIT
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }

    //to fetch book details from the database and show it here
    public void getbookDetails() {
        int bookId = Integer.parseInt(txt_bookid.getText());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where BookID = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ibl_bookid.setText(rs.getString("BookID"));
                ibl_bookname.setText(rs.getString("Book_name"));
                ibl_author.setText(rs.getString("Author"));
                ibl_quantity.setText(rs.getString("quantity"));
            } else {
                ibl_bookerror.setText("Invalid Book ID");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //to fetch Student details from the database and show it here
    public void getStudentDetails() {
        int studentId = Integer.parseInt(txt_studentid.getText());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from students_details where StudentID = ?");
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ibl_studentid.setText(rs.getString("StudentID"));
                ibl_name.setText(rs.getString("name"));
                ibl_course.setText(rs.getString("course"));
                ibl_branch.setText(rs.getString("branch"));
            } else {
                ibl_studenterror.setText("Invalid Student ID");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Insert Issue BOOK details to database
    public boolean issuebook() {
        boolean isIssued = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());
        String bookName = ibl_bookname.getText();
        String studentName = ibl_name.getText();

        Date uissueDate = date_issuedate.getDatoFecha();
        Date udueDate = date_duedate.getDatoFecha();

        long l1 = uissueDate.getTime();
        long l2 = udueDate.getTime();

        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into issue_book_deatils(book_ID,book_name,student_id,name," + "issue_date,due_date,status) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, studentId);
            pst.setString(4, studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "Pending");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isIssued = true;
            } else {
                isIssued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isIssued;
    }

    // updating Count or quantity of book after issuing
    public void updateBookCount() {
        int bookId = Integer.parseInt(txt_bookid.getText());
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity-1 where BookID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book Count Updated");
                int initialCount = Integer.parseInt(ibl_quantity.getText());
                ibl_quantity.setText(Integer.toString(initialCount - 1));
            } else {
                JOptionPane.showMessageDialog(this, "Book Count failed to be Updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //duplication 
    public boolean isAlreadyissued() {
        boolean isAlreadyissued = false;
        int bookId = Integer.parseInt(txt_bookid.getText());
        int studentId = Integer.parseInt(txt_studentid.getText());
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_deatils where book_ID =? AND student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");

            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                isAlreadyissued = true;
            } else {
                isAlreadyissued = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAlreadyissued;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ibl_branch = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ibl_studentid = new javax.swing.JLabel();
        ibl_name = new javax.swing.JLabel();
        ibl_course = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ibl_studenterror = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_studentid = new app.bolivia.swing.JCTextField();
        date_issuedate = new rojeru_san.componentes.RSDateChooser();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        date_duedate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        ibl_quantity = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ibl_bookid = new javax.swing.JLabel();
        ibl_bookname = new javax.swing.JLabel();
        ibl_author = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ibl_bookerror = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 280, 5));

        ibl_branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_branch.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ibl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 190, 40));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student ID:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Name:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Branch:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

        ibl_studentid.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_studentid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ibl_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 190, 40));

        ibl_name.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ibl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 190, 40));

        ibl_course.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ibl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, 190, 40));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel4.setText("Student Details");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        ibl_studenterror.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_studenterror.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(ibl_studenterror, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, 230, 50));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 420, 810));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        panel_main.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel2.setText("Issue Book");
        panel_main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        panel_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 430, 5));

        jLabel32.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 51, 51));
        jLabel32.setText("Book ID:");
        panel_main.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 270, -1, -1));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txt_bookid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookid.setPlaceholder("Enter Book id..");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        panel_main.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 260, 360, 30));

        jLabel33.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 51, 51));
        jLabel33.setText("Student ID:");
        panel_main.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 340, -1, -1));

        txt_studentid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txt_studentid.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_studentid.setPlaceholder("Enter Student id..");
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        panel_main.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 330, 360, 30));

        date_issuedate.setColorBackground(new java.awt.Color(255, 0, 0));
        date_issuedate.setColorButtonHover(new java.awt.Color(255, 102, 102));
        date_issuedate.setPlaceholder("Select Issue date.");
        panel_main.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 440, 360, -1));

        jLabel34.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 51, 51));
        jLabel34.setText("Issue Date:");
        panel_main.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 450, -1, -1));

        jLabel35.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 51));
        jLabel35.setText("Due Date:");
        panel_main.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 530, -1, -1));

        date_duedate.setColorBackground(new java.awt.Color(255, 0, 0));
        date_duedate.setColorButtonHover(new java.awt.Color(255, 102, 102));
        date_duedate.setPlaceholder("Select Due date.");
        panel_main.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 520, 360, -1));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 0, 0));
        rSMaterialButtonCircle2.setText("Issue Book");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 600, 460, 80));

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel12.setText("BACK");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 50));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel13.setText("Book Details");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 280, 5));

        ibl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(ibl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 190, 40));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book ID:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Book Name:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Author:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, -1));

        ibl_bookid.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_bookid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(ibl_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 190, 40));

        ibl_bookname.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_bookname.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(ibl_bookname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 190, 40));

        ibl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(ibl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, 190, 40));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Quantity:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, -1, -1));

        ibl_bookerror.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        ibl_bookerror.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(ibl_bookerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 220, 50));

        panel_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 810));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 800));

        setSize(new java.awt.Dimension(1411, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        if (!txt_bookid.getText().equals("")) {
            getbookDetails();
        }
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        if (!txt_studentid.getText().equals("")) {
            getStudentDetails();
        }
    }//GEN-LAST:event_txt_studentidFocusLost

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (ibl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Book isnt available");
        } else {

            if (isAlreadyissued() == false) {
                if (issuebook() == true) {
                    JOptionPane.showMessageDialog(this, "Book issued succefully");
                    updateBookCount();
                } else {
                    JOptionPane.showMessageDialog(this, "Book issued failed");
                }
            } else {
                JOptionPane.showMessageDialog(this, "this Student already has the book");
            }

        }


    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

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
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_duedate;
    private rojeru_san.componentes.RSDateChooser date_issuedate;
    private javax.swing.JLabel ibl_author;
    private javax.swing.JLabel ibl_bookerror;
    private javax.swing.JLabel ibl_bookid;
    private javax.swing.JLabel ibl_bookname;
    private javax.swing.JLabel ibl_branch;
    private javax.swing.JLabel ibl_course;
    private javax.swing.JLabel ibl_name;
    private javax.swing.JLabel ibl_quantity;
    private javax.swing.JLabel ibl_studenterror;
    private javax.swing.JLabel ibl_studentid;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel panel_main;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
