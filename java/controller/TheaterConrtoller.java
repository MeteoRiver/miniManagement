package controller;

import model.TheaterDTO;

import java.util.ArrayList;

//극장정보
public class TheaterConrtoller {
    private ArrayList<TheaterDTO> list;
    private int nextId;

    public TheaterConrtoller(){
        list = new ArrayList<>();
        nextId = 1;
    }
    public ArrayList<TheaterDTO> selectAll(){
        return list;
    }
    public TheaterDTO selectOne(int id){
        TheaterDTO theaterDTO = new TheaterDTO();
        theaterDTO.setTheaterId(id);
        if(list.contains(theaterDTO)){
            return list.get(list.indexOf(theaterDTO));
        }
        return null;
    }
    public void update(TheaterDTO movieDTO){
        list.set(list.indexOf(movieDTO), movieDTO);
    }

    public void delete(int id){
        TheaterDTO movieDTO = new TheaterDTO();
        movieDTO.setTheaterId(id);
        list.remove(movieDTO);
    }

    public void insert(TheaterDTO theaterDTO){
        theaterDTO.setTheaterId(nextId++);
        list.add(theaterDTO);
    }
    public String selectTitleById(int id){
        TheaterDTO theaterDTO = new TheaterDTO();
        theaterDTO.setTheaterId(id);

        return list.get(list.indexOf(theaterDTO)).getTheaterName();
    }
    //중복검증
    public boolean validateTitle(String name){
        for(TheaterDTO theaterDTO : list){
            if(theaterDTO.getTheaterName().equalsIgnoreCase(name)){
                return false;
            }
        }
        return true;
    }
}
