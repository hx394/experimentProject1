package com.example.demo.controller.test1;


import com.example.demo.entity.Student;
import com.example.demo.IStudent;
import com.example.demo.mapper.StudentMapper;

import jakarta.websocket.server.PathParam;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import jakarta.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/student")
public class SearchController {


    @Resource
    public IStudent studentService;

    @Resource
    public StudentMapper studentMapper;

    @GetMapping("/search")//    /{sid}
    public String hello(@RequestParam("sid")String  sid){
        return studentService.getById(sid);
    }
//                        @PathParam("sid")
    @GetMapping("/getAll")
    public String selectAll()
    {
        log.info("测试日志输出");
       return studentService.getAll();
    }

    @PostMapping("/delete")
    public String delete(@RequestBody Student student){
        return studentService.deleteById(student.getsId());
    }

    @ResponseBody
    @RequestMapping("array/{currPage}/{pageSize}")
    public List < Student > getStudentByArray(@PathVariable("currPage") int currPage, @PathVariable("pageSize") int pageSize)
    {
        List < Student > student = studentService.queryStudentsByArray(currPage, pageSize);
        return student;
    }
}
