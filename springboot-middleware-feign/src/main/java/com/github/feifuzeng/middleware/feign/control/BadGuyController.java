package com.github.feifuzeng.middleware.feign.control;

import com.github.feifuzeng.middleware.base.result.PlainResult;
import com.github.feifuzeng.middleware.feign.service.BadGuyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author feifz
 * @version 1.0.0
 * @Description 控制器
 * @createTime 2019年10月23日 09:50:00
 */
@RestController
@Log4j2
@RequestMapping("/api/badGuy")
public class BadGuyController {

    @Resource
    private BadGuyService badGuyService;

    @GetMapping({"quotations", "quotations/{count}"})
    public PlainResult<List<String>> getBadGuyQuotations(
            @PathVariable(value = "count", required = false) Integer count
    ) {
        PlainResult<List<String>> result = new PlainResult<>();
        try {
            List<String> resultStrings = badGuyService.getQuotations(count);
            result.setData(resultStrings);
        } catch (Exception e) {
            log.error("Failed to get bad guy quotations.", e);
            result.setErrorMessage("error");
        }
        return result;
    }

}
