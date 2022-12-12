import domain.BookMovie;
import domain.Movie;
import domain.MovieRepository;
import domain.PlaySchedule;
import view.InputView;
import view.OutputView;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static view.OutputView.*;

public class MovieApplication {
    private static List<Movie> movies;
    private static int money;
    private static List<BookMovie> bookMovies =new ArrayList<>();

    public static void main(String[] args) {
        MovieInfo();
        bookMovie();
        payMoney();
    }

    /**
     * 영화 데이터 추가
     */
    public static void MovieInfo(){
        movies = MovieRepository.getMovies();
        OutputView.printMovies(movies);
    }

    /**
     * 영화 예약하기
     */
    public static void bookMovie(){
        Movie movie = ChooseMovie();
        PlaySchedule playSchedule = ChoosePlaySchedule(movie);
        int people = bookPeople(playSchedule);
        bookMovies.add(new BookMovie(movie,people,playSchedule));
        money+=movie.getPrice()*people;
        Check();
    }

    /**
     * 영화 번호 선택
     */
    private static Movie ChooseMovie(){
        return reservationMovie(movies, InputView.inputMovieId());
    }
    private static  Movie reservationMovie(List<Movie> movies, int movieId) {
        Movie movie = ValidateMovie(movies, movieId);
        OutputView.printMovie(movie);
        return movie;
    }
    public static Movie ValidateMovie(List<Movie> movies, int movieId){
        Movie movie1 =null;
        try {
            movie1 = movies.stream()
                    .filter(movie -> movie.getId() == movieId)
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }catch (Exception e){
            OutputView.Error(ERROR_MISTAKE);
            ValidateMovie( movies, movieId);
        }
        return movie1;
    }

    /**
     * 예약할 시간표 선택
     */

    private static PlaySchedule ChoosePlaySchedule(Movie movie) {
        checkMovieDate(movie);
        PlaySchedule playSchedule = ValidateSchedules(movie);
        return playSchedule;
    }

    public static void checkMovieDate(Movie movie){
        try {
            movie.getPlaySchedules().stream()
                    .filter(playSchedule -> playSchedule.getStartDateTime().isAfter(LocalDateTime.now()))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }catch (IllegalArgumentException e){
            OutputView.Error(ERROR_MISTAKE);
            checkMovieDate(movie);
        }
    }

    public static PlaySchedule ValidateSchedules(Movie movie){
        PlaySchedule playSchedule=null;
        try {
            playSchedule=movie.getPlaySchedules().get(InputView.inputTime());
            boolean check = playSchedule.getStartDateTime().isBefore(LocalDateTime.now());
            if(check){
                throw new IllegalArgumentException(ERROR_TIME_OVER);
            }
        }catch (Exception e){
            OutputView.Error(ERROR_MISTAKE);
            ValidateSchedules(movie);
        }
        return playSchedule;
    }

    /**
     * 남은 좌석 갯수 예약
     */
    private static int bookPeople(PlaySchedule playSchedule){

        int people = InputView.inputPeople();
        if(people >playSchedule.getCapacity()){
            throw new IllegalArgumentException(ERROR_OUT_OF_RANGE);
        }
        return people;
    }
    public static void Check(){
        int i = InputView.inputCheck();
        switch (i){
            case 1: OutputView.Finish(bookMovies,money); break;
            case 2: bookMovie(); break;
        }
    }

    /**
     * 결제 구역
     */
    private static void payMoney(){

        /**
         * 포인트 입력하기
         */
        int point = InputView.inputPoint();
        /**
         * 결제하기
         */
        float persent=Discount();

        int afterPoint=money- point;
        int payMoney = afterPoint - (int)(persent * afterPoint) ;
        OutputView.End(point,persent,payMoney);

    }
    private static float Discount(){
        int pay = InputView.inputPay();
        return pay==1?0.05f:0.02f;
    }
}