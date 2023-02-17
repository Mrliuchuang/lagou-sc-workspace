package org.example.dao;


import org.example.dao.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuchuang
 * @date 2023/1/18 17:34
 */
public interface ResumeDao extends JpaRepository<Resume, Long> {
}
