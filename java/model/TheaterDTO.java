package model;

import lombok.Data;

//극장정보
@Data
public class TheaterDTO {
    //극장번호
    private int theaterId;
    //극장이름
    private String theaterName;
    //극장주소
    private String theaterAddress;
    //극장전화번호
    private int theaterNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof TheaterDTO) {
            TheaterDTO u = (TheaterDTO) o;
            return theaterId == u.theaterId;
        }

        return false;
    }
}
