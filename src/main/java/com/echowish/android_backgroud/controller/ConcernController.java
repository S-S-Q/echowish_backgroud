package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.MyConcern;
import com.echowish.android_backgroud.service.ConcernService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("concern")
public class ConcernController {
    @Autowired
    ConcernService concernService;

    @GetMapping("concernNewFriend")
    @ResponseBody
    public String concernNewFriend(@RequestParam(value = "hostId")Integer hostId,
                                   @RequestParam(value = "friId")Integer friId)
    {
        System.out.println(friId);
        return concernService.concernNewFriend(hostId,friId);
    }

    @GetMapping("cancelConcern")
    @ResponseBody
    public String cancleConcern(@RequestParam(value = "hostId")Integer hostId,
                                @RequestParam(value = "friId")Integer friId)
    {
        return concernService.cancelFriend(hostId,friId);
    }

    @GetMapping("getMyConcern")
    @ResponseBody
    public List<MyConcern> getMyConcern(@RequestParam(value = "hostId")Integer hostId)
    {
        return concernService.getAllMyConcern(hostId);
    }
}
