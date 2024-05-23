package controller;
//리뷰정보

import model.ReviewDTO;
import model.ShowDTO;
import model.UserDTO;

import java.util.ArrayList;

public class ReviewController {
    private ArrayList<ReviewDTO> list;
    private int nextId;

    public ReviewController(){
        list = new ArrayList<>();
        nextId = 1;
    }
    public ArrayList<ReviewDTO> selectAll(){return list;}

    public void insert(ReviewDTO reviewDTO){
        reviewDTO.setId(nextId++);
        list.add(reviewDTO);
    }
    public void delete(int id){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(id);
        list.remove(reviewDTO);
    }
    public ReviewDTO selectOne(int id){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(id);
        if(list.contains(reviewDTO)){
            return list.get(list.indexOf(reviewDTO));
        }
        return null;
    }

}
