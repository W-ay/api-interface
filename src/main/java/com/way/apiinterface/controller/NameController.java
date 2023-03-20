package com.way.apiinterface.controller;

import cn.hutool.json.JSONUtil;
import com.way.apiinterface.common.CustomException;
import com.way.apiinterface.model.User;
import com.way.apiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Name Controller
 *
 * @author Way
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping
    private String getNameByGet(String username) {
        return "GET :: " + username;
    }

    @PostMapping
    private String getNameByPost(@RequestParam String username) {
        return "POST PARAM  " + username;
    }

    @PostMapping("/json")
    private String getNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        //todo 从数据库拿数据
        String secretKey = "";
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        //todo 从redis拿随机数
        headerMap.put("nonce", nonce);
        headerMap.put("timestamp", timestamp);

        if (!SignUtils.getSign(headerMap, secretKey, JSONUtil.toJsonStr(user)).equals(sign)) {
            throw new CustomException("错误的API请求");
        }
        return "POST JSON  " + user.getUsername();
    }


}
