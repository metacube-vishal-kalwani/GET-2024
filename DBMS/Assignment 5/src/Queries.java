public class Queries {

    public static String getQuery1(String userId) {

        String query1 = "SELECT order_id, order_date, total_amount, order_status"
                + "FROM CustomerOrders"
                + "WHERE user_id ='"
                + userId
                + "' AND order_status ='Inprocess';";
        return query1;
    }

}
