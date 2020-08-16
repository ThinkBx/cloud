package com.cloud.db.bean;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体父类
 * @author fjj
 * @date 2020/5/26 23:34
 */
@Data
public class BaseBean<T extends Model> extends Model {
    private Long id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
