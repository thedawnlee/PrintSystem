package com.wrq.service;

import com.wrq.commons.ServerResponse;
import com.wrq.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangqian on 2019/4/6.
 */
public interface IFileService {

    ServerResponse<FileVo> upload(MultipartFile file, String path, Integer userId);

    ServerResponse getFileList(Integer userId,Integer pageNum, Integer pageSize);
}
