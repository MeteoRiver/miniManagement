package viewer;

import controller.MovieController;
import controller.ReviewController;
import controller.ShowController;
import lombok.Setter;
import model.MovieDTO;
import model.ReviewDTO;
import model.ShowDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieViewerAdmin {
    // 현재 영화 리스트 출력
    @Setter
    private MovieController movieController;
    @Setter
    private MovieDTO movieDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private UserViewer userViewer;
    @Setter
    private ShowController showController;
    @Setter
    private ReviewController reviewController;
    public void movieList() {
        ArrayList<MovieDTO> list = movieController.selectAll();
        if (list.isEmpty()) {
            System.out.println("영화 목록이 존재하지 않습니다.");
        }
        else {
            briefList(list);
            }
            String message = "상세보기할 영화의 번호나 추가를 하시려면 0을 뒤로 돌아가시려면 -1을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == -1) {
                userViewer.adminMenu();
            }

            else if(userChoice == 0) {
                insert();
            }
            else if(userChoice <-1||userChoice >list.size()){
                System.out.println("잘못된 입력입니다.");
                movieList();
            }
            else if (userChoice != 0&&userChoice != -1) {
            printOne(userChoice);
            }
            }
    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return movieController.selectOne(value) != null;
    }

    public void briefList(ArrayList<MovieDTO> list){
        for(MovieDTO m : list){
            System.out.printf("영화 번호: %d. 영화 제목: %s\n", m.getMovieId(),m.getTitle());
        }
    }

    public void printOne(int id){
        MovieDTO m = movieController.selectOne(id);
        System.out.println("==============================");
        System.out.printf("영화 번호 :%d, 영화 제목 : %s\n 영화 등급 : %s\n", id, m.getTitle(), m.getRestrict());
        System.out.println("------------------------------");
        System.out.println("줄거리: "+m.getStory());
        System.out.println("==============================");
        String message="1. 수정 2. 삭제, 뒤로가려면 아무키나 눌러주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice == 1){
            update(id);
        }
        else if(userChoice == 2){
            delete(id);
        }
        movieList();
    }

    public void insert(){
        MovieDTO m = new MovieDTO();

        String message;
        message = "추가하실 영화 제목을 입력해주세요.";
        m.setTitle(ScannerUtil.nextLine(scanner, message));
        message = "추가하실 영화의 줄거리를 입력해주세요";
        m.setStory(ScannerUtil.nextLine(scanner, message));
        message = "추가하실 영화의 등급을 입력해주세요";
        m.setRestrict(ScannerUtil.nextLine(scanner, message));
        movieController.insert(m);
        movieList();
    }

    public void update(int id){
        MovieDTO m = movieController.selectOne(id);
        String message;

        message = "수정하실 영화 제목을 입력해주세요.";
        m.setTitle(ScannerUtil.nextLine(scanner, message));
        message = "수정하실 영화의 줄거리를 입력해주세요";
        m.setStory(ScannerUtil.nextLine(scanner, message));
        message = "수정하실 영화의 등급을 입력해주세요";
        m.setRestrict(ScannerUtil.nextLine(scanner, message));
        movieController.update(m);
        printOne(id);
    }
    public void delete(int id){
        ArrayList<ShowDTO> list = showController.selectAll();
        ArrayList<ReviewDTO> r = reviewController.selectAll();
        String answer = ScannerUtil.nextLine(scanner, "정말로 삭제하시겠습니까? Y/N");
        if (answer.equalsIgnoreCase("Y")) {
            movieController.delete(id);
            if(list!=null&&!list.isEmpty()) {
                for(int i=list.size()-1; i>=0; i--) {
                    if (list.get(i).getMovieId() == id) {
                        System.out.println(list.get(i).getShowId());
                        showController.delete(list.get(i).getShowId());
                    }
                }
            }
            if(r!=null&&!r.isEmpty()) {
                for(int i=r.size()-1; i>=0; i--) {
                    if (r.get(i).getMovieID() == id) {
                        System.out.println(r.get(i).getId());
                        reviewController.delete(r.get(i).getId());
                    }
                }
            }

            movieList();
        }else {
            printOne(id);
        }
    }

}
