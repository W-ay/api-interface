package com.way.apiinterface.controller;

import com.way.apiinterface.model.VO.PhotoInfoVO;
import com.way.apiinterface.utils.CommonUtils;
import com.way.dubbointerface.common.BaseResponse;
import com.way.dubbointerface.common.ResultUtils;
import com.way.dubbointerface.model.entity.PhotoInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    /**
     * 获取随机头像
     * @return 头像链接
     */
    @GetMapping("/girl")
    public BaseResponse getRandomGirlAvatar(){
        List<PhotoInfo> girlAvatars = CommonUtils.girlAvatars;
        PhotoInfo photoInfo = girlAvatars.get((int) (Math.random() * girlAvatars.size()));
        PhotoInfoVO photoInfoVO = new PhotoInfoVO();
        BeanUtils.copyProperties(photoInfo,photoInfoVO);
        return ResultUtils.success(photoInfoVO);
    }
    /**
     * 获取随机头像
     * @return 头像链接
     */
    @GetMapping("/boy")
    public BaseResponse getRandomBoyAvatar(){
        List<PhotoInfo> boyAvatars = CommonUtils.boyAvatars;
        PhotoInfo photoInfo = boyAvatars.get((int) (Math.random() * boyAvatars.size()));
        PhotoInfoVO photoInfoVO = new PhotoInfoVO();
        BeanUtils.copyProperties(photoInfo,photoInfoVO);
        return ResultUtils.success(photoInfoVO);
    }
}
