package com.way.apiinterface.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.way.apiinterface.common.CustomException;
import com.way.apiinterface.model.User;
import com.way.apiinterface.utils.RedisUtils;
import com.way.apiinterface.utils.SignUtils;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Set;

/**
 * Name Controller
 *
 * @author Way
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @Resource
    private RedisUtils redisUtils;
    private final String TABLE = "name_nonce";

    @GetMapping("/nonce")
    public String getNonce(HttpServletRequest request) {
        //todo 权限校验

        ZSetOperations ops = redisUtils.getStringRedisTemplate().opsForZSet();
        String value;
        //判断是否存在
        while (true) {
            value = RandomUtil.randomNumbers(6);
            Double score = ops.score(TABLE, value);
            if (score == null) {
                break;
            }
        }
        //添加元素
        ops.add(TABLE, value, 0);
        Set range = ops.range(TABLE, 0, -1);
        return value;
    }

    @GetMapping
    public String getNameByGet(String username) {
        return "GET :: " + username;
    }

    @PostMapping
    public String getNameByPost(@RequestBody String username) {
        return "POST PARAM  " + username;
    }

    @PostMapping("/json")
    public String getNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        //todo 从数据库拿secretKey
        String secretKey = "";
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        ZSetOperations<String, String> ops = redisUtils.getStringRedisTemplate().opsForZSet();
        Double score = ops.score(TABLE, nonce);
        //随机数不存在|非0
        if (score == null || score != 0) {
            throw new CustomException("错误的请求");
        }

        headerMap.put("nonce", nonce);
        headerMap.put("timestamp", timestamp);

        if (!SignUtils.getSign(headerMap, secretKey, JSONUtil.toJsonStr(user)).equals(sign)) {
            throw new CustomException("错误的API请求");
        }

        //将随机数score设为时间戳
        ops.add(TABLE, nonce, System.currentTimeMillis());
        return "POST JSON  " + user.getUsername();
    }


}
