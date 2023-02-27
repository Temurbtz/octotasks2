package com.tasks.octotasks2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity,String> {

}
