package com.shanzhu.sc.controller.Score;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shanzhu.sc.dto.Course;
import com.shanzhu.sc.dto.Score;
import com.shanzhu.sc.service.Score.ScoreService;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 成绩 控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@RequestMapping("/api/sms/score")
public class ScoreController {

    @Resource
    private ScoreService scoreService;

    @GetMapping("/getCourseList")
    public PagingResult<Course> getCourseList(@RequestParam Map<String, Object> condition, @RequestParam(required = false, name = "$limit", defaultValue = "10") Integer limit, @RequestParam(required = false, name = "$offset", defaultValue = "0") Integer offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return scoreService.getCourseList(rowBounds, condition);
    }

    @PostMapping
    private void addEntry(@RequestBody JSONArray UserScore) {
        List<Score> list = JSONObject.parseArray(UserScore.toJSONString(), Score.class);
        scoreService.addEntry(list);
    }

    @GetMapping("/export")
    public List<Course> getExportList(@RequestParam Map<String, Object> condition) {
        return scoreService.getExportList(condition);
    }

    @GetMapping("/getUserNum")
    public List<Map<String, Object>> getUserNum(@RequestParam Map<String, Object> condition) {
        return scoreService.getUserNum(condition);
    }

    @GetMapping("/getUserTotal")
    public Map<String, Object> getUserTotal(@RequestParam Map<String, Object> condition) {
        return scoreService.getUserTotal(condition);
    }
}
