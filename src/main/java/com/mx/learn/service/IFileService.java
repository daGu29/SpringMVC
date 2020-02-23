package com.mx.learn.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * IFileService
 *
 * @author dagu29
 * @date 2020/2/20 0020
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
