package viewer;

import controller.MovieController;
import controller.ReviewController;
import controller.UserController;
import lombok.Setter;
import model.MovieDTO;
import model.ReviewDTO;
import model.UserDTO;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

//리뷰정보
public class ReviewViewer {
    @Setter
    private ReviewController reviewController;
    @Setter
    private MovieController movieController;
    @Setter
    private UserViewer userViewer;
    @Setter
    private ReviewDTO reviewDTO;
    @Setter
    private Scanner scanner;
    @Setter
    private UserDTO userDTO;
    @Setter
    private MovieViewer movieViewer;
    @Setter
    private UserController userController;
    public void reviewList(int movieId, int userId) {
        ArrayList<ReviewDTO> list = reviewController.selectAll();
        ArrayList<UserDTO> u = userController.selectAll();
        if (list.isEmpty()) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("전체 평점 목록입니다.");
            System.out.println("평점 목록이 존재하지 않습니다.");
        }
        else {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
            System.out.println("전체 평점 목록입니다.");
            briefList(list,movieId);
        }
            String message = "전문가 평점은 2를 일반인 평점은 1을 평점을 입력하시려면 0 뒤로 가실려면 -1을 입력해주세요.";
            int userChoice = ScannerUtil.nextInt(scanner, message);


            if (userChoice == 0) {
                if(u.get(userId).getGrade()==2){
                    insert1(movieId, userId);
                } else {
                    insert2(movieId, userId);
                }
            }
            else if(userChoice ==-1) {
                movieViewer.movieList(userId);
            }
            else if(userChoice == 2) {
                //전문가 평점
                listAd(list,movieId,userId);
            }
            else if (userChoice == 1) {
                //일반인 평점
                listNo(list,movieId,userId);
            }
            else{
                System.out.println("잘못 입력하셨습니다.");
            }
            reviewList(movieId,userId);
    }
    private void briefList(ArrayList<ReviewDTO> list,int movieId) {
        double sum = 0;
        for (ReviewDTO r : list) {
            if (r.getMovieID() == movieId){
                sum += r.getScore();
            System.out.printf("번호: %d, 유저번호: %s, 영화번호: %s, 평점: %s\n", r.getId(), r.getUserId(), r.getMovieID(), r.getScore());
        }
    }
        System.out.println("평균 평점은 : "+ sum);
    }
    private void listAd(ArrayList<ReviewDTO> list,int movieId,int userId){
        ArrayList<UserDTO> u = userController.selectAll();

        double sum=0;
        int count=0;
        for(ReviewDTO r : list){
            if(u.get(r.getUserId()).getGrade()==2) {
                if (r.getMovieID() == movieId) {
                    sum += r.getScore();
                    count++;
                    System.out.printf("번호: %d, 유저번호: %s, 영화번호: %s, 평점: %s\n", r.getId(), r.getUserId(), r.getMovieID(), r.getScore());
                }
            }
        }
        String message = "상세보기할 평론의 번호나 뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);

        if(userChoice == 0) {
            reviewList(movieId, userId);
        }
        else if(userChoice >0&&userChoice <= list.size()) {
            printOne(userChoice);
            listAd(list,movieId,userId);
        }
        else{
            System.out.println("잘못된 입력입니다.");
            listAd(list,movieId,userId);
        }
        System.out.println("평균 평점은 : "+ sum/count);
    }

    private void listNo(ArrayList<ReviewDTO> list,int movieId,int userId) {
        ArrayList<UserDTO> u = userController.selectAll();
        double sum = 0;
        int count = 0;
        for (ReviewDTO r : list) {
            if (u.get(r.getUserId()).getGrade() == 3) {
                if (r.getMovieID() == movieId) {
                    sum += r.getScore();
                    System.out.printf("번호: %d, 유저번호: %s, 영화번호: %s, 평점: %s\n", r.getId(), r.getUserId(), r.getMovieID(), r.getScore());
                    count++;
                }

            }
        }
        if (sum > 0) {
            System.out.println("평균 평점은 : " + sum / count);
        }
        else{
        System.out.println("평점이 존재하지 않습니다.");
    }
        String message = "뒤로 가실려면 0을 입력해주세요.";
        int userChoice = ScannerUtil.nextInt(scanner, message);
        if(userChoice == 0) {reviewList(movieId,userId);}
        else{System.out.println("잘못된 입력입니다.");
        listNo(list,movieId,userId);
        }


    }
    private void insert1(int movieid, int userid){
        ReviewDTO r = new ReviewDTO();
        ArrayList<UserDTO> u = userController.selectAll();
        ArrayList<MovieDTO> m =movieController.selectAll();
        ArrayList<ReviewDTO> r1 = reviewController.selectAll();
        String message;
        message = "평점을 입력해주세요 (1~10)";
        r.setScore(ScannerUtil.nextInt(scanner, message,1,10));
        message = "평론을 입력해주세요";
        r.setContent(ScannerUtil.nextLine(scanner, message));
        r.setUserId(u.get(userid).getId());
        for(int i =0; i<r1.size(); i++) {
            if(r1.get(i).getMovieID()==movieid) {
                if (r1.get(i).getUserId() == userid) {
                    System.out.println("이미 평점을 입력하셨습니다.");
                    reviewList(movieid, userid);
                }
            }
        }
        r.setMovieID(movieid);
        reviewController.insert(r);
        reviewList(movieid, userid);
    }
    private void insert2(int movieid, int userid){
        ReviewDTO r = new ReviewDTO();
        ArrayList<UserDTO> u = userController.selectAll();
        ArrayList<MovieDTO> m =movieController.selectAll();
        ArrayList<ReviewDTO> r1 = reviewController.selectAll();
        String message;
        message = "평점을 입력해주세요 (1~10)";
        r.setScore(ScannerUtil.nextInt(scanner, message,1,10));
        r.setContent(" ");
        r.setUserId(u.get(userid).getId());
        for(int i =0; i<r1.size(); i++) {
            if(r1.get(i).getMovieID()==movieid) {
                if (r1.get(i).getUserId() == userid) {
                    System.out.println("이미 평점을 입력하셨습니다.");
                    reviewList(movieid, userid);
                }
            }
        }
        r.setMovieID(movieid);
        reviewController.insert(r);
        reviewList(movieid, userid);
    }
    public void printOne(int id) {
        ArrayList<ReviewDTO> l = reviewController.selectAll();
        ArrayList<UserDTO> us = userController.selectAll();
        ReviewDTO r = reviewController.selectOne(id);
        UserDTO u = userController.selectOne(id);

            if(us.get(r.getUserId()).getGrade()==2) {
                 System.out.println("==============================");
                 System.out.printf("번호 : %d, 유저번호 : %d, 영화 번호 : %d\n", r.getId(), r.getUserId(), r.getMovieID());
                 System.out.println("------------------------------");
                 System.out.println("평론: " + r.getContent());
                 System.out.println("==============================");
                 System.out.println("뒤로가려면 아무키나 눌러주세요.");
            }
        else{
            String message ="보기에 해당하는 번호로 다시 입력해주세요";
            int userId=ScannerUtil.nextInt(scanner, message);
            if(userId>0&&userId<=l.size()) {
                printOne(userId);
            }
            else{
                message ="잘못된 번호입니다.";
                userId=ScannerUtil.nextInt(scanner, message);
                printOne(userId);
            }
        }
    }
}
