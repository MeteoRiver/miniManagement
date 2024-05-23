package main;

import controller.*;
import model.MovieDTO;
import viewer.*;

import java.util.Scanner;

public class MovieMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UserController userController = new UserController();
        MovieController movieController = new MovieController();
        ReviewController reviewController = new ReviewController();
        TheaterConrtoller theaterController = new TheaterConrtoller();
        ShowController showController = new ShowController();

        UserViewer userVierwer = new UserViewer();
        ReviewViewer reviewViewer = new ReviewViewer();
        TheaterViewer theaterViewer = new TheaterViewer();
        ShowViewer showViewer = new ShowViewer();
        MovieViewer movieViewer = new MovieViewer();
        MovieViewerAdmin movieViewerAdmin = new MovieViewerAdmin();
        TheaterViewerAdmin theaterVieweAdmin = new TheaterViewerAdmin();
        ShowViewerAdmin showVieweAdmin = new ShowViewerAdmin();

        userVierwer.setScanner(scanner);
        userVierwer.setUserController(userController);
        userVierwer.setMovieViewer(movieViewer);
        userVierwer.setShowVieweradmin(showVieweAdmin);
        userVierwer.setTheaterViewer(theaterViewer);
        userVierwer.setMovieViewerAdmin(movieViewerAdmin);
        userVierwer.setTheaterViewerAdmin(theaterVieweAdmin);
        userVierwer.setTheaterController(theaterController);

        reviewViewer.setScanner(scanner);
        reviewViewer.setReviewController(reviewController);
        reviewViewer.setMovieViewer(movieViewer);
        reviewViewer.setUserController(userController);
        reviewViewer.setUserViewer(userVierwer);
        reviewViewer.setMovieController(movieController);

        theaterViewer.setScanner(scanner);
        theaterViewer.setTheaterController(theaterController);
        theaterViewer.setShowViewer(showViewer);
        theaterViewer.setUserViewer(userVierwer);

        showViewer.setScanner(scanner);
        showViewer.setShowController(showController);
        showViewer.setMovieViewer(movieViewer);
        showViewer.setTheaterViewer(theaterViewer);

        movieViewer.setScanner(scanner);
        movieViewer.setMovieController(movieController);
        movieViewer.setReviewViewer(reviewViewer);
        movieViewer.setUserController(userController);
        movieViewer.setUserViewer(userVierwer);

        movieViewerAdmin.setScanner(scanner);
        movieViewerAdmin.setMovieController(movieController);
        movieViewerAdmin.setUserViewer(userVierwer);
        movieViewerAdmin.setShowController(showController);
        movieViewerAdmin.setReviewController(reviewController);

        theaterVieweAdmin.setScanner(scanner);
        theaterVieweAdmin.setTheaterController(theaterController);
        theaterVieweAdmin.setUserViewer(userVierwer);
        theaterVieweAdmin.setMovieController(movieController);
        theaterVieweAdmin.setMovieViewerAdmin(movieViewerAdmin);
        theaterVieweAdmin.setShowController(showController);

        showVieweAdmin.setScanner(scanner);
        showVieweAdmin.setShowController(showController);
        showVieweAdmin.setMovieViewer(movieViewer);
        showVieweAdmin.setUserViewer(userVierwer);
        showVieweAdmin.setMovieController(movieController);
        showVieweAdmin.setTheaterController(theaterController);
        showVieweAdmin.setMovieViewerAdmin(movieViewerAdmin);


        userVierwer.admin();
        userVierwer.loginMenu();
    }
}
