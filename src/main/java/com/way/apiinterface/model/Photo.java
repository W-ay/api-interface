package com.way.apiinterface.model;

import lombok.Data;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * @author Way
 */
@Data
public class Photo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private PhotoType photoType;
    private Path path;

}

enum PhotoType {
    //动漫
    ANI,
    //人物
    PEOPLE,
    //其他
    OTHER
}