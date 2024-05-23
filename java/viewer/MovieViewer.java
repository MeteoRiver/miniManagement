package viewer;
//영화정보

import controller.MovieController;
import controller.UserController;
import lombok.Setter;
import model.MovieDTO;
import model.UserDTO;
import util.ScannerUtil;


import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewer {
    @Setter
    private MovieController movieController;
    @Setter
    private MovieDTO movieDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private ReviewViewer reviewViewer;
    @Setter
    private UserController userController;
    @Setter
    private UserViewer userViewer;
    public void movieList(int id) {
        ArrayList<MovieDTO> list = movieController.selectAll();
        if (list.isEmpty()) {
            System.out.println("영화 목록이 존재하지 않습니다.");
            userViewer.showmenu();
        }
        else {
            briefList(list);
            String message = "상세보기할 영화의 번호나 뒤로 가실려면 0을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);


            if (userChoice != 0) {
                printOne(userChoice, id);
            }

            while (!validateValue(userChoice)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
            userViewer.showmenu();
        }
    }
    private void briefList(ArrayList<MovieDTO> list){
        for(MovieDTO m : list){
            System.out.printf("%d, %s\n", m.getMovieId(),m.getTitle());
        }
    }
    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return movieController.selectOne(value) != null;
    }
    public void printOne(int id, int userId){
        MovieDTO m = movieController.selectOne(id);
        ArrayList<UserDTO> u  = userController.selectAll();
        System.out.println("==============================");
        System.out.printf("영화 번호 :%d, 영화 제목 : %s\n 영화 등급 : %s\n", id, m.getTitle(), m.getRestrict());
        System.out.println("------------------------------");
        System.out.println("줄거리: "+m.getStory());
        System.out.println("==============================");
        String message="평점을 확인하려면 1번 뒤로가려면 아무키나 눌러주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice == 1){
            //평점;
            reviewViewer.reviewList(id, userId);
        }
    }

}
