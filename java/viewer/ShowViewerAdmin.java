package viewer;

import controller.MovieController;
import controller.ShowController;
import controller.TheaterConrtoller;
import lombok.Setter;
import model.MovieDTO;
import model.ReviewDTO;
import model.ShowDTO;
import model.TheaterDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

//상영정보
public class ShowViewerAdmin {
    @Setter
    private ShowController showController;
    @Setter
    private TheaterDTO theaterDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private MovieController movieController;
    @Setter
    private TheaterConrtoller theaterController;
    @Setter
    private UserViewer userViewer;
    @Setter
    private MovieViewer movieViewer;
    @Setter
    private MovieViewerAdmin movieViewerAdmin;

    public void showListadmin(int id) {//id는 극장 번호
        ArrayList<ShowDTO> list = showController.selectAll();
        ArrayList<TheaterDTO> theaters = theaterController.selectAll();
        ArrayList<MovieDTO> movies = movieController.selectAll();

        try{if(theaters.get(id) == null){}}
        catch (IndexOutOfBoundsException e) {
            System.out.println("해당 극장이 존재하지 않습니다.");
            userViewer.adminMenu();
        }
        if (list.isEmpty()) {
            System.out.println("해당 극장에 상영 목록이 존재하지 않습니다.");
        }

        else {briefList(list);}
            String message = "상세보기 할 번호를 입력하시거나 정보를 추가하시려면 0 뒤로가시려면 -1을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);


            if (userChoice == -1) {
                //뒤로가기
                userViewer.adminMenu();
            }
            else if(userChoice == 0){
                insert(theaters.get(id).getTheaterId());
            }
            else if(userChoice <-1||userChoice >list.size()){
                System.out.println("잘못된 입력입니다. 메뉴 화면으로 돌아갑니다");
                userViewer.adminMenu();
            }
            else if(userChoice !=-1&&userChoice!=0){
                printOne(userChoice, theaters.get(id).getTheaterId());
            }

    }
    public void insert(int id) {
        ShowDTO s = new ShowDTO();
        ArrayList<TheaterDTO> theaters = theaterController.selectAll();

        String message;
        ArrayList<MovieDTO> list = movieController.selectAll();
        movieViewerAdmin.briefList(list);
        s.setTheaterId(theaters.get(id-1).getTheaterId());
        message = "영화 번호를 입력해주세요.";
        int a=ScannerUtil.nextInt(scanner, message);
        if(a>list.size()||a<1){
            System.out.println("잘못된 입력입니다.");
            insert(id);
        }
        else {
            s.setMovieId(a);
        }
        message = "상영정보를 입력해주세요.";
        s.setShowTime(ScannerUtil.nextLine(scanner,message));

        showController.insert(s);
        showListadmin(id-1);
    }
    private void briefList(ArrayList<ShowDTO> list){
        //극장번호 받아서 해당하는거만 출력
        for(ShowDTO s : list){
            System.out.printf("번호:%d. 극장 번호 : %d, 영화 번호 : %d, 상영정보 : %s\n", s.getShowId(),s.getTheaterId(),s.getMovieId(),s.getShowTime());
        }
    }
    public void printOne(int id,int theaterId){
        ShowDTO s = showController.selectOne(id);
        System.out.println("==============================");
        System.out.println("------------------------------");
        System.out.printf("번호:%d. 극장 번호 : %d, 영화 번호 : %d, 상영정보 : %s\n",s.getShowId(), s.getTheaterId(),s.getMovieId(),s.getShowTime());
        System.out.println("==============================");
        String message="1. 수정 2. 삭제, 메인메뉴로 돌아가려면 아무 숫자키를 눌러주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice == 1){
            update(id, theaterId);
        }
        else if(userChoice == 2){
            delete(id, theaterId);
        }
        else{
            System.out.println("메인메뉴로 돌아갑니다.");
            userViewer.adminMenu();
        }
    }
    public void update(int id, int movieId){
        ShowDTO s = showController.selectOne(id);
        ArrayList<TheaterDTO> theaters = theaterController.selectAll();

        String message;
        ArrayList<MovieDTO> list = movieController.selectAll();
        movieViewerAdmin.briefList(list);
        s.setTheaterId(theaters.get(id-1).getTheaterId());
        message = "수정하실 영화 번호를 입력해주세요.";
        int a = ScannerUtil.nextInt(scanner, message);
        if(a>list.size()||a<1){
            System.out.println("잘못된 입력입니다.");
            update(id, movieId);
        }
        else {
            s.setMovieId(a);
        }
        message = "수정하실 상영정보를 입력해주세요.";
        s.setShowTime(ScannerUtil.nextLine(scanner,message));

        showController.update(s);
        printOne(id, movieId);

    }
    public void delete(int id, int movieId){
        String answer = ScannerUtil.nextLine(scanner, "정말로 삭제하시겠습니까? Y/N");
        if (answer.equalsIgnoreCase("Y")) {
            showController.delete(id);
            userViewer.adminMenu();
        } else {
            showListadmin(movieId);
        }
    }

    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return showController.selectOne(value) != null;
    }
}
