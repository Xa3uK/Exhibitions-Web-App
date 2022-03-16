package util;

public interface Queries {
    String ADD_EXHIBITION = "insert into exhibitions (theme, hall, start_date, end_date, " +
            "start_time, end_time, price) values (?,?,?,?,?,?,?)";
    String DEL_EXHIBITION = "delete from exhibitions where id = ?";
    String FIND_ALL_EXHIBITIONS = "select * from exhibitions";
    String FIND_BY_LOGIN = "select * from users where login = ?";
    String FIND_BY_EMAIL = "select * from users where email = ?";
    String REGISTER_USER = "insert into users (login, password, email, role) values (?,?,?,?)";
    String ADD_MONEY = "update users set money = money + ? where id = ?";
    String DEBIT_MONEY = "update users set money = money - ? where id = ?";
    String BUY_TICKET = "insert into tickets (user_id, exhibition_id) values (?, ?)";
    String CHECK_AMOUNT_MONEY = "select money from users where id = ?";
    String CHECK_EXHIBITION_STATISTICS = "select e.id, e.theme, e.hall, e.price, e.start_date, e.end_date, e.start_time, e.end_time,\n" +
            "count(*) as sold_tickets from exhibitions e\n" +
            "join tickets t on (e.id = t.exhibition_id)\n" +
            "group by e.id order by sold_tickets desc";
    String CHECK_EXHIBITION_PRICE = "select price from exhibitions where id = ?";
    String GET_USER = "select * from users where id = ?";
}