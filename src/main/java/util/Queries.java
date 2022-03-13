package util;

public interface Queries {
    String ADD_EXHIBITION = "insert into exhibitions (theme, hall, start_date, end_date, " +
            "start_time, end_time, price) values (?,?,?,?,?,?,?)";
    String DEL_EXHIBITION = "delete from exhibitions where id = ?";
    String FIND_ALL_EXHIBITIONS = "select * from exhibitions";
}
