import controller.ExhibitionService;
import model.Exhibition;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class TempMain {
    public static void main(String[] args) {
        Exhibition ex = new Exhibition();
        ex.setTheme("Animals");
        ex.setHall("Elon room");
        ex.setPrice(100);
        ex.setStartDate(LocalDate.of(2022, 3, 15));
        ex.setEndDate(LocalDate.of(2022, 5, 22));
        ex.setStartTime(Time.valueOf(LocalTime.of(10,0)));
        ex.setEndTime(Time.valueOf(LocalTime.of(17,0)));

        ExhibitionService exserv = new ExhibitionService();
        List<Exhibition> exs = exserv.getExhibitions();
        for (Exhibition exhibition: exs) {
            System.out.println(exhibition);
        }
    }
}
