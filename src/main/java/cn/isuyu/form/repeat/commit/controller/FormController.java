package cn.isuyu.form.repeat.commit.controller;

import cn.isuyu.form.repeat.commit.annotations.Form;
import cn.isuyu.form.repeat.commit.utils.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FormController {

    @Autowired
    private RedisTools redisTools;

    @RequestMapping(value = "getFormId")
    public String getKey() {

        String key = UUID.randomUUID().toString();
        //将key存储到缓存中，有效期10分钟
        redisTools.save(key,key,10);
        return key;
    }

    /**
     * 表单重复提交
     * @return
     */
    @RequestMapping(value = "commit")
    @Form
    public String commit() {
        return "success";
    }
}
