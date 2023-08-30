package com.way.apiinterface.utils;

import com.way.dubbointerface.model.entity.PhotoInfo;
import com.way.dubbointerface.service.InnerPhotoInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import java.util.List;

public class CommonUtils {
    @DubboReference
    private InnerPhotoInfoService innerPhotoInfoService;
    public static List<PhotoInfo> girlAvatars = null;
    public static List<PhotoInfo> boyAvatars = null;

    public List<PhotoInfo> updateGirlAvatars(){
        girlAvatars = innerPhotoInfoService.getPhotoInfosByPhotoType("girlAvatar");
        return girlAvatars;
    }
    public List<PhotoInfo> updateBoyAvatars(){
        boyAvatars = innerPhotoInfoService.getPhotoInfosByPhotoType("boyAvatar");
        return boyAvatars;
    }
}
