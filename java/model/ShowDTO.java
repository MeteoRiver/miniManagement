package model;

import lombok.Data;
import model.MovieDTO;
import model.TheaterDTO;
//상영정보
@Data
public class ShowDTO {
    //상영정보 번호
    private int showId;
    //영화 번호
    private int movieId;
    //극장 번호
    private int theaterId;
    //상영시간
    private String showTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ShowDTO) {
            ShowDTO u = (ShowDTO) o;
            return showId == u.showId;
        }

        return false;
    }
}
