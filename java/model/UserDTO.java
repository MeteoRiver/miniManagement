package model;

import lombok.Data;

@Data
public class UserDTO {
    //key value
    private int id;
    //id저장
    private String username;
    //pw저장
    private String password;
    //닉네임 저장
    private String nickname;
    //권한 1 - 관리자 2 - 전문평론가 3 - 일반계정
    private int grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof UserDTO) {
            UserDTO u = (UserDTO) o;
            return id == u.id;
        }

        return false;
    }

}
