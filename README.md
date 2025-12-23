PROJECT: Exam Seating Arrangement System
LANGUAGE: Java (NetBeans Project)
DATABASE: MySQL (XAMPP / phpMyAdmin)

----------------------------------------
1. IMPORTANT – CHECK DATABASE CONNECTION
----------------------------------------
Before running the project, make sure your MySQL server (XAMPP) is ON.

Required:
- Apache: RUNNING
- MySQL: RUNNING

Open phpMyAdmin and confirm that the database is created correctly.

Databases Names:
    1.authentication_database
    2.buildings_database
    3.students_database
    4.arrangeseats_database
	
If the database does NOT exist:
1. Open phpMyAdmin
2. Click “New”
3. Create database: exam_system
4. Click “Import”
5. Select file: database/exam_system.sql
6. Click “Go”

Your database is now ready.


----------------------------------------
2. ADD REQUIRED JAR FILES (Libraries)
----------------------------------------
After opening the project in NetBeans:

1. Right-click on “Libraries”
2. Select “Add JAR/Folder”
3. Add the following files from the /lib folder:

   - mysql-connector-j-8.0.33.jar
   - itextpdf-5.5.13.3.jar
   - xmlworker-5.5.13.jar

Without adding these JARs, the project will NOT run.


----------------------------------------
3. STARTING THE PROJECT (LOGIN SYSTEM)
----------------------------------------
1. Open the project in NetBeans.
2. Add libraries (explained above).
3. Make sure MySQL is connected.
4. Run the file:

   Login.java   (or the main login screen class)

This will open the authentication window.


----------------------------------------
4. TEACHER LOGIN (ADMIN PANEL)
----------------------------------------
Teacher credentials are stored inside the database(authentication_database) tablename(teachers):

    teachers

The login form verifies username & password directly from the database.

After successful login:
- Teacher dashboard opens
- This interface is fully user-friendly

Teacher can:
  ✓ Arrange rooms  
  ✓ Assign seats  
  ✓ Check students  
  ✓ Delete a room  
  ✓ Generate / save seating  
  ✓ Log out   


----------------------------------------
5. STUDENT LOGIN (SLIP CHECKING)
----------------------------------------
Student login credentials are stored in the students table.

Process:
1. Open Login page
2. Enter SAP ID / Roll Number
3. Student dashboard will show:

   - Student Name
   - SAP ID / Roll Number
   - Assigned Room
   - Seat Number

Students can download their exam slip.


----------------------------------------
6. HOW THE SYSTEM WORKS (QUICK SUMMARY)
----------------------------------------
- Teacher logs in → manages rooms → assigns seats.
- System ensures no adjacent students have same roll series.
- Output is saved room-wise for record.
- Students log in → view their seating slip → download.


----------------------------------------
7. REQUIREMENTS
----------------------------------------
Java JDK 8+
NetBeans IDE
MySQL + XAMPP
MySQL Connector JAR

Run order:
1. Start XAMPP MySQL
2. Import SQL file
3. Add libraries
4. Run Login.java


----------------------------------------
8. FILE STRUCTURE
----------------------------------------
SourceCode/
    src/        → Java source files
    lib/        → Required JAR files
    images/     → Buttons, icons, UI images
    database/   → Databases.sql
    README.txt  → Instructions file


----------------------------------------
9. CONTACT / SUPPORT
----------------------------------------
For any issue running the project:
- Re-check database import
- Confirm JAR libraries are added in NetBeans
- Make sure MySQL is running before executing the program

----------------------------------------
END OF README
----------------------------------------
