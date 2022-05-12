package com.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String avatar;
    private List<Long> roleIdList;
}
