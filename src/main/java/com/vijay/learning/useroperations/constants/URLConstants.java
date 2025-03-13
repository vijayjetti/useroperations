package com.vijay.learning.useroperations.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLConstants {

    public static final String BASE_URL="/user" ;
    public static final String ADD_USER = "/addUser";
    public static final String GET_USER = "getUser/{id}";
    public static final String UPDATE_USER = "/updateUser/{id}";
    public static final String DELETE_USER = "/deleteUser/{id}";
    public static final String GET_USER_LIST = "/users";
    public static final String CHECK_EXISTING_USER = "/isExistingUser/{id}";
    public static final String USER_SUCCESS_RESPONSE = "/success";
    public static final String USER_FAILUER_RESPONSE = "/failure";
}
