package com.shanzhu.sc.controller.Timetable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.sc.domain.CourseInfo;
import com.shanzhu.sc.domain.WeekCourse;
import com.shanzhu.sc.service.Timetable.TimetableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 课程表控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@RequestMapping("/api/sms/timetable")
public class TimetableController {

    @Resource
    private TimetableService timetableService;

    /**
     * 新增、更新课程表
     *
     * @param WeekCourseList 课程表信息
     */
    @PostMapping
    public void add(@RequestBody JSONArray WeekCourseList) {
        List<WeekCourse> list = JSONObject.parseArray(WeekCourseList.toJSONString(), WeekCourse.class);
        timetableService.add(list);
    }

    @GetMapping("/getTimetable")
    public List<WeekCourse> getStudentList(@RequestParam Map<String, Object> condition) {
        return timetableService.getTimetable(condition);
    }

    @GetMapping("/getTimetableByStudent")
    public List<WeekCourse> getTimetableByStudent(@RequestParam Map<String, Object> condition) {
        return timetableService.getTimetableByStudent(condition);
    }

    @GetMapping("/getTimetableByTeacher")
    public List<WeekCourse> getTimetableByTeacher(@RequestParam Map<String, Object> condition) {
        return timetableService.getTimetableByTeacher(condition);
    }

    @PostMapping("/updateCourseInfo")
    public void updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        timetableService.updateCourseInfo(courseInfo);
    }

}
