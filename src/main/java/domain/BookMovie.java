package domain;

public class BookMovie {
    Movie movie;
    int capacity;
    PlaySchedule playSchedules ;
    public BookMovie(Movie movie, int capacity, PlaySchedule playSchedules) {
        this.movie = movie;
        this.capacity = capacity;
        this.playSchedules = playSchedules;
    }

    @Override
    public String toString() {
        return  movie.getId()+ " - " + movie.getName() +"\n"
                + "시작시간 = " + playSchedules.getStartDateTime()+"\n"
                + "예약 인원 = "+ capacity + "명"+"\n";
    }
}
