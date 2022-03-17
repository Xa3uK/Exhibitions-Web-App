package model;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class TicketDao {
    private String theme;
    private String hall;
    private LocalDate startDate;
    private LocalDate endDate;
    private Time startTime;
    private Time endTime;
    private int price;
    private int ticketsCount;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDao ticketDao = (TicketDao) o;
        return price == ticketDao.price && ticketsCount == ticketDao.ticketsCount && theme.equals(ticketDao.theme) && hall.equals(ticketDao.hall) && startDate.equals(ticketDao.startDate) && endDate.equals(ticketDao.endDate) && startTime.equals(ticketDao.startTime) && endTime.equals(ticketDao.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, hall, startDate, endDate, startTime, endTime, price, ticketsCount);
    }
}
