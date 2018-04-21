package com.shape.controller;

import com.alibaba.fastjson.JSON;
import com.shape.domain.JsonResult;
import com.shape.domain.ScoreVo;
import com.shp.model.Score;
import com.shp.model.User;
import com.shp.query.ScoreQuery;
import com.shp.query.UserQuery;
import com.shp.result.BaseResult;
import com.shp.result.PageResult;
import com.shp.service.ScoreService;
import com.shp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by HXC on 2018/3/26.
 */
@RestController
@RequestMapping("/data")
public class DataController {

    private static Logger log = LoggerFactory.getLogger(DataController.class);

    @Resource
    private UserService userService;

    @Resource
    private ScoreService scoreService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody UserQuery user) {
        JsonResult result = JsonResult.falseRuesult();
        try {
            User userBaseResult = userService.getUserByQuery(user);
            if (userBaseResult == null) {
                result.setMessage("用户名或密码错误");
            }else {
                result.setData(userBaseResult);
                result.setSuccess(true);
                result.setMessage(null);
            }
        }catch (Exception e) {
            log.error("DataController.login error 参数：{}", JSON.toJSONString(user), e);
        }
        return result;
    }

    @RequestMapping(value = "/registe", method = RequestMethod.POST)
    public JsonResult registe(@RequestBody User user) {
        JsonResult jsonResult = JsonResult.falseRuesult();
        try {
            int result = userService.insertUser(user);
            if (result > 0) {
                jsonResult.setSuccess(true);
                jsonResult.setMessage(null);
            }
        }catch (Exception e) {
            log.error("DataController.registe error 参数：{}",JSON.toJSONString(user), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "queryScore", method = RequestMethod.POST)
    public JsonResult<PageResult<ScoreVo>> queryScore(@RequestBody ScoreQuery query) {
        JsonResult<PageResult<ScoreVo>> jsonResult = JsonResult.falseRuesult();
        PageResult<ScoreVo> scoreVoPageResult = new PageResult<ScoreVo>();
        try{
            PageResult<Score> scoreList = scoreService.queryScoreByPage(query);
            scoreVoPageResult.setPageIndex(scoreList.getPageIndex());
            scoreVoPageResult.setPageSize(scoreList.getPageSize());
            scoreVoPageResult.setTotal(scoreList.getTotal());
            List<ScoreVo> scoreVoList = scoreList.getContent().stream()
                    .map(item -> {
                        ScoreVo vo = new ScoreVo();
                        BeanUtils.copyProperties(item,vo);
                        User user = userService.queryUserById(item.getUserId());
                        vo.setName(user.getUserName());
                        return vo;
                    }).collect(Collectors.toList());
            scoreVoPageResult.setContent(scoreVoList);
            jsonResult.setData(scoreVoPageResult);
            jsonResult.setSuccess(true);
            jsonResult.setMessage(null);
        }catch (Exception e) {
            log.error("DataController.queryScore error 参数:{}", JSON.toJSONString(query), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "insertScore",method = RequestMethod.POST)
    public JsonResult insertScore(@RequestBody Score score) {
        JsonResult jsonResult = JsonResult.falseRuesult();
        try {
            int result = scoreService.insertScore(score);
            jsonResult.setSuccess(result > 0);
            jsonResult.setMessage(null);
        }catch (Exception e) {
            log.error("DataController.insertScore error 参数：{}", JSON.toJSONString(score), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "queryUser", method = RequestMethod.POST)
    public JsonResult<PageResult<User>> queryUser(@RequestBody UserQuery query) {
        JsonResult jsonResult = JsonResult.falseRuesult();
        try{
            PageResult<User> user = userService.queryUserByPage(query);
            jsonResult.setSuccess(true);
            jsonResult.setData(user);
            jsonResult.setMessage(null);
        }catch (Exception e) {
            log.error("DataController.queryUser error 参数：{}", JSON.toJSONString(query), e);
        }
        return jsonResult;
    }
}
