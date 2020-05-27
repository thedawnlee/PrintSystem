package com.dawn.vo;

import lombok.Data;

/**
 * 个人中心 我的文件
 *
 */
@Data
public class FileListVo {

    private Integer fileId;

    private String fileName;

    private String createTime;

    private Integer share;

    private Integer integral;

    private String fileTypeImg;

    private String fileNewName; /* 用来下载 */

}
