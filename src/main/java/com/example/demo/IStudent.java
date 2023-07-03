package com.example.demo;

import com.example.demo.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IStudent {

    public String  getById(String sid);


    public String getAll();

    public String deleteById(String sid);

    List< Student > queryStudentsByArray(int currPage, int pageSize);
}
