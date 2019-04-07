package com.wrq.service.impl;

import com.google.common.collect.Lists;
import com.wrq.commons.ServerResponse;
import com.wrq.dao.FileMapper;
import com.wrq.enums.ShareEnum;
import com.wrq.service.IFileService;
import com.wrq.utils.FTPUtil;
import com.wrq.utils.PageCountUtil;
import com.wrq.vo.FileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by wangqian on 2019/4/6.
 */
@Slf4j
@Service(value = "iFileService")
public class FileServiceImpl implements IFileService {


    @Autowired
    private FileMapper fileMapper;

    /**
     * 文件上传
     * @param file
     * @param path
     * @return
     */
    public ServerResponse<FileVo> upload(MultipartFile file, String path, Integer userId){

        String fileName = file.getOriginalFilename();

        int pageCount = 0;

        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;

        log.info("文件开始上传，上传文件的文件名:{},上传的路径为:{},新文件名为:{}",fileName,path,uploadFileName);

        File fileDir = new File(path);

        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);

        try {

            // 文件已经上传
            file.transferTo(targetFile);


            // targetFile上传到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            //  获取文件的页数
            try {
                String uploadPath =   path.substring(path.lastIndexOf("\\") + 1);
                pageCount = getFilePageCount(uploadPath, uploadFileName, fileExtensionName);
                log.info("获取文件的页数为 pageCount = {}", pageCount);
            } catch ( Exception e ) {
                log.error("获取文件的页数错误!");
            }

            // 进行业务逻辑
            com.wrq.pojo.File uploadFile = new com.wrq.pojo.File();
            uploadFile.setUserId(userId);
            uploadFile.setFileName(fileName);
            uploadFile.setNewName(uploadFileName);
            uploadFile.setPageNum(pageCount);
            uploadFile.setShare(ShareEnum.NOT_SHARE.getCode());
            fileMapper.insert(uploadFile);

            // 上传完后，删除upload下面的文件
            targetFile.delete();

        } catch (IOException e) {
            log.error("文件上传异常",e);
            return null;
        }


        FileVo fileVo = new FileVo();

        fileVo.setFileName(targetFile.getName());
        fileVo.setPageNum(pageCount);

        return ServerResponse.createBySuccess(fileVo);
    }

    /**
     * 得到文件页数
     * @param filePath 路径
     * @param fileName 名字
     * @param type doc、ppt、pdf
     * @return 文件页数
     * @throws Exception
     */
    private int getFilePageCount(String filePath, String fileName, String type) throws Exception {

        String fileLocation = "static" + "/" + filePath + "/" + fileName;

        int pageCount = -1;

        log.info("获取文件页数，filePath = {}, fileName = {}, type = {}, fileLocation = {}",filePath, fileName, type, fileLocation);

        switch ( type.toLowerCase() ){
            case "doc":
                return PageCountUtil.getDocCount(fileLocation);
            case "docx":
                return PageCountUtil.getDocxCount(fileLocation);
            case "ppt":
                return PageCountUtil.getPPTCount(fileLocation);
            case "pdf":
                return PageCountUtil.getPdfPage(fileLocation);
            case "xlsx":
                return PageCountUtil.getExcelPageCount(fileLocation);
            case "pptx":
                return PageCountUtil.getPPTCount(fileLocation);
            default:
                return 1;
        }
    }

}
