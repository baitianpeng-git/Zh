package com.zh.btp.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandle implements MetaObjectHandler {

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("进入创建");
        this.setFieldValByName("createTime",String.valueOf(df.format(date)),metaObject);
        this.setFieldValByName("createBy","btp",metaObject);
        this.setFieldValByName("updateTime",df.format(date),metaObject);
        this.setFieldValByName("updateBy","btp",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("进入修改");
        this.setFieldValByName("updateTime",df.format(date),metaObject);
        this.setFieldValByName("updateBy","btp",metaObject);
    }
}


