package org.example.service;

import org.example.dao.pojo.Resume;

/**
 * @author liuchuang
 * @date 2023/1/18 17:37
 */
public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
