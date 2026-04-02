package com.shanzhu.sc.controller.User;

import com.shanzhu.sc.dto.User;
import com.shanzhu.sc.service.User.AdminService;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 管理员账户控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@RequestMapping("/api/sms/user/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping
    public void addAdmin(@RequestBody User user) {
        adminService.add(user);
    }

    @DeleteMapping("/{ids}")
    public void delete(@PathVariable("ids") Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        adminService.delete(idsList);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        adminService.update(user);
    }

    @GetMapping("/getAdminList")
    public PagingResult<User> getAdminList(@RequestParam Map<String, Object> condition,
                                           @RequestParam(required = false, name = "$limit", defaultValue = "10") Integer limit,
                                           @RequestParam(required = false, name = "$offset", defaultValue = "0") Integer offset) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return adminService.getAdminList(rowBounds, condition);
    }

}
