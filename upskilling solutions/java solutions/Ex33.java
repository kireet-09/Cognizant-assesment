import java.sql.*;

public class Ex33 {

    static String url =
            "jdbc:mysql://localhost:3306/studentdb";

    static String username = "root";

    static String password = "root";

    public static void transfer(
            int fromAccount,
            int toAccount,
            double amount
    ) {

        Connection con = null;

        try {

            con =
                    DriverManager.getConnection(
                            url,
                            username,
                            password
                    );

            con.setAutoCommit(false);

            PreparedStatement debit =
                    con.prepareStatement(
                            "UPDATE accounts " +
                            "SET balance = balance - ? " +
                            "WHERE id = ?"
                    );

            debit.setDouble(1,amount);
            debit.setInt(2,fromAccount);

            debit.executeUpdate();

            PreparedStatement credit =
                    con.prepareStatement(
                            "UPDATE accounts " +
                            "SET balance = balance + ? " +
                            "WHERE id = ?"
                    );

            credit.setDouble(1,amount);
            credit.setInt(2,toAccount);

            credit.executeUpdate();

            con.commit();

            System.out.println(
                    "Transfer Successful"
            );

        } catch(Exception e) {

            try {

                if(con != null)
                    con.rollback();

            } catch(Exception ex) {

                System.out.println(ex);
            }

            System.out.println(
                    "Transaction Failed"
            );

        } finally {

            try {

                if(con != null)
                    con.close();

            } catch(Exception e) {

                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {

        transfer(
                1,
                2,
                1000
        );
    }
}