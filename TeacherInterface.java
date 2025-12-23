/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author KABIR BALOCH
 */
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class TeacherInterface extends javax.swing.JFrame {
 private String teacherEmail; 
 private String loggedTeacherName;
 /**
     * Creates new form TeacherInterface
     * @param email
     */
   public TeacherInterface(String email) {
        this.teacherEmail = email;
        this.loggedTeacherName = getTeacherNameFromEmail(email);
  
        initComponents();
        SetImage();
        InputStudentSap.addActionListener(e -> SearchButton.doClick());
        Deletebutton.setVisible(false);
       Delete_Panel.setVisible(false);
    Rooms_of_List.setVisible(false);
        ShowData.setVisible(false);
            SearchButton.setVisible(false);
        InputStudentSap.setVisible(false);
        InputStudentName.setVisible(false);
        arrangepanel.setVisible(true);  
        Student_Panel.setVisible(false);
     Building_List.setVisible(false);
        SearchByMultiple.setVisible(false);
    // Inside main JFrame class
    Deletebutton.addActionListener(e -> {
        String building = Building_List.getSelectedItem().toString();
    String room = Rooms_of_List.getSelectedItem().toString();
    
            try {
                deleteRoomArrangement(building, room);
            } catch (SQLException ex) {
                Logger.getLogger(TeacherInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
});

    Rooms_of_List.addActionListener((e) -> {
    String building = (String) Building_List.getSelectedItem();
String room = (String) Rooms_of_List.getSelectedItem();

    if (building != null && room != null &&
        !building.equals("Select Building") &&
        !room.equals("Select Room")) {

        loadDetails(room);
    }
});

    Building_List.addActionListener((e) -> {
    String building = (String) Building_List.getSelectedItem();
    if (building != null && !building.equals("Select Building")) {
        loadRooms(building);
    }
});

navigate_to_arrange.addActionListener((ActionEvent e) -> {
            saplogo.setVisible(true);
    Deletebutton.setVisible(false);   
    InputStudentSap.setVisible(false);
    InputStudentName.setVisible(false);
    jLabel4.setVisible(true);
    mainpanel.setVisible(true);
    arrangepanel.setVisible(true);
   Student_Panel.setVisible(false);
   SearchButton.setVisible(false);
   ShowData.setVisible(false);
    Rooms_of_List.setVisible(false);
    Building_List.setVisible(false);
   jComboBox1.setVisible(true);
        });
navigation_to_delete.addActionListener((ActionEvent e) -> {
   loadBuildings();
           saplogo.setVisible(true);
   jComboBox1.setVisible(false);
    Deletebutton.setVisible(true);
    Delete_Panel.setVisible(true);
          Student_Panel.setVisible(false);
   SearchButton.setVisible(false);
   ShowData.setVisible(false);
InputStudentSap.setVisible(false);
    InputStudentName.setVisible(false);
SearchBySingle.setVisible(false);
SearchByMultiple.setVisible(false);
        SearchByFirstRows.setVisible(false);
    mainpanel.setVisible(false); 
 Rooms_of_List.setVisible(true);
    Building_List.setVisible(true);
});
VerifyStudentsSeats.addActionListener(e -> {
    loadRooms();
           saplogo.setVisible(true);
    jComboBox1.setVisible(false);
    Deletebutton.setVisible(false);
    Rooms_of_List.setVisible(false);
    Building_List.setVisible(false);
    InputStudentName.setVisible(true);
    mainpanel.setVisible(false); 
    arrangepanel.setVisible(false);
   Student_Panel.setVisible(true);
SearchBySingle.setVisible(false);
SearchByMultiple.setVisible(false);
        SearchByFirstRows.setVisible(false);
});

        
    list_of_building.addActionListener((var e) -> {
selectedBuilding = (String) list_of_building.getSelectedItem();
list_of_rooms.removeAllItems();

if (selectedBuilding == null) return;

// Map combo box selection to actual table names in DB
String tableName = switch (selectedBuilding) {
    case "Building 1" -> "building_one_new";
    case "Building 2" -> "building_two";
    case "Building 3" -> "building_three";
    case "Building 4" -> "building_four";
    default -> "";
};

if (tableName.isEmpty()) return;

try (Connection con = connectDB()) {
String query = "SELECT RoomNames FROM " + tableName;
PreparedStatement ps = con.prepareStatement(query);
ResultSet rs = ps.executeQuery();
while (rs.next()) {
list_of_rooms.addItem(rs.getString("RoomNames"));
}

} catch (Exception ex) {
JOptionPane.showMessageDialog(null, "Error loading rooms: " + ex.getMessage());
}
        });
    list_of_rooms.addActionListener((java.awt.event.ActionEvent e) -> {
    selectedRoom = (String) list_of_rooms.getSelectedItem();
    if (selectedRoom == null || selectedBuilding == null) return;

    String tableName = switch (selectedBuilding) {
        case "Building 1" -> "building_one_new";
        case "Building 2" -> "building_two";
        case "Building 3" -> "building_three";
        case "Building 4" -> "building_four";
        default -> "";
    };

    if (tableName.isEmpty()) return;
   
    try (Connection con = connectDB()) {
   
        String query = "SELECT RoomCapacity, Available FROM " + tableName + " WHERE RoomNames = ?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1, selectedRoom);
ResultSet rs = ps.executeQuery();


        if (rs.next()) {
            selectedCapacity = rs.getInt("RoomCapacity");
            isAvailable = rs.getInt("Available");

            // Always show the room capacity in your text field
            CapacityShow.setText(String.valueOf(selectedCapacity));

            // Only show a popup if the room is not available
            if (isAvailable == 0) {
                JOptionPane.showMessageDialog(this, "Room is not available!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "No room data found!");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching room details: " + ex.getMessage());
    }
});
jComboBox1.addActionListener(e -> {
    String selectedSection = (String) jComboBox1.getSelectedItem();
    if (selectedSection == null || selectedSection.isEmpty()) return;

    // Clean up the string if it has quotes like Section 'A'
    selectedSection = selectedSection.replace("Section", "").replace("'", "").trim();
if (selectedSection.contains("'")) {
    selectedSection = selectedSection.substring(selectedSection.indexOf("'") + 1, selectedSection.lastIndexOf("'"));
}
    try (Connection con = StudentsconnectDB()) {
        if (con == null) return;

        // Assuming table name is 'students' and it has columns: Section, TotalStudents
        String query = "SELECT COUNT(*) AS total FROM students WHERE Section = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, selectedSection);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int totalStudents = rs.getInt("total");
            TotalStudents.setText(String.valueOf(totalStudents));
        } else {
            TotalStudents.setText("0");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching section data: " + ex.getMessage());
    }
}); 
   }

    public String getTeacherEmail() {
        return teacherEmail;
    
    
    }
    private String selectedBuilding;
private String selectedRoom;
private int selectedCapacity;
private int isAvailable;

    public TeacherInterface() {
        initComponents();
        SetImage();
            saplogo.setVisible(true);
        Rooms_of_List.setVisible(false);
   Deletebutton.setVisible(false);
        navigation_to_delete.setVisible(true);
       Delete_Panel.setVisible(false);
        ShowData.setVisible(false);
            SearchButton.setVisible(false);
        InputStudentSap.setVisible(false);
        InputStudentName.setVisible(false);
        arrangepanel.setVisible(true);  
        Student_Panel.setVisible(false);
        SearchByMultiple.setVisible(false);
        Building_List.setVisible(false);
ShowArrangedBy.setVisible(false);
ShowArrangedSection.setVisible(false);
    
// Inside main JFrame class

    Rooms_of_List.addActionListener((e) -> {
    String building = (String) Rooms_of_List.getSelectedItem();
    String room = (String) Rooms_of_List.getSelectedItem();

    if (building != null && room != null &&
        !building.equals("Select Building") &&
        !room.equals("Select Room")) {

        loadDetails(room);
    }
});

    Building_List.addActionListener((e) -> {
    String building = (String) Building_List.getSelectedItem();
    if (building != null && !building.equals("Select Building")) {
        loadRooms(building);
    }
});
navigation_to_delete.addActionListener((ActionEvent e) -> {
     loadBuildings();
             saplogo.setVisible(true);
     Deletebutton.setVisible(true);
     Rooms_of_List.setVisible(true);
   Building_List.setVisible(true);   
    Delete_Panel.setVisible(true);
          Student_Panel.setVisible(false);
   SearchButton.setVisible(false);
   ShowData.setVisible(false);
InputStudentSap.setVisible(false);
    InputStudentName.setVisible(false);
SearchBySingle.setVisible(false);
SearchByMultiple.setVisible(false);
        SearchByFirstRows.setVisible(false);
    mainpanel.setVisible(false); 
ShowArrangedBy.setVisible(true);
ShowArrangedSection.setVisible(true);

});

    navigate_to_arrange.addActionListener((ActionEvent e) -> {
             saplogo.setVisible(true);
        Deletebutton.setVisible(false);
        InputStudentSap.setVisible(false);
         Rooms_of_List.setVisible(false);
   Building_List.setVisible(false);
    InputStudentName.setVisible(false);
    jLabel4.setVisible(true);
    mainpanel.setVisible(true);
    arrangepanel.setVisible(true);
   Student_Panel.setVisible(false);
   SearchButton.setVisible(false);
   ShowData.setVisible(false);
ShowArrangedBy.setVisible(false);
ShowArrangedSection.setVisible(false);

    });
    

VerifyStudentsSeats.addActionListener(e -> {
            saplogo.setVisible(true);
    loadRooms();
ShowArrangedBy.setVisible(false);
ShowArrangedSection.setVisible(false);
Deletebutton.setVisible(false);
    Rooms_of_List.setVisible(false);
   Building_List.setVisible(false);
    InputStudentName.setVisible(true);
    mainpanel.setVisible(false); 
    arrangepanel.setVisible(false);
   Student_Panel.setVisible(true);
SearchBySingle.setVisible(false);
SearchByMultiple.setVisible(false);
        SearchByFirstRows.setVisible(false);
});

        
    list_of_building.addActionListener((var e) -> {
selectedBuilding = (String) list_of_building.getSelectedItem();
list_of_rooms.removeAllItems();

if (selectedBuilding == null) return;

// Map combo box selection to actual table names in DB
String tableName = switch (selectedBuilding) {
    case "Building 1" -> "building_one_new";
    case "Building 2" -> "building_two";
    case "Building 3" -> "building_three";
    case "Building 4" -> "building_four";
    default -> "";
};

if (tableName.isEmpty()) return;

try (Connection con = connectDB()) {
String query = "SELECT RoomNames FROM " + tableName;
PreparedStatement ps = con.prepareStatement(query);
ResultSet rs = ps.executeQuery();
while (rs.next()) {
list_of_rooms.addItem(rs.getString("RoomNames"));
}

} catch (Exception ex) {
JOptionPane.showMessageDialog(null, "Error loading rooms: " + ex.getMessage());
}
        });
    Building_List.addActionListener((java.awt.event.ActionEvent e) -> {
        selectedBuilding = (String) list_of_building.getSelectedItem();
        list_of_rooms.removeAllItems();
        
        if (selectedBuilding == null) return;
        
// Map combo box selection to actual table names in DB
String tableName;
tableName = switch (selectedBuilding) {
    case "Building 1" -> "building_one_new";
    case "Building 2" -> "building_two";
    case "Building 3" -> "building_three";
    case "Building 4" -> "building_four";
    default -> "";};
        });
    list_of_rooms.addActionListener((java.awt.event.ActionEvent e) -> {
    selectedRoom = (String) list_of_rooms.getSelectedItem();
    if (selectedRoom == null || selectedBuilding == null) return;

    String tableName = switch (selectedBuilding) {
        case "Building 1" -> "building_one_new";
        case "Building 2" -> "building_two";
        case "Building 3" -> "building_three";
        case "Building 4" -> "building_four";
        default -> "";
    };

    if (tableName.isEmpty()) return;
   
    try (Connection con = connectDB()) {
   
        String query = "SELECT RoomCapacity, Available FROM " + tableName + " WHERE RoomNames = ?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1, selectedRoom);
ResultSet rs = ps.executeQuery();


        if (rs.next()) {
            selectedCapacity = rs.getInt("RoomCapacity");
            isAvailable = rs.getInt("Available");

            // Always show the room capacity in your text field
            CapacityShow.setText(String.valueOf(selectedCapacity));

            // Only show a popup if the room is not available
            if (isAvailable == 0) {
                JOptionPane.showMessageDialog(this, "Room is not available!");
            }

        } else {
            JOptionPane.showMessageDialog(this, "No room data found!");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching room details: " + ex.getMessage());
    }
});
jComboBox1.addActionListener(e -> {
    String selectedSection = (String) jComboBox1.getSelectedItem();
    if (selectedSection == null || selectedSection.isEmpty()) return;

    // Clean up the string if it has quotes like Section 'A'
    selectedSection = selectedSection.replace("Section", "").replace("'", "").trim();
if (selectedSection.contains("'")) {
    selectedSection = selectedSection.substring(selectedSection.indexOf("'") + 1, selectedSection.lastIndexOf("'"));
}
    try (Connection con = StudentsconnectDB()) {
        if (con == null) return;

        // Assuming table name is 'students' and it has columns: Section, TotalStudents
        String query = "SELECT COUNT(*) AS total FROM students WHERE Section = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, selectedSection);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int totalStudents = rs.getInt("total");
            TotalStudents.setText(String.valueOf(totalStudents));
        } else {
            TotalStudents.setText("0");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching section data: " + ex.getMessage());
    }
});


    }
    private String getTeacherNameFromEmail(String email) {
    if (email == null || !email.contains("@")) return null;
    return email.split("@")[0];   // "kabeerahmedfaculty"
}
private Connection StudentsconnectDB() {
    try {
        String url = "jdbc:mysql://localhost:3306/students_database?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = ""; // if you set one, put it here
        return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Login database connection failed: " + e.getMessage());
        return null;
    }
}
    public void load(){
        
        ShowData.setVisible(false);
            SearchButton.setVisible(false);
        InputStudentSap.setVisible(false);
        InputStudentName.setVisible(false);
        arrangepanel.setVisible(true);  
        Student_Panel.setVisible(false);
        SearchByMultiple.setVisible(false);
    // Inside main JFrame class
navigate_to_arrange.addActionListener((ActionEvent e) -> {
        InputStudentSap.setVisible(false);
    InputStudentName.setVisible(false);
    jLabel4.setVisible(true);
    mainpanel.setVisible(true);
    arrangepanel.setVisible(true);
   Student_Panel.setVisible(false);
   SearchButton.setVisible(false);
   ShowData.setVisible(false);
        });

VerifyStudentsSeats.addActionListener(e -> {
    loadRooms();

    InputStudentName.setVisible(true);
    mainpanel.setVisible(false); 
    arrangepanel.setVisible(false);
   Student_Panel.setVisible(true);
SearchBySingle.setVisible(false);
SearchByMultiple.setVisible(false);
        SearchByFirstRows.setVisible(false);
});}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        navigate_to_arrange = new javax.swing.JButton();
        VerifyStudentsSeats = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        navigation_to_delete = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        arrangepanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        list_of_building = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        CapacityShow = new javax.swing.JTextField();
        list_of_rooms = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TotalStudents = new javax.swing.JTextField();
        ArrangeSeats = new javax.swing.JButton();
        saplogo = new javax.swing.JLabel();
        Student_Panel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        roomcombobox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        SearchBySingle = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        InputStudentName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        InputStudentSap = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ShowData = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        SearchByFirstRows = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ShowFirstFiveStudents = new javax.swing.JTextArea();
        ShowFirstRows = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        SearchByMultiple = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        InputStudentsNames = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ShowOutput = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        Delete_Panel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        Building_List = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        Rooms_of_List = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        ShowArrangedBy = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        ShowArrangedSection = new javax.swing.JTextField();
        Deletebutton = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        navigate_to_arrange.setBackground(new java.awt.Color(204, 204, 204));
        navigate_to_arrange.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        navigate_to_arrange.setText("ArrangeSeats");
        navigate_to_arrange.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        navigate_to_arrange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        navigate_to_arrange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigate_to_arrangeActionPerformed(evt);
            }
        });
        jPanel1.add(navigate_to_arrange, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 120, 30));

        VerifyStudentsSeats.setBackground(new java.awt.Color(204, 204, 204));
        VerifyStudentsSeats.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        VerifyStudentsSeats.setText("VerifyStudentSeats");
        VerifyStudentsSeats.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        VerifyStudentsSeats.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VerifyStudentsSeats.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        VerifyStudentsSeats.setPreferredSize(new java.awt.Dimension(91, 24));
        VerifyStudentsSeats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerifyStudentsSeatsActionPerformed(evt);
            }
        });
        jPanel1.add(VerifyStudentsSeats, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, 30));

        jComboBox1.setBackground(new java.awt.Color(204, 204, 204));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Section 'A'", "Section 'B'", "Section 'C'" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 120, 30));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel7.setText(" NAVIGATION BUTTONS");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 30));

        navigation_to_delete.setBackground(new java.awt.Color(204, 204, 204));
        navigation_to_delete.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        navigation_to_delete.setText("DeleteArrangeseats");
        navigation_to_delete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        navigation_to_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                navigation_to_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(navigation_to_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, 30));

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("LOG OUT");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 120, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 140, 370));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 24)); // NOI18N
        jLabel1.setText("         WELCOME TO SEAT ARRANGEMENT SYSTEM");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 650, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 710, 70));

        mainpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        mainpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        arrangepanel.setBackground(new java.awt.Color(153, 153, 153));
        arrangepanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel4.setText("Select Building :");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        arrangepanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 50));
        jLabel4.getAccessibleContext().setAccessibleParent(mainpanel);

        list_of_building.setBackground(new java.awt.Color(204, 204, 204));
        list_of_building.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        list_of_building.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Building 1", "Building 2", "Building 3", "Building 4" }));
        list_of_building.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        list_of_building.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        list_of_building.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                list_of_buildingItemStateChanged(evt);
            }
        });
        list_of_building.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_of_buildingActionPerformed(evt);
            }
        });
        list_of_building.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                list_of_buildingKeyPressed(evt);
            }
        });
        arrangepanel.add(list_of_building, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 100, 30));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel3.setText("Capacity of Room :");
        arrangepanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, 50));

        CapacityShow.setEditable(false);
        CapacityShow.setBackground(new java.awt.Color(204, 204, 204));
        CapacityShow.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CapacityShow.setText("0");
        CapacityShow.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        arrangepanel.add(CapacityShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 50, 30));

        list_of_rooms.setBackground(new java.awt.Color(204, 204, 204));
        list_of_rooms.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        list_of_rooms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RoomNames" }));
        list_of_rooms.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        list_of_rooms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_of_roomsActionPerformed(evt);
            }
        });
        arrangepanel.add(list_of_rooms, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 120, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("Select Room :");
        arrangepanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 90, 30));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setText("Total Students In This Section :");
        arrangepanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 220, 40));

        TotalStudents.setEditable(false);
        TotalStudents.setBackground(new java.awt.Color(204, 204, 204));
        TotalStudents.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotalStudents.setText("0");
        TotalStudents.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        arrangepanel.add(TotalStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 60, 30));

        ArrangeSeats.setBackground(new java.awt.Color(153, 153, 153));
        ArrangeSeats.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        ArrangeSeats.setText("ArrangeSeats");
        ArrangeSeats.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 2, true));
        ArrangeSeats.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ArrangeSeats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArrangeSeatsActionPerformed(evt);
            }
        });
        arrangepanel.add(ArrangeSeats, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 213, 170, 70));
        arrangepanel.add(saplogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 40, 30));

        mainpanel.add(arrangepanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 370));
        arrangepanel.getAccessibleContext().setAccessibleParent(arrangepanel);

        getContentPane().add(mainpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 710, 370));

        Student_Panel.setBackground(new java.awt.Color(153, 153, 153));
        Student_Panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        Student_Panel.setMinimumSize(new java.awt.Dimension(450, 250));
        Student_Panel.setPreferredSize(new java.awt.Dimension(450, 250));
        Student_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(153, 153, 153));
        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("SELECT ROOMS :");
        Student_Panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        roomcombobox.setBackground(new java.awt.Color(153, 153, 153));
        roomcombobox.setFont(new java.awt.Font("Segoe UI Semilight", 2, 14)); // NOI18N
        roomcombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "see rooms" }));
        Student_Panel.add(roomcombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 120, -1));

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel12.setText("SELECT OPTIONS :");
        Student_Panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 140, 50));

        jComboBox2.setBackground(new java.awt.Color(153, 153, 153));
        jComboBox2.setFont(new java.awt.Font("Segoe UI Semibold", 2, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "See First Five(5) Students in a Selected Room", "Search For Single Student with Name and Sap", "Search For Multiple Students with Names and Saps" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        Student_Panel.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 280, 30));

        SearchBySingle.setBackground(new java.awt.Color(153, 153, 153));
        SearchBySingle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setText("Enter Student FullName :");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchBySingle.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 59));

        InputStudentName.setBackground(new java.awt.Color(204, 204, 204));
        InputStudentName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        InputStudentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputStudentNameActionPerformed(evt);
            }
        });
        SearchBySingle.add(InputStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 150, 40));

        jLabel9.setBackground(new java.awt.Color(153, 153, 153));
        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel9.setText("Enter Sap_ID :");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchBySingle.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, 50));

        InputStudentSap.setBackground(new java.awt.Color(204, 204, 204));
        InputStudentSap.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        SearchBySingle.add(InputStudentSap, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 150, 40));

        SearchButton.setBackground(new java.awt.Color(153, 153, 153));
        SearchButton.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        SearchButton.setText("SEARCH");
        SearchButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });
        SearchBySingle.add(SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 130, 70));

        ShowData.setEditable(false);
        ShowData.setBackground(new java.awt.Color(204, 204, 204));
        ShowData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        ShowData.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jScrollPane2.setViewportView(ShowData);
        ShowData.getAccessibleContext().setAccessibleParent(SearchBySingle);

        SearchBySingle.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 150, 50));

        jLabel10.setBackground(new java.awt.Color(153, 153, 153));
        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel10.setText("Here is your Data :");
        SearchBySingle.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 190, 60));
        SearchBySingle.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, 40, 30));

        Student_Panel.add(SearchBySingle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 670, 250));

        SearchByFirstRows.setBackground(new java.awt.Color(153, 153, 153));
        SearchByFirstRows.setPreferredSize(new java.awt.Dimension(620, 240));
        SearchByFirstRows.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setBackground(new java.awt.Color(153, 153, 153));
        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        jLabel13.setText("Here Is First 5 Students in Selected Room :");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchByFirstRows.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 40));

        ShowFirstFiveStudents.setEditable(false);
        ShowFirstFiveStudents.setBackground(new java.awt.Color(204, 204, 204));
        ShowFirstFiveStudents.setColumns(20);
        ShowFirstFiveStudents.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        ShowFirstFiveStudents.setRows(5);
        ShowFirstFiveStudents.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        ShowFirstFiveStudents.setSelectionColor(new java.awt.Color(153, 153, 153));
        jScrollPane1.setViewportView(ShowFirstFiveStudents);

        SearchByFirstRows.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 310, 100));

        ShowFirstRows.setBackground(new java.awt.Color(153, 153, 153));
        ShowFirstRows.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        ShowFirstRows.setText("SHOW");
        ShowFirstRows.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        ShowFirstRows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowFirstRowsActionPerformed(evt);
            }
        });
        SearchByFirstRows.add(ShowFirstRows, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 160, 50));
        SearchByFirstRows.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 40, 30));
        SearchByFirstRows.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 40, 30));

        Student_Panel.add(SearchByFirstRows, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 670, 250));

        SearchByMultiple.setBackground(new java.awt.Color(153, 153, 153));
        SearchByMultiple.setPreferredSize(new java.awt.Dimension(610, 240));
        SearchByMultiple.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setBackground(new java.awt.Color(153, 153, 153));
        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        jLabel14.setText("Enter multiple records (FullName, SAP_ID per line):");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SearchByMultiple.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 430, 54));

        InputStudentsNames.setBackground(new java.awt.Color(204, 204, 204));
        InputStudentsNames.setColumns(20);
        InputStudentsNames.setRows(5);
        InputStudentsNames.setText("LIKE AHMAD, 50001\nFARHAN, 50002\nSANA, 50003");
        InputStudentsNames.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        InputStudentsNames.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InputStudentsNamesKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(InputStudentsNames);

        SearchByMultiple.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 240, 150));

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton1.setText("SEARCH ALL");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        SearchByMultiple.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 130, 50));

        ShowOutput.setEditable(false);
        ShowOutput.setBackground(new java.awt.Color(204, 204, 204));
        ShowOutput.setColumns(20);
        ShowOutput.setRows(5);
        ShowOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jScrollPane4.setViewportView(ShowOutput);

        SearchByMultiple.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 66, 300, 160));
        SearchByMultiple.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 40, 30));

        Student_Panel.add(SearchByMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 680, 270));

        getContentPane().add(Student_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 710, 370));

        Delete_Panel.setBackground(new java.awt.Color(153, 153, 153));
        Delete_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        Delete_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setBackground(new java.awt.Color(153, 153, 153));
        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel15.setText("SELECT BUILDING :");
        Delete_Panel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, 40));

        Building_List.setBackground(new java.awt.Color(153, 153, 153));
        Building_List.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        Building_List.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BuildingNames" }));
        Building_List.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        Building_List.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Building_ListActionPerformed(evt);
            }
        });
        Delete_Panel.add(Building_List, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 32, 150, 30));

        jLabel16.setBackground(new java.awt.Color(153, 153, 153));
        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel16.setText("SELECT ROOM :");
        Delete_Panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 26, 110, 50));

        Rooms_of_List.setBackground(new java.awt.Color(153, 153, 153));
        Rooms_of_List.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        Rooms_of_List.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RoomNames" }));
        Rooms_of_List.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        Delete_Panel.add(Rooms_of_List, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 140, 30));

        jLabel17.setBackground(new java.awt.Color(153, 153, 153));
        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        jLabel17.setText("This Room is Arrnged by :");
        Delete_Panel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 180, 30));

        ShowArrangedBy.setEditable(false);
        ShowArrangedBy.setBackground(new java.awt.Color(153, 153, 153));
        ShowArrangedBy.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        Delete_Panel.add(ShowArrangedBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 190, 30));

        jLabel18.setBackground(new java.awt.Color(153, 153, 153));
        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        jLabel18.setText("For This Section :");
        Delete_Panel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 130, 30));

        ShowArrangedSection.setEditable(false);
        ShowArrangedSection.setBackground(new java.awt.Color(153, 153, 153));
        ShowArrangedSection.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        ShowArrangedSection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowArrangedSectionActionPerformed(evt);
            }
        });
        Delete_Panel.add(ShowArrangedSection, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 40, 30));

        Deletebutton.setBackground(new java.awt.Color(153, 153, 153));
        Deletebutton.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        Deletebutton.setText("DELETE");
        Deletebutton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        Deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletebuttonActionPerformed(evt);
            }
        });
        Delete_Panel.add(Deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 130, 50));
        Delete_Panel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 40, 30));

        getContentPane().add(Delete_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 710, 370));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void navigate_to_arrangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navigate_to_arrangeActionPerformed
        // TODO add your handling code here:
        // Step 1: Validate Capacity and Students fields
   
    }//GEN-LAST:event_navigate_to_arrangeActionPerformed

    public void SetImage(){
        ImageIcon icon= new ImageIcon(getClass().getResource("/images/p1.jpeg"));

Image img =icon.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
   jLabel2.setIcon(new ImageIcon(img));
   ImageIcon icon1= new ImageIcon(getClass().getResource("/images/sap.jpeg"));
Image img1 =icon1.getImage().getScaledInstance(saplogo.getWidth(), saplogo.getHeight(), Image.SCALE_SMOOTH);
   saplogo.setIcon(new ImageIcon(img1));
    jLabel19.setIcon(new ImageIcon(img1));
    jLabel20.setIcon(new ImageIcon(img1));
    jLabel21.setIcon(new ImageIcon(img1));
    jLabel22.setIcon(new ImageIcon(img1));
    jLabel23.setIcon(new ImageIcon(img1));
    
    }
    private void loadBuildings() {
    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/arrangeseats_database", "root", ""
        );

        String sql = "SELECT DISTINCT Building FROM arrangedseats";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Building_List.removeAllItems();
        Building_List.addItem("Select Building");

        while (rs.next()) {
            Building_List.addItem(rs.getString("Building"));
        }

        con.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private void loadRooms(String building) {
    try {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/arrangeseats_database", "root", ""
        )) {
            String sql = "SELECT DISTINCT Room FROM arrangedseats WHERE Building=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, building);
            
            ResultSet rs = ps.executeQuery();
            
            Rooms_of_List.removeAllItems();
            Rooms_of_List.addItem("Select Room");
            
            while (rs.next()) {
                Rooms_of_List.addItem(rs.getString("Room"));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private void loadDetails(String room) {
    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/arrangeseats_database","root",""
        );

        String sql = "SELECT ArrangedBy, Section FROM arrangedseats WHERE Building=? AND Room=? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1, selectedBuilding);
ps.setString(2, room);


        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ShowArrangedBy.setText(rs.getString("ArrangedBy"));
            ShowArrangedSection.setText(rs.getString("Section"));
            
        }

    } catch(Exception e) { e.printStackTrace(); }
}
private boolean isAuthorizedToDelete(String building, String room) {
    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/arrangeseats_database", "root", ""
        );

        String sql = "SELECT ArrangedBy FROM arrangedseats WHERE Building=? AND Room=? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, building);
        ps.setString(2, room);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String arrangedBy = rs.getString("ArrangedBy");

            // Compare case-insensitive
            return arrangedBy.equalsIgnoreCase(loggedTeacherName);
        }

        con.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
private void deleteRoomArrangement(String building, String room) throws SQLException {
    // Step 1: Auth check
    if (!isAuthorizedToDelete(building, room)) {
        JOptionPane.showMessageDialog(this,
            "You are not authorized to delete this arrangement.\nThis room was not arranged by you.");
        return;
    }

    Connection arrangeCon = null;
    Connection buildingCon = null;
    PreparedStatement ps = null;

    try {
        // Step 2: Connect to arrangedseats DB
        arrangeCon = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/arrangeseats_database?useSSL=false&serverTimezone=UTC", 
            "root", ""
        );

        // Step 3: Clean spaces in arrangedseats DB
        // Step 4: Delete in batches from arrangedseats
        String deleteSql = "DELETE FROM arrangedseats WHERE Building=? AND Room=? LIMIT 1000";
        ps = arrangeCon.prepareStatement(deleteSql);
        ps.setString(1, building);
        ps.setString(2, room);

        int totalDeleted = 0;
        int rows;
      boolean flag;
        do {
            rows = ps.executeUpdate();
            totalDeleted += rows;
        flag=true;
        } while (rows > 0);
        ps.close();
if(flag){  String trimSql = "UPDATE arrangedseats SET Building = TRIM(Building), Room = TRIM(Room) "
                       + "WHERE Building=? AND Room=?";
        ps = arrangeCon.prepareStatement(trimSql);
        ps.setString(1, building);
        ps.setString(2, room);
        ps.executeUpdate();
        ps.close();

      }
else{
try{   }
catch(Exception e){
    e.printStackTrace();
}}
        // Step 5: Update buildings_database to mark room available
        buildingCon = connectBuildingsDB();
        if (buildingCon != null) {
     String tableName = switch (building) {
    case "Building 1" -> "building_one_new";
    case "Building 2" -> "building_two";
    case "Building 3" -> "building_three";
    case "Building 4" -> "building_four";
    default -> "";
};

if (!tableName.isEmpty()) {
    String updateAvailable = "UPDATE " + tableName + " SET Available = 1 WHERE RoomNames = ?";
    ps = buildingCon.prepareStatement(updateAvailable);
    ps.setString(1, room);  // Correct
    ps.executeUpdate();
    ps.close();
}

        // Step 6: User feedback
        if (totalDeleted > 0) {
            JOptionPane.showMessageDialog(this, 
                "Room arrangement deleted successfully. Total rows deleted: " + totalDeleted);
        } else {
            JOptionPane.showMessageDialog(this, 
                "No arrangement found to delete for this building and room.");
        }

    } 
}
catch(SQLException e){
}}
    private Connection connectBuildingsDB() {
    try {
        String url = "jdbc:mysql://localhost:3306/buildings_database";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Buildings DB connection failed: " + e.getMessage());
        return null;
    }
}

private Connection connectArrangeSeatsDB() {
    try {
        String url = "jdbc:mysql://localhost:3306/arrangeseats_database";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "ArrangeSeats DB connection failed: " + e.getMessage());
        return null;
    }
}

    private void VerifyStudentsSeatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerifyStudentsSeatsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VerifyStudentsSeatsActionPerformed

    private void ArrangeSeatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArrangeSeatsActionPerformed
        // TODO add your handling code here:
        String capacityText = CapacityShow.getText();
        String studentsText = TotalStudents.getText();
        String teacherEmail = this.teacherEmail;  // get from the TeacherInterface
        teacherEmail = teacherEmail.split("@")[0]; // <-- Add this line


        try {
            int roomCapacity = Integer.parseInt(capacityText.trim());
            int totalStudents = Integer.parseInt(studentsText.trim());

            if (roomCapacity <= totalStudents) {
                JOptionPane.showMessageDialog(
                    this,
                    "Please select a valid or higher capacity room for arranging.",
                    "Capacity Error",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter valid numerical values for Capacity and Students.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Step 2: Main logic
        try {
            Connection studentCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_database", "root", "");
            Connection buildingCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/buildings_database", "root", "");
            Connection arrangeCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/arrangeseats_database", "root", "");

            String selectedSection = (String) jComboBox1.getSelectedItem();
            if (selectedSection.contains("'")) {
                selectedSection = selectedSection.substring(selectedSection.indexOf("'") + 1, selectedSection.lastIndexOf("'"));
            }
            String tableName = switch (selectedBuilding) {
                case "Building 1" -> "building_one_new";
                case "Building 2" -> "building_two";
                case "Building 3" -> "building_three";
                case "Building 4" -> "building_four";
                default -> "";
            };

            String selectedBuilding = (String) list_of_building.getSelectedItem();
            String selectedRoom = (String) list_of_rooms.getSelectedItem();

            if (selectedSection == null || selectedBuilding == null || selectedRoom == null ||
                selectedSection.equalsIgnoreCase("Select Section") ||
                selectedBuilding.equalsIgnoreCase("Select Building") ||
                selectedRoom.equalsIgnoreCase("Select Room")) {
                JOptionPane.showMessageDialog(this, "Please select Section, Building, and Room first.");
                return;
            }
            // Step 3: Fetch students of selected section
            String queryStudents = "SELECT Names, Roll_No, Sap, Section FROM students WHERE Section = ?";
            PreparedStatement pstStudents = studentCon.prepareStatement(queryStudents);
            pstStudents.setString(1, selectedSection.trim());
            ResultSet rsStudents = pstStudents.executeQuery();

            List<String[]> students = new ArrayList<>();
            while (rsStudents.next()) {
                students.add(new String[]{
                    rsStudents.getString("Names"),
                    rsStudents.getString("Roll_No"),
                    rsStudents.getString("Sap"),
                    rsStudents.getString("Section")
                });
            }

            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students found for section: " + selectedSection);
                pstStudents.close();
                studentCon.close();
                buildingCon.close();
                arrangeCon.close();
                return;
            }

            // Step 4: Fetch selected room capacity and availability
            String queryCapacity = "SELECT RoomCapacity, Available FROM " + tableName + " WHERE RoomNames = ?";
            PreparedStatement pstCap = buildingCon.prepareStatement(queryCapacity);
            pstCap.setString(1, selectedRoom);
            ResultSet rsCap = pstCap.executeQuery();

            int capacity = 0;
            int available = 0;
            if (rsCap.next()) {
                capacity = rsCap.getInt("RoomCapacity");
                available = rsCap.getInt("Available");
            } else {
                JOptionPane.showMessageDialog(this, "Room not found in building: " + selectedBuilding);
                return;
            }

            if (available == 0) {
                JOptionPane.showMessageDialog(this, "Room is not available.");
                return;
            }

            if (capacity < students.size()) {
                JOptionPane.showMessageDialog(this, "Room capacity is less than total students in section.");
                return;
            }

            // Step 5: Randomize student order for unpredictable arrangement
            Collections.shuffle(students, new Random());

            // Step 6: Insert arranged data into arrangedseats table
            String insertQuery = "INSERT INTO arrangedseats (Name, Roll_No, Sap, Section, Building, Room, SeatNo, ArrangedBy) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement pstInsert = arrangeCon.prepareStatement(insertQuery);

            int seatNo = 1;
            for (String[] s : students) {
                pstInsert.setString(1, s[0]);
                pstInsert.setString(2, s[1]);
                pstInsert.setString(3, s[2]);
                pstInsert.setString(4, s[3]);
                pstInsert.setString(5, selectedBuilding);
                pstInsert.setString(6, selectedRoom);
                pstInsert.setInt(7, seatNo++);
                pstInsert.setString(8, teacherEmail);
                pstInsert.executeUpdate();
            }

            // Step 7: Mark room unavailable (Available = 0)
            String updateRoom = "UPDATE " + tableName + " SET Available = 0 WHERE RoomNames = ?";
            PreparedStatement pstUpdate = buildingCon.prepareStatement(updateRoom);
            pstUpdate.setString(1, selectedRoom);
            pstUpdate.executeUpdate();

            JOptionPane.showMessageDialog(this, "Seats arranged successfully for section " + selectedSection + " in room " + selectedRoom + ".");

            // Step 8: Close all
            pstStudents.close();
            pstCap.close();
            pstInsert.close();
            pstUpdate.close();
            studentCon.close();
            buildingCon.close();
            arrangeCon.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_ArrangeSeatsActionPerformed

    private void list_of_buildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_of_buildingActionPerformed
        // TODO add your handling code herere :
    }//GEN-LAST:event_list_of_buildingActionPerformed

    private void list_of_buildingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_list_of_buildingItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_list_of_buildingItemStateChanged

    private void InputStudentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputStudentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputStudentNameActionPerformed

    private void list_of_buildingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_list_of_buildingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_list_of_buildingKeyPressed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        String fullName = InputStudentName.getText().trim();
    String sapId = InputStudentSap.getText().trim();

    // Step 2: Validate inputs
    if (fullName.isEmpty() || sapId.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
                "Please fill in both Full Name and SAP ID fields.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
        return; // Stop here
    } try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/arrangeseats_database", "root", "");

        // Step 3: SQL Query
        String sql = "SELECT Room, SeatNo,ArrangedBy FROM arrangedseats WHERE Name = ? AND Sap = ? ";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullName);
        pst.setString(2, sapId);
        ResultSet rs = pst.executeQuery();

        // Step 4: Show Data
        if (rs.next()) {
            String ArrangedBy=rs.getString("ArrangedBy");
            String room = rs.getString("Room");
            String seat = rs.getString("SeatNo");
            ShowData.setText("Room Name: " + room + "\nSeat Number: " + seat +"\nArranged By : "+ArrangedBy);
        } else {
            JOptionPane.showMessageDialog(this, "No record found for this student.");
        }

        con.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    String choice = (String) jComboBox2.getSelectedItem();

    SearchByFirstRows.setVisible("See First Five(5) Students in a Selected Room".equals(choice));
    SearchBySingle.setVisible("Search For Single Student with Name and Sap".equals(choice));
        ShowData.setVisible("Search For Single Student with Name and Sap".equals(choice));
            SearchButton.setVisible("Search For Single Student with Name and Sap".equals(choice));
        InputStudentSap.setVisible("Search For Single Student with Name and Sap".equals(choice));
        InputStudentName.setVisible("Search For Single Student with Name and Sap".equals(choice));
   SearchByMultiple.setVisible("Search For Multiple Students with Names and Saps".equals(choice));
        
//SearchBySingle.setVisible(true);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void list_of_roomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_of_roomsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_list_of_roomsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         String input = InputStudentsNames.getText().trim();

    if (input.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter at least one record before searching.");
        return;
    }

    ShowOutput.setText(""); // clear old results

    String[] lines = input.split("\\n");
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            String name = parts[0].trim();
            String sap = parts[1].trim();

            // Example pseudo-code for searching DB:
            String result = searchStudent(name, sap);

            // Append result to outputArea
            ShowOutput.append(result + "\n");
        } else {
            ShowOutput.append("Invalid input format: " + line + "\n");
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed
private String searchStudent(String name, String sap) {
    String result = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/arrangeseats_database", "root", "");

        String query = "SELECT * FROM arrangedseats WHERE Name = ? AND Sap = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, name);
        pst.setString(2, sap);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Customize the message based on your table columns
            String room = rs.getString("Room");
            String seat = rs.getString("SeatNo");
            String ArrangedSeat = rs.getString("ArrangedBy");
            result = name + " (" + sap + ") → Room: " + room + ", Seat: " + seat +" ArrangedBy "+ArrangedSeat;
        } else {
            result = name + " (" + sap + ") → Not found in arrangeseats";
        }

        rs.close();
        pst.close();
        con.close();

    } catch (Exception e) {
        result = "Error searching " + name + " (" + sap + "): " + e.getMessage();
    }

    return result;
}

    private void ShowFirstRowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowFirstRowsActionPerformed
        // TODO add your handling code here:
           String selectedRoom = (String) roomcombobox.getSelectedItem();
    if (selectedRoom == null || selectedRoom.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a room first.");
        return;
    }
try{
Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/arrangeseats_database", "root", "");
        PreparedStatement ps = con.prepareStatement(
            "SELECT Name, Sap,Section, SeatNo,ArrangedBy FROM arrangedseats WHERE room = ? LIMIT 5;"
        );
        ps.setString(1, selectedRoom);
        ResultSet rs = ps.executeQuery();

        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (rs.next()) {
            count++;
            sb.append(count)
              .append(". Name: ").append(rs.getString("Name"))
              .append("\n   SAP ID: ").append(rs.getString("Sap"))
              .append("\n   Section: ").append(rs.getString("Section"))
              .append("\n   Seat: ").append(rs.getString("SeatNo"))
              .append("\n   ArrangedBy: ").append(rs.getString("ArrangedBy"))
              .append("\n\n");
        }

        if (count == 0) {
            ShowFirstFiveStudents.setText("No students found for this room.");
        } else {
            ShowFirstFiveStudents.setText(sb.toString());
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching students: " + e.getMessage());
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(TeacherInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ShowFirstRowsActionPerformed

    private void navigation_to_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_navigation_to_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_navigation_to_deleteActionPerformed

    private void Building_ListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Building_ListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Building_ListActionPerformed

    private void ShowArrangedSectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowArrangedSectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowArrangedSectionActionPerformed

    private void DeletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletebuttonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_DeletebuttonActionPerformed

    private void InputStudentsNamesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InputStudentsNamesKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_InputStudentsNamesKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
private Connection connectDB() {
    try {
        // Update port if using 3307 in XAMPP
        String url = "jdbc:mysql://localhost:3306/buildings_database?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = ""; // or your password if set
        return DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
        return null;
    }
}
void loadRooms() {
    try (var con = connectArrangeSeatsDB()) {
        PreparedStatement ps = con.prepareStatement("SELECT DISTINCT Room FROM arrangedseats");
        ResultSet rs = ps.executeQuery();
        roomcombobox.removeAllItems();
        while (rs.next()) {
            roomcombobox.addItem(rs.getString("Room"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
    }
}

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
            java.util.logging.Logger.getLogger(TeacherInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
new TeacherInterface().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ArrangeSeats;
    private javax.swing.JComboBox<String> Building_List;
    private javax.swing.JTextField CapacityShow;
    private javax.swing.JPanel Delete_Panel;
    private javax.swing.JButton Deletebutton;
    private javax.swing.JTextField InputStudentName;
    private javax.swing.JTextField InputStudentSap;
    private javax.swing.JTextArea InputStudentsNames;
    private javax.swing.JComboBox<String> Rooms_of_List;
    private javax.swing.JButton SearchButton;
    private javax.swing.JPanel SearchByFirstRows;
    private javax.swing.JPanel SearchByMultiple;
    private javax.swing.JPanel SearchBySingle;
    private javax.swing.JTextField ShowArrangedBy;
    private javax.swing.JTextField ShowArrangedSection;
    private javax.swing.JTextPane ShowData;
    private javax.swing.JTextArea ShowFirstFiveStudents;
    private javax.swing.JButton ShowFirstRows;
    private javax.swing.JTextArea ShowOutput;
    private javax.swing.JPanel Student_Panel;
    private javax.swing.JTextField TotalStudents;
    private javax.swing.JButton VerifyStudentsSeats;
    private javax.swing.JPanel arrangepanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> list_of_building;
    private javax.swing.JComboBox<String> list_of_rooms;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton navigate_to_arrange;
    private javax.swing.JButton navigation_to_delete;
    private javax.swing.JComboBox<String> roomcombobox;
    private javax.swing.JLabel saplogo;
    // End of variables declaration//GEN-END:variables
}