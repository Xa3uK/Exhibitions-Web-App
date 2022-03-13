package util;

public interface Queries {
    String ADD_EXHIBITION = "insert into exhibitions (theme, hall, start_date, end_date, " +
            "start_time, end_time, price) values (?,?,?,?,?,?,?)";
    String DEL_EXHIBITION = "delete from exhibitions where id = ?";
    String FIND_ALL_EXHIBITIONS = "select * from exhibitions";
    String FIND_BY_LOGIN = "select * from users where login = ?";
    String FIND_BY_EMAIL = "select * from users where email = ?";
    String REGISTER_USER = "insert into users (login, password, email, role) values (?,?,?,?)";
}
