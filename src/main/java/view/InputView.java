package view;

import java.util.InputMismatchException;
import java.util.Scanner;
public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MOVIE_TEXT = "## 예약할 영화를 선택하세요.";
    private static final String INPUT_SCHEDULE_TEXT = "## 예약할 시간를 선택하세요.(위에서 부터 1,2,...)";
    private static final String INPUT_CAPACITY_TEXT = "## 예약 인원을 입력하세요.";
    private static final String INPUT_RESERVE_TEXT = "## 예약 확인 입니다. [ 1: 예약, 2: 추가 예약 ]";
    private static final String INPUT_POINT_TEXT = "## 사용할 포인트를 입력하세요.";
    private static final String INPUT_PAYMENT_METHOD_TEXT = "## 결제 방법을 선택하세요. [ 1: 현금, 2: 카드 ]";
    private static final String ERROR_NOT_INTEGER = "정수가 아닙니다 정수를 입력해주세요.";
    public static int inputMovieId() {
        System.out.println(INPUT_MOVIE_TEXT);
        return getInputInt();
    }
    public static int inputTime() {
        System.out.println(INPUT_SCHEDULE_TEXT);
        return getInputInt()-1;
    }
    public static int inputPeople() {
        System.out.println(INPUT_CAPACITY_TEXT);
        return getInputInt();
    }
    public static int inputCheck() {
        System.out.println(INPUT_RESERVE_TEXT);
        return getInputInt();
    }

    public static int inputPoint() {
        System.out.println(INPUT_POINT_TEXT);
        return getInputInt();
    }
    public static int inputPay() {
        System.out.println(INPUT_PAYMENT_METHOD_TEXT);
        return getInputInt();
    }

    private static int getInputInt(){
        try {
            return scanner.nextInt();
        }catch (InputMismatchException exception){
            System.out.println(ERROR_NOT_INTEGER);
            return getInputInt();
        }
    }
}
