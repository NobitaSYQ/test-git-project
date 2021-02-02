package com.enation.app.javashop.framework.dfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * FastDFS客户端封装
 *
 * @author lewis
 * @data 2021/2/1.
 */
@Component
public class FastDFSClient {

    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 文件上传
     *
     * @param file
     * @return fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取源文件名称
        String originalFileName = file.getOriginalFilename();
        //获取文件后缀--.doc
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        //获取文件大小
        long fileSize = file.getSize();

        return uploadFile(bytes, fileSize, extension);
    }

    /**
     * 文件上传
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = storageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        return storePath.getFullPath();
    }

    public void delFile(String filePath) {
        storageClient.deleteFile(filePath);
    }

    /**
     * 下载文件
     * 返回文件字节流大小
     *
     * @param fileUrl 文件URL,如 group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        return download(group, path);
    }

    /**
     * 下载文件
     * @param group 文件组，如 group1
     * @param path 文件
     * @return
     */
    public byte[] download(String group, String path) {
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = storageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }

}
