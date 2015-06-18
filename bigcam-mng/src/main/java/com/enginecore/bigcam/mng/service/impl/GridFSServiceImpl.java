package com.enginecore.bigcam.mng.service.impl;

import com.enginecore.bigcam.mng.service.GridFSService;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yyam on 14-11-18.
 */
//@Service
public class GridFSServiceImpl implements GridFSService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void uploadFileToGridFS(InputStream toUpload, String filename, String bucket) {
        if (StringUtils.isBlank(filename)) {
            throw new IllegalArgumentException("上传文件到mongo中，但没有指定文件在mongo中的文件名");
        }
        try {
            GridFS gridFS = getGridFS(bucket);
            //保证同一个集合中不存在同名文件
            gridFS.remove(filename);
            GridFSInputFile inputFile = gridFS.createFile(toUpload);
            inputFile.setFilename(filename);
            inputFile.save();
        } finally {
            if (toUpload != null) {
                try {
                    toUpload.close();
                } catch (IOException e) {
                    logger.warn("上传文件到mongo后关闭文件流出现异常", e);
                }
            }
        }
    }

    private GridFS getGridFS(String bucket) {
        GridFS gridFS = null;
        if (StringUtils.isNotBlank(bucket)) {
            gridFS = new GridFS(mongoTemplate.getDb(), bucket);
        } else {
            gridFS = new GridFS(mongoTemplate.getDb());
        }
        return gridFS;
    }

    @Override
    public void removeFile(String filename, String bucket) {
        if (StringUtils.isBlank(filename)) {
            throw new IllegalArgumentException("删除mongo中的文件，但没有指定文件的名字");
        }
        GridFS gridFS = getGridFS(bucket);
        gridFS.remove(filename);
    }
}
