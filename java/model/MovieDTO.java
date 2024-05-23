package model;

import lombok.Data;

//영화정보
@Data
public class MovieDTO {
    //영화번호
    private int movieId;
    //영화제목
    private String title;
    //영화줄거리
    private String story;
    //영화등급
    private String restrict;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof MovieDTO) {
            MovieDTO u = (MovieDTO) o;
            return movieId == u.movieId;
        }

        return false;
    }



}
