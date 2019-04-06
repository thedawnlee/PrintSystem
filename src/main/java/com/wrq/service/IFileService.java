package com.wrq.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangqian on 2019/4/6.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);

}
