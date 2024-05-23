package controller;

import model.MovieDTO;

import java.lang.management.MonitorInfo;
import java.util.ArrayList;

//영화정보
public class MovieController {
    private ArrayList<MovieDTO> list;
    private int nextId;

    public MovieController(){
        list = new ArrayList<>();
        nextId = 1;
    }
    public ArrayList<MovieDTO> selectAll(){
        return list;
    }
    public MovieDTO selectOne(int id){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(id);
        if(list.contains(movieDTO)){
            return list.get(list.indexOf(movieDTO));
        }
        return null;
    }
    public void update(MovieDTO movieDTO){
        list.set(list.indexOf(movieDTO), movieDTO);
    }

    public void delete(int id){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(id);
        list.remove(movieDTO);
    }

    public void insert(MovieDTO movieDTO){
        movieDTO.setMovieId(nextId++);
        list.add(movieDTO);
    }
    public String selectTitleById(int id){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId(id);

        return list.get(list.indexOf(movieDTO)).getTitle();
    }
    //중복검증
    public boolean validateTitle(String title){
        for(MovieDTO movieDTO : list){
            if(movieDTO.getTitle().equalsIgnoreCase(title)){
                return false;
            }
        }
        return true;
    }
}
