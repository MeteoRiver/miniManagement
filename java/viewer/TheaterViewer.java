package viewer;
//극장정보

import controller.TheaterConrtoller;
import lombok.Setter;
import model.TheaterDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class TheaterViewer {
    @Setter
    private TheaterConrtoller theaterController;
    @Setter
    private TheaterDTO theaterDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private ShowViewer showViewer;
    @Setter
    private UserViewer userViewer;

    public void theaterList() {
        ArrayList<TheaterDTO> list = theaterController.selectAll();
        if (list.isEmpty()) {
            System.out.println("극장 목록이 존재하지 않습니다.");
            userViewer.showmenu();
        }
        else {
            briefList(list);
            String message = "상세정보를 확인 할 극장의 번호나 뒤로 가실려면 -1을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if(userChoice == -1) {
                userViewer.showmenu();
            }
            while (!validateValue(userChoice)) {
                System.out.println("잘못 입력하셨습니다.");
                userChoice = ScannerUtil.nextInt(scanner, message);
            }
            if (userChoice != -1) {
                printOne(userChoice);
            }
        }
    }
    private void briefList(ArrayList<TheaterDTO> list){
        for(TheaterDTO m : list){
            System.out.printf("극장 번호 : %d, 극장 이름 : %s\n", m.getTheaterId(),m.getTheaterName());
        }
    }
    private boolean validateValue(int value) {
        if (value == 0) {
            return true;
        }

        return theaterController.selectOne(value) != null;
    }
    public void printOne(int id){
        TheaterDTO t = theaterController.selectOne(id);
        System.out.println("==============================");
        System.out.printf("극장 번호 :%d, 극장 이름 : %s\n", id, t.getTheaterName());
        System.out.println("------------------------------");
        System.out.println("극장주소: "+t.getTheaterAddress());
        System.out.println("극장 전화번호: "+t.getTheaterNum());
        System.out.println("==============================");
        String message="상영정보를 확인하려면 1번 뒤로가려면 아무키나 눌러주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice == 1){
            //상영정보;
            showViewer.showList(t.getTheaterId());
        }
    }

}
