package backend;
import java.sql.*;

public class DBWorker {

    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "z123456789Zazat";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("------------------------------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    int[] count = new int[71];

    public void PopularFoods() {
        String SQL = "SELECT dish_id from food_order";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                switch (rs.getInt(2)) {
                    case 1:
                        count[0]++;
                    case 2:
                        count[1]++;
                    case 3:
                        count[2]++;
                    case 4:
                        count[3]++;
                    case 5:
                        count[4]++;
                    case 6:
                        count[5]++;
                    case 7:
                        count[6]++;
                    case 8:
                        count[7]++;
                    case 9:
                        count[8]++;
                    case 10:
                        count[9]++;
                    case 11:
                        count[10]++;
                    case 12:
                        count[11]++;
                    case 13:
                        count[12]++;
                    case 14:
                        count[13]++;
                    case 15:
                        count[14]++;
                    case 16:
                        count[15]++;
                    case 17:
                        count[16]++;
                    case 18:
                        count[17]++;
                    case 19:
                        count[18]++;
                    case 20:
                        count[19]++;
                    case 21:
                        count[20]++;
                    case 22:
                        count[21]++;
                    case 23:
                        count[22]++;
                    case 24:
                        count[23]++;
                    case 25:
                        count[24]++;
                    case 26:
                        count[25]++;
                    case 27:
                        count[26]++;
                    case 28:
                        count[27]++;
                    case 29:
                        count[28]++;
                    case 30:
                        count[29]++;
                    case 31:
                        count[30]++;
                    case 32:
                        count[31]++;
                    case 33:
                        count[32]++;
                    case 34:
                        count[33]++;
                    case 35:
                        count[34]++;
                    case 36:
                        count[35]++;
                    case 37:
                        count[36]++;
                    case 38:
                        count[37]++;
                    case 39:
                        count[38]++;
                    case 40:
                        count[39]++;
                    case 41:
                        count[40]++;
                    case 42:
                        count[41]++;
                    case 43:
                        count[42]++;
                    case 44:
                        count[43]++;
                    case 45:
                        count[44]++;
                    case 46:
                        count[45]++;
                    case 47:
                        count[46]++;
                    case 48:
                        count[47]++;
                    case 49:
                        count[48]++;
                    case 50:
                        count[49]++;
                    case 51:
                        count[50]++;
                    case 52:
                        count[51]++;
                    case 53:
                        count[52]++;
                    case 54:
                        count[53]++;
                    case 55:
                        count[54]++;
                    case 56:
                        count[55]++;
                    case 57:
                        count[56]++;
                    case 58:
                        count[57]++;
                    case 59:
                        count[58]++;
                    case 60:
                        count[59]++;
                    case 61:
                        count[60]++;
                    case 62:
                        count[61]++;
                    case 63:
                        count[62]++;
                    case 64:
                        count[63]++;
                    case 65:
                        count[64]++;
                    case 66:
                        count[65]++;
                    case 67:
                        count[66]++;
                    case 68:
                        count[67]++;
                    case 69:
                        count[68]++;
                    case 70:
                        count[69]++;
                    case 71:
                        count[70]++;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            if (max < count[i]) {
                max = count[i];
            }
        }
    }

    public void addOrder(int dishId) {
        String SQL = "insert into food_order(dish_id, order_cost, waiting_time, order_time) " +
                "values (?, ?, '1 hour', current_timestamp)";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setInt(1, dishId);
            preparedStatement.setInt(2, getPrice(dishId) + getDeliveryCost(dishId));
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCafeList() {
        String SQL = "select * from cafe";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {

            System.out.println("id  ||  name  || open at || close at || aver. check || delivery cost");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "   : " + rs.getString(2) + "    : "
                        + rs.getTime(3) + " : " + rs.getTime(4) + "   : "
                        + rs.getInt(5) + "       : " + rs.getInt(6));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getFoodList(int i) {
        int j = 0;
        if (i < 5 && i != 0) {
            j = i;
        } else {
            System.out.println("Wrong input");
        }
        String SQL = "select dish.id, dish_name, grams_weight, price from dish left join cafe on cafe_id = cafe.id " +
                "where cafe_id = " + j;
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            System.out.println("Меню:");
            System.out.println("id ||   dish   || grams || price");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " || " + rs.getString(2) + " || " +
                        rs.getInt(3) + " || " + rs.getInt(4));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int getPrice(int id) {
        String SQL = "select price from dish where id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        return 0;
    }

    private int getDeliveryCost(int id) {
        String SQL = "select delivery_cost from cafe join dish on dish.cafe_id = cafe.id where dish.id = ?";
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        return 0;
    }

    public void returnAllOrders() {
        String SQL = "select food_order.id, dish.dish_name, order_cost, waiting_time, order_time from food_order " +
                "join dish on dish.id = food_order.dish_id;";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {

            System.out.println("№ || dish  ||  order_cost  ||  waiting ||  order time   ||");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " : " + rs.getString(2) +
                        " : " + rs.getInt(3) + " : " + rs.getString(4) + " : " +
                        rs.getTimestamp(5));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void getDishByPrice() {
        String SQL = "select dish.id, dish_name, (dish.price + delivery_cost) " +
                "from dish join cafe on dish.cafe_id = cafe.id order by (dish.price + delivery_cost) DESC;";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {

            System.out.println("№ || id ||  dish  || total price ||");
            int i = 1;
            while (rs.next()) {
                System.out.println(i + "  :   " + rs.getInt(1) + " : " + rs.getString(2) +
                        " : " + rs.getInt(3));
                i++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
