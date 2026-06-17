import java.sql.*;

public class Ex32 {

    static String url =
            "jdbc:mysql://localhost:3306/studentdb";

    static String username = "root";

    static String password = "root";

    public static void insertStudent(
            int id,
            String name
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            String query =
                    "INSERT INTO students VALUES (?, ?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setInt(1,id);
            ps.setString(2,name);

            ps.executeUpdate();

            System.out.println(
                    "Student Inserted Successfully"
            );

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }

    public static void updateStudent(
            int id,
            String name
    ) {

        try {

            Connection con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            String query =
                    "UPDATE students SET name=? WHERE id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1,name);
            ps.setInt(2,id);

            ps.executeUpdate();

            System.out.println(
                    "Student Updated Successfully"
            );

            con.close();

        } catch(Exception e) {

            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        insertStudent(
                101,
                "Krishna"
        );

        updateStudent(
                101,
                "Krishna K"
        );
    }
}