package com.way.apiinterface.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 获取随机图片
 *
 * @author Way
 */
@RestController
@RequestMapping("/photo")
@Slf4j
public class PhotosController {
    String dir = new String("F:\\图片\\animation");


    @GetMapping
    public void getPhoto(HttpServletResponse response) throws IOException, InterruptedException {
        ServletOutputStream outputStream = response.getOutputStream();
        //todo add cache
        List<String> list = getAllFile(dir, true);
        String randomFileName = list.get(RandomUtil.randomInt(0, list.size()));
        FileInputStream fis = new FileInputStream(randomFileName);

        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes))>=0) {
            outputStream.write(bytes,0,len);
//            outputStream.write(Base64.getEncoder().encode(bytes),0,len);
        }
        fis.close();
        log.info("文件..."+randomFileName);
    }

    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(),isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }
}
