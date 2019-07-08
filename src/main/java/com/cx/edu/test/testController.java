package com.cx.edu.test;

import com.cx.edu.exception.ResponseData;
import com.cx.edu.file.service.FileService;
import com.cx.edu.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/test")
@Slf4j
public class testController {


    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FileService fileService;

    @GetMapping()
    public void set() {
        redisService.setValue(String.valueOf(94L), "1234567534");
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.opsForValue().set("10", "124");
    }

    @GetMapping("get")
    public String get() {
        return (String) redisTemplate.opsForValue().get("3");

    }

    //Runnable 启动多线程
    @GetMapping("runnable")
    public void runnable() {
        Runnable runnable = dealMsg("线程一");
        Runnable runnable1 = dealMsg("线程二");
        new Thread(runnable).start();
        new Thread(runnable1).start();
    }

    private Runnable dealMsg(String name) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.info("线程：{}启动",name);
            }
        };
        return runnable;
    }

    //多线程处理数据**
    @GetMapping("executorService")
    public ResponseData executorService() throws Exception{
        List<Integer> data = getData();
        int num = 1000;
        int threadCount = data.size()/num;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<List<Integer>>> futures = new ArrayList<>(threadCount);
        for (int i =0; i <= threadCount; i++) {
            int from = i * num;
            int to = num + i * num;
            if (i == threadCount) {
                to = data.size();
            }
            log.info("开始：{}， 结束：{}", from, to);
            List<Integer> subList = data.subList(from, to);
            Future<List<Integer>> future = executorService.submit(() -> {
                List<Integer> resultSubList = subList.stream().filter(a -> {
                    if (a%2 == 1) {
                        return false;
                    }
                    return true;
                }).collect(Collectors.toList());
                return resultSubList;
            });
            futures.add(future);
        }
        List<Integer> result = new ArrayList<>();
        for (Future<List<Integer>> future : futures) {
            while (!future.isDone()) {

            }
            List<Integer> subResult = future.get();
            result.addAll(subResult);
        }
        ResponseData responseData = new ResponseData();
        responseData.setData(result);
        return responseData;
    }

    private List<Integer> getData() {
        int i = 0;
        List<Integer> data = new LinkedList<>();
        while (i < 4999) {
            data.add(i);
            i++;
        }
        return data;
    }

    @GetMapping("txtFile")
    public ResponseData txtFile() throws IOException {
        String fileName = "20190618.txt";
        List<String> data = new ArrayList<>();
        data.add("第一行");
        data.add("第二行");
        Path path = fileService.createFile(fileName);
        fileService.write(data, fileName);
        return new ResponseData();
    }


}
