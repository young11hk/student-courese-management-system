package com.shanzhu.sc.service.Upload;

import com.shanzhu.sc.domain.Upload;

import java.util.Map;

/**
 * 上传文件 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface UploadService {

    /**
     * 上传头像
     *
     * @param upload 上传文件信息
     */
    void upload(Upload upload);

    /**
     * 获取头像
     *
     * @param condition 查询条件
     * @return 头像
     */
    String getHeader(Map<String, Object> condition);
}
