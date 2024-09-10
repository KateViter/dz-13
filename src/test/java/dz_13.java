import org.testng.annotations.Test;
import utils.DBManager;
import java.sql.*;

public class dz_13 {

    public static final String SELECT_ALL = "SELECT * FROM teachers";
    public static final String SELECT_PARAMS = "SELECT * FROM teachers WHERE salary > 42000";
    private static final String INSERT = "INSERT INTO teachers(first_name, last_name, school, hire_date, salary) values(?,?,?,?,?)";
    private static final String UPDATE = "update teachers set first_name=? where id=?";
    private static final String DELETE = "delete from teachers where id=?";


    @Test()
    public void selectAllTest() {
        try(Connection connect = DBManager.getConnection()) {
            PreparedStatement stmt = connect.prepareStatement(SELECT_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString("first_name")
                        + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test()
    public void selectWithParamTest(){
        try(Connection connect = DBManager.getConnection()) {
            PreparedStatement stmt = connect.prepareStatement(SELECT_PARAMS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString("first_name")
                        + " " + rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test()
    public void updateTest() {
        try (Connection connect = DBManager.getConnection()) {
            PreparedStatement prepSt = connect.prepareStatement(UPDATE);
            prepSt.setString(1,"Sofie");
            prepSt.setInt(2,3);
            prepSt.execute();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test()
    public void insertTest() {
        try (Connection connect = DBManager.getConnection()) {
            PreparedStatement prepSt = connect.prepareStatement(INSERT);
            prepSt.setString(1, "John");
            prepSt.setString(2, "Doe");
            prepSt.setString(3, "School #46");
            prepSt.setDate(4, java.sql.Date.valueOf("2018-01-02"));
            prepSt.setInt(5, 18000);
            prepSt.execute();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Test()
    public void deleteTest() {
        try (Connection connect = DBManager.getConnection()) {
            PreparedStatement prepStatement = connect.prepareStatement(DELETE);
            prepStatement.setInt(1, 7);
            prepStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
