DECLARE
BEGIN
   FOR rec IN (
      SELECT c.Name,
             l.LoanID,
             l.EndDate
      FROM Customers c
      JOIN Loans l
      ON c.CustomerID = l.CustomerID
      WHERE l.EndDate BETWEEN SYSDATE
                          AND SYSDATE + 30
   )
   LOOP
      DBMS_OUTPUT.PUT_LINE(
         'Reminder: Loan '
         || rec.LoanID
         || ' for '
         || rec.Name
         || ' is due on '
         || rec.EndDate
      );

   END LOOP;
END;
/