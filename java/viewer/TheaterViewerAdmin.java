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


public class TheaterViewerAdmin {
    // 현재 극장 리스트 출력
    @Setter
    private TheaterConrtoller theaterController;
    @Setter
    private TheaterDTO theaterDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private UserViewer userViewer;
    @Setter
    private MovieViewerAdmin movieViewerAdmin;
    @Setter
    private MovieController movieController;
    @Setter
    private ShowController showController;
    public void theaterList() {
        ArrayList<TheaterDTO> list = theaterController.selectAll();
        if (list.isEmpty()) {
            System.out.println("극장 목록이 존재하지 않습니다.");
        }
        else {
            briefList(list);
        }
            String message = "상세보기할 극장의 번호나 추가를 하시려면 0을 뒤로가시려면 -1을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == -1) {
                userViewer.adminMenu();
            }

            if(userChoice == 0) {
                insert();
            }
            else if (userChoice<-1||userChoice>list.size()) {
                System.out.println("잘못된 입력입니다.");
                theaterList();
            }
            else if (userChoice != 0&&userChoice != -1) {
                printOne(userChoice);
            }
    }
    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return theaterController.selectOne(value) != null;
    }

    public void briefList(ArrayList<TheaterDTO> list){
        for(TheaterDTO m : list){
            System.out.printf("극장 번호 : %d 극장 이름 :%s\n", m.getTheaterId(),m.getTheaterName());
        }
    }

    public void printOne(int id){
        TheaterDTO t = theaterController.selectOne(id);
        System.out.println("==============================");
        System.out.println("------------------------------");
        System.out.println("극장 전화번호: "+t.getTheaterNum());
        System.out.println("극장 주소: "+t.getTheaterAddress());
        System.out.println("==============================");
        String message="1. 수정 2. 삭제, 뒤로가려면 아무숫자나 눌러주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice == 1){
            update(id);
        }
        else if(userChoice == 2){
            delete(id);
        }
        else {
            theaterList();
        }
    }

    // 추가하려면 0
    // 그 외에 수정 삭제하려면 해당 번호 누르기
    // 수정, 삭제

    public void insert(){
        TheaterDTO t = new TheaterDTO();

        String message;

        message = "추가하실 극장 이름을 입력해주세요.";
        t.setTheaterName(ScannerUtil.nextLine(scanner, message));
        message = "추가하실 극장의 주소를 입력해주세요";
        t.setTheaterAddress(ScannerUtil.nextLine(scanner, message));
        message = "추가하실 극장의 전화번호를 입력해주세요";
        t.setTheaterNum(ScannerUtil.nextInt(scanner, message));
        theaterController.insert(t);
        theaterList();
    }
    public void update(int id){
        TheaterDTO t = theaterController.selectOne(id);
        String message;

        message = "수정하실 극장의 이름을 입력해주세요.";
        t.setTheaterName(ScannerUtil.nextLine(scanner, message));
        message = "수정하실 극장의 주소를 입력해주세요";
        t.setTheaterAddress(ScannerUtil.nextLine(scanner, message));
        message = "수정하실 영화의 등급을 입력해주세요";
        t.setTheaterNum(ScannerUtil.nextInt(scanner, message));
        theaterController.update(t);
        printOne(id);
    }
    public void delete(int id){
        String answer = ScannerUtil.nextLine(scanner, "정말로 삭제하시겠습니까? Y/N");
        ArrayList<ShowDTO> list = showController.selectAll();
        if (answer.equalsIgnoreCase("Y")) {
            theaterController.delete(id);
            if(list!=null&&!list.isEmpty()) {
                for(int i=list.size()-1; i>=0; i--) {
                  if (list.get(i).getTheaterId() == id) {
                      System.out.println(list.get(i).getShowId());
                      showController.delete(list.get(i).getShowId());
                  }
              }
        }theaterList();
        } else {
            printOne(id);
        }
    }

}
