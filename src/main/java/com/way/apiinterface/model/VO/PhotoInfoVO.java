package com.way.apiinterface.model.VO;

import lombok.Data;

import java.io.Serializable;
@Data
public class PhotoInfoVO implements Serializable {
    /**
     * 图片地址
     */
    private String url;
    private static final long serialVersionUID = 1L;

}
