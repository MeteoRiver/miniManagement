package viewer;

import controller.TheaterConrtoller;
import controller.UserController;
import lombok.Setter;
import model.TheaterDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class UserViewer {
    @Setter
    private UserController userController;
    @Setter
    private Scanner scanner;
    @Setter
    private UserDTO userDTO;
    @Setter
    private MovieViewerAdmin movieViewerAdmin;
    @Setter
    private TheaterViewerAdmin theaterViewerAdmin;
    @Setter
    private MovieViewer movieViewer;
    @Setter
    private TheaterViewer theaterViewer;
    @Setter
    private ShowViewerAdmin showVieweradmin;
    @Setter
    private TheaterConrtoller theaterController;

    public void loginMenu(){
        String message = "1. 로그인 2. 회원가입 3. 프로그램 종료";

        while(true){
            int userChoiceLogin = ScannerUtil.nextInt(scanner, message);
            if(userChoiceLogin == 1){
                //로그인
                auth();
                if(userDTO!=null){
                    //회원메뉴 실행
                    if (userDTO.getGrade()==3) {
                    //일반회원일때
                        showmenu();
                    }
                    else if(userDTO.getGrade()==2){
                        //전문 평론가 일때
                        showmenu();
                    }
                    else if (userDTO.getGrade()==1){
                        //관리자일때
                        adminMenu();
                    }
                }
            }
            else if(userChoiceLogin == 2){
                //회원가입
                register();
            }
            else if(userChoiceLogin == 3){
                System.out.println("프로그램을 종료합니다. 사용해주셔서 감사합니다.");
                System.exit(0);
            }

        }
    }

    private void auth(){
        String message;
        message = "아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(scanner, message);
        message = "비밀번호를 입력해주세요.";
        String password = ScannerUtil.nextLine(scanner, message);

        userDTO = userController.login(username, password);
        if (userDTO == null){
            System.out.println("잘못 입력하셨습니다. 로그인 정보를 다시 확인해주세요.");
        }
    }
    public void admin(){
        UserDTO temp = new UserDTO();
        temp.setGrade(1);
        temp.setUsername("admin");
        temp.setPassword("admin");
        temp.setNickname("admin");
        userController.insert(temp);
    }

    private void register(){
        String message;
        message="사용하실 아이디를 입력해주세요.";
        String username = ScannerUtil.nextLine(scanner, message);
        //중복 검중
        if (userController.validateUsername(username)){
            //정상작동
            message="사용하실 비밀번호를 입력해주세요.";
            String password = ScannerUtil.nextLine(scanner, message);
            message="사용하실 닉네임을 입력해주세요.";
            String nickname = ScannerUtil.nextLine(scanner, message);

            UserDTO temp = new UserDTO();
            temp.setUsername(username);
            temp.setPassword(password);
            temp.setNickname(nickname);
            temp.setGrade(3);

            userController.insert(temp);
        }
        else{
            //중복일때
            System.out.println("중복된 아이디는 사용하실 수 없습니다.");
        }
    }
    //로그인 이후 관리자 메뉴
    public void adminMenu(){
        System.out.println("관리자 메뉴입니다. 번호를 입력해주세요");
        String message = "1. 회원 권한관리 2. 영화 관리 3. 극장 관리 4. 상영정보 관리 5. 뒤로가기";
        int admin = ScannerUtil.nextInt(scanner, message,1, 5);
        if(admin == 1){
            adminManagement();
        }
        else if(admin == 2){
            movieViewerAdmin.movieList();
        }
        else if(admin == 3){
            theaterViewerAdmin.theaterList();
        }
        else if(admin == 4){
            ArrayList<TheaterDTO> list = theaterController.selectAll();
            theaterViewerAdmin.briefList(list);
            message="극장번호를 입력하세요";
            int num=ScannerUtil.nextInt(scanner, message)-1;
            showVieweradmin.showListadmin(num);
        }
        else if(admin == 5){
            loginMenu();
        }
        adminMenu();
    }

    public void adminManagement() {
        ArrayList<UserDTO> list = userController.selectAll();
        briefList(list);

        try {

            String message = "수정할 회원의 번호를 입력해주세요, 뒤로가시려면 -1을 입력해주세요.";
            int adminChoice = ScannerUtil.nextInt(scanner, message,-1,list.size()-1);
            if(adminChoice == -1){
                adminMenu();
            }
            if(adminChoice > list.size()-1||adminChoice <-1||adminChoice==0){
                System.out.println("잘못된 입력입니다.");
                adminManagement();
            }
            //회원 등급 수정
            message = "수정하실 권한의 번호를 입력해주세요 (1 - 관리자, 2 - 전문평론가,3 - 일반인)";
            int adminNum = ScannerUtil.nextInt(scanner, message, 1, 3);

            if (adminNum <= 3 && adminNum >= 1) {
                userController.setGrade0(adminChoice, adminNum);
                if (userDTO.getGrade()!=1) {loginMenu();}
                else{adminMenu();}
            }
            //나가기
            else {
                System.out.println("관리자 메뉴를 종료합니다");
            }
        }
        catch (NullPointerException e) {
            System.out.println("비정상적인 접근입니다. 초기화면으로 돌아갑니다.");
            loginMenu();
        }

    }


    public void showmenu(){
        //영화 ->평점
        //극장 -> 상영정보
        //상영정보 -> 극장
        //상영정보 -> 영화
        String message = "1. 영화 확인 2. 극장 확인 \n로그인 키로 돌아가려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice==1){
            //영화 목록
            movieViewer.movieList(userDTO.getId());
        }
        else if(userChoice==2){
            //극장 목록
            theaterViewer.theaterList();
        }
        loginMenu();
    }

    private void briefList(ArrayList<UserDTO> list) {
        for (UserDTO u : list) {
            System.out.printf("%d. %s, %d\n", u.getId(), u.getUsername(), u.getGrade());
        }
    }
}
