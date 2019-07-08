package com.cx.edu.file.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Slf4j
@Service
public class FileService {

    @Value("${education.txtFile}")
    private String txtPath;

    public void write(List<String> lines, String fileName) throws IOException {
        if (CollectionUtils.isEmpty(lines)) {
            return;
        }
        Path parent = Paths.get(txtPath);
        Path localFile = parent.resolve(fileName);
        log.info("=================数据写入文件：{}开始===============", fileName);
        Files.write(localFile, lines, StandardOpenOption.APPEND);
        log.info("=================数据写入文件结束==================");
    }

    public Path createFile(String fileName) throws IOException {
        Path localFile = Paths.get(txtPath, fileName);
        try {
            if (Files.exists(localFile)) {
                Files.delete(localFile);
            }
            Files.createFile(localFile);
            log.info("创建文件：{}成功", localFile.toString());
        } catch (IOException e) {
            String format = String.format("创建或删除文件：{%s}失败", localFile.toString());
            log.error(format, e);
            throw e;
        }
        return localFile;
    }
}
