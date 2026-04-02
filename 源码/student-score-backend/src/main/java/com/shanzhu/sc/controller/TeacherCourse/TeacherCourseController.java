package com.shanzhu.sc.controller.TeacherCourse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.sc.domain.TeacherCourse;
import com.shanzhu.sc.dto.Course;
import com.shanzhu.sc.service.TeacherCourse.TeacherCourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 教师课程控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@RequestMapping("/api/sms/teacher/course")
public class TeacherCourseController {

    @Resource
    private TeacherCourseService teacherCourseService;

    @PostMapping
    public void add(@RequestBody JSONArray teacherCourseInfo) {
        List<TeacherCourse> list = JSONObject.parseArray(teacherCourseInfo.toJSONString(), TeacherCourse.class);
        teacherCourseService.add(list);
    }

    @DeleteMapping("/{ids}")
    public void delete(@PathVariable("ids") Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        teacherCourseService.delete(idsList);
    }

    @PutMapping
    public void update(@RequestBody TeacherCourse teacherCourse) {
        teacherCourseService.update(teacherCourse);
    }

    @GetMapping("/getCourseListById/{id}")
    public List<TeacherCourse> getCourseListById(@PathVariable("id") String id) {
        List<TeacherCourse> teacherCourses = teacherCourseService.getCourseListById(id);
        return teacherCourses;
    }

    @GetMapping("/getProfessionInfoByTeacher/{teacherId}")
    public List<Map<String, Object>> getProfessionInfo(@PathVariable("teacherId") String teacherId) {
        return teacherCourseService.getProfessionInfo(teacherId);
    }

    @GetMapping("/getProfessionInfoByAdmin")
    public List<Map<String, Object>> getProfessionInfoByAdmin() {
        return teacherCourseService.getProfessionInfoByAdmin();
    }

    @GetMapping("/getCourseInfo")
    public Course getCourseInfo(@RequestParam Map<String, Object> condition) {
        return teacherCourseService.getCourseInfo(condition);
    }
}
