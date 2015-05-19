package com.enginecore.bigcam.mng.service;

import java.io.InputStream;

/**
 * Created by yyam on 14-11-18.
 */
public interface GridFSService {
    /**
     * 把指定的文件流上传到mongo的bucket集合
     * @param toUpload  要上传的文件流
     * @param filename  在gridFS中的文件名
     * @param bucket    gridFS的集合名称
     */
    void uploadFileToGridFS(InputStream toUpload, String filename, String bucket);

    void removeFile(String filename, String bucket);
}
