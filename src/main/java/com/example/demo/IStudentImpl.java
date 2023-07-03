package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.List;
import java.util.Map;

@Service
public class IStudentImpl implements  IStudent{
    @Resource
    public StudentMapper studentMapper;

    public Gson gson = new Gson();


    @Override
    public String getById(String sid) {


        Student student = studentMapper.selectByPrimaryKey(sid);
        return gson.toJson(student);
    }



    public String getAll(){


        PageHelper.startPage(1, 3,true);

        List<Student> student = studentMapper.selectAll();

        return gson.toJson(new PageInfo(student));
    }

    public String deleteById(String sid){
        studentMapper.deleteByPrimaryKey(sid);
        return "success";

    }


    @Override
    public List < Student > queryStudentsByArray(int currPage, int pageSize)
    {
        //查询全部数据
        List < Student > students = studentMapper.queryStudentsByArray();
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = currPage * pageSize;
        return students.subList(firstIndex, lastIndex); //直接在list中截取
    }



}
