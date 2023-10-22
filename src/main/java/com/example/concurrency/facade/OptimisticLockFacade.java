package com.example.concurrency.facade;

import com.example.concurrency.service.OptimisticLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class OptimisticLockFacade {
    //private static final Logger logger = LoggerFactory.getLogger(ThisClass.class);

    private final Logger logger = LoggerFactory.getLogger(getClass());

    LocalDateTime dateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    private OptimisticLockService optimisticLockService;


    public OptimisticLockFacade(OptimisticLockService optimisticLockService) {
        this.optimisticLockService = optimisticLockService;
    }

    public Map<String, Object> updateArticle(Long articleId, String title, String content) throws InterruptedException {
        while (true) {

            try {
                optimisticLockService.updateArticle(articleId,title,content);

            } catch (Exception e) { //락걸려서 50초 잠들고 재시도
                Map<String, Object> errMap = new HashMap<String,Object>();
                errMap.put("ERROR","다른 에디터가 수정중입니다.");
                errMap.put("락 발생시간",dateTime.format(formatter));

                Thread.sleep(50);

                return errMap;
            }
        }

    }
}
