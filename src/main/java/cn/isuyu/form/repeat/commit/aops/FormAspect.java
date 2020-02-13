package cn.isuyu.form.repeat.commit.aops;

import cn.isuyu.form.repeat.commit.utils.RedisTools;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class FormAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTools redisTools;

    @Before("@annotation(cn.isuyu.form.repeat.commit.annotations.Form)")
    public void formBefore(){
        String formId = request.getHeader("formId");

        if (null == formId) {
            throw new RuntimeException("请在头信息中携带formId");
        //删除缓存中的formId 这里直接使用delete是避免并发情况  使用seect + delete 存在并发问题
        } else if (!redisTools.delete(formId)){
            throw new RuntimeException("非法formId,请获取后重试");
        }
    }
}
