package com.echowish.android_backgroud.controller;

import com.echowish.android_backgroud.pojo.preconcern.MyPreConcernRequest;
import com.echowish.android_backgroud.service.PreConcernService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("preconcern")
public class PreConcernController {
    @Autowired
    PreConcernService preConcernService;

    @GetMapping("sendConcernRequest")
    @ResponseBody
    String sendConcernRequest(@RequestParam(value = "hostId")Integer hostId,
                              @RequestParam(value = "friId")Integer friId)
    {
        return preConcernService.sendConcernRequest(hostId,friId);
    }

    @GetMapping("acceptConcernRequest")
    @ResponseBody
    String acceptConcernRequest(@RequestParam(value = "hostId")Integer hostId,
                                @RequestParam(value = "friId")Integer friId)
    {
        return preConcernService.acceptConcernRequest(hostId,friId);
    }

    @GetMapping("refuseConcernRequest")
    @ResponseBody
    String refuseConcernRequest(@RequestParam(value = "hostId")Integer hostId,
                                @RequestParam(value = "friId")Integer friId)
    {
        return preConcernService.refuseConcernRequest(hostId,friId);
    }

    @GetMapping("getMyPreConcernRequests")
    @ResponseBody
    List<MyPreConcernRequest> getMyPreConcernRequests(@RequestParam(value = "hostId")Integer hostId)
    {
        return preConcernService.queryAllMyPreConcern(hostId);
    }
}
