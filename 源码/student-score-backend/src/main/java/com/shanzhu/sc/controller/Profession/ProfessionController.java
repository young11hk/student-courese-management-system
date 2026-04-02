package com.shanzhu.sc.controller.Profession;

import com.shanzhu.sc.domain.Profession;
import com.shanzhu.sc.service.Profession.ProfessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专业控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@RequestMapping("/api/sms/profession")
public class ProfessionController {

    @Resource
    private ProfessionService professionService;

    @GetMapping("/getProfessionList")
    private List<Profession> getProfessionList() {
        return professionService.getProfessionList();
    }
}
