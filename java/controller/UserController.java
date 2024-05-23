package controller;

import model.UserDTO;
import java.util.ArrayList;

public class UserController {
    private ArrayList<UserDTO> list;
    private int nextId;

    public UserController(){
        list = new ArrayList<>();
        nextId = 0;
    }
    public ArrayList<UserDTO> selectAll(){return list;}
    public void insert(UserDTO userDTO){
        userDTO.setId(nextId++);
        list.add(userDTO);
    }
    //중복검증
    public boolean validateUsername(String username){
        for(UserDTO userDTO : list){
            if(userDTO.getUsername().equalsIgnoreCase(username)){
                return false;
            }
        }
        return true;
    }

    public UserDTO selectOne(int id){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        if(list.contains(userDTO)){
            return list.get(list.indexOf(userDTO));
        }
        return null;
    }


    public void update(UserDTO userDTO){
        list.set(list.indexOf(userDTO), userDTO);
    }

    public void delete(int id){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        list.remove(userDTO);
    }
    public UserDTO login(String username, String password){
        for(UserDTO userDTO : list){
            if(username.equalsIgnoreCase(userDTO.getUsername())){
                if(password.equals(userDTO.getPassword())){
                    return userDTO;
                }
            }
        }
        return null;
    }

    public String selectNicknameById(int id){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);

        return list.get(list.indexOf(userDTO)).getNickname();
    }
    public void setGrade0(int id, int grade){
        selectOne(id).setGrade(grade);
    }
}
