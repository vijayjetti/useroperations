package com.vijay.learning.useroperations.constants;

public class ApiEndPoints {
    public static final String BASE_URL = "/api/v1";
    
    // User endpoints
    public static final String ADD_USER = "/user";
    public static final String GET_USER = "/user/{id}";
    public static final String UPDATE_USER = "/user/{id}";
    public static final String DELETE_USER = "/user/{id}";
    public static final String GET_USER_LIST = "/users";
    public static final String CHECK_EXISTING_USER = "/user/exists/{id}";
    
    // Employee endpoints
    public static final String ADD_EMPLOYEE = "/employee";
    public static final String GET_EMPLOYEE = "/employee/{id}";
    public static final String UPDATE_EMPLOYEE = "/employee/{id}";
    public static final String DELETE_EMPLOYEE = "/employee/{id}";
    public static final String GET_EMPLOYEE_LIST = "/employees";
    public static final String CHECK_EXISTING_EMPLOYEE = "/employee/exists/{id}";
}