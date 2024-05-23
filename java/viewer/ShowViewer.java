package viewer;

import controller.MovieController;
import controller.ShowController;
import controller.TheaterConrtoller;
import lombok.Setter;
import model.MovieDTO;
import model.ShowDTO;
import model.TheaterDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

//상영정보
public class ShowViewer {
    @Setter
    private ShowController showController;
    @Setter
    private ShowDTO showDTO;
    @Setter
    private MovieDTO movieDTO;
    @Setter
    private TheaterDTO theaterDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private MovieViewer movieViewer;
    @Setter
    private TheaterConrtoller theaterController;
    @Setter
    private TheaterViewer theaterViewer;

    public void showList(int id) {
        ArrayList<ShowDTO> list = showController.selectAll();
        if (list.isEmpty()) {
            System.out.println("해당 극장에 상영 목록이 존재하지 않습니다.");
            theaterViewer.theaterList();
        }
        else {
            briefList(list);
            String message = "뒤로 가실려면 아무키나 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);

            while (!validateValue(userChoice)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }

            // 뒤로가기
                theaterViewer.theaterList();

        }
    }
    private void briefList(ArrayList<ShowDTO> list){
        //극장번호 받아서 해당하는거만 출력
        for(ShowDTO s : list){
            System.out.printf("극장 번호 : %d, 영화 번호 : %d, 상영시간 : %s\n", s.getTheaterId(),s.getMovieId(),s.getShowTime());
        }
    }
    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return showController.selectOne(value) != null;
    }
}
