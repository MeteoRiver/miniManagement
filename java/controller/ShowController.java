package controller;
//상영정보

import model.ShowDTO;

import java.util.ArrayList;

public class ShowController {
    private ArrayList<ShowDTO> list;
    private int nextId;

    public ShowController(){
        list = new ArrayList<>();
        nextId = 1;
    }

    public ArrayList<ShowDTO> selectAll(){
        return list;
    }

    public ShowDTO selectOne(int id){
        ShowDTO showDTO = new ShowDTO();
        showDTO.setShowId(id);
        if(list.contains(showDTO)){
            return list.get(list.indexOf(showDTO));
        }
        return null;
    }
    public void update(ShowDTO showDTO){
        list.set(list.indexOf(showDTO), showDTO);
    }

    public void delete(int id){
        ShowDTO showDTO = new ShowDTO();
        showDTO.setShowId(id);
        list.remove(showDTO);
    }

    public void insert(ShowDTO showDTO){
        showDTO.setShowId(nextId++);
        list.add(showDTO);
    }

}
