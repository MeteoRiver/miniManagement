package model;

import lombok.Data;

//리뷰정보
@Data
public class ReviewDTO {
    //리뷰 번호
    private int id;
    //회원 번호
    private int UserId;
    //영화 번호
    private int MovieID;
    //평점
    private double score;
    //평론
    private String Content;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ReviewDTO) {
            ReviewDTO u = (ReviewDTO) o;
            return id == u.id;
        }

        return false;
    }
}
