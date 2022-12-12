package view;

import domain.BookMovie;
import domain.Movie;

import java.util.List;

public class OutputView {
    public static String ERROR_MISTAKE="잘못 입력하셨습니다 다시 입력해주세요.";
    public static String ERROR_OUT_OF_RANGE="범위를 벗어났습니다 다시 입력해주세요.";
    public static String ERROR_TIME_OVER="시간이 지났습니다 다시 입력해주세요.";
    public static void printMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
   public static void printMovie(Movie movie) {
        System.out.println(movie);
    }

    public static void Finish(List<BookMovie> bookMovies, int money) {
        for(BookMovie bookMovie : bookMovies){
            System.out.println(bookMovie.toString());
        }
        System.out.println();
    }
    public static void Error(String message) {
        System.out.println(message);
    }
    public static void End(int point, float persent, int payMoney) {
        System.out.println(
                        "포인트 : " + point+"\n"+
                        "할인률 : " + persent*100+"% \n"+
                        "결제금액 : " + payMoney+"\n"

        );
    }
}
