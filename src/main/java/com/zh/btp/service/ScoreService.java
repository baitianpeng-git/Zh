package com.zh.btp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.btp.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.btp.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
public interface ScoreService extends IService<Score> {

    IPage<Score> getScoreOrPr(Page<Score> page,Score score);
    List<Score> listOrTj(Score score);
    int insert(Score score);
    int update(Score score);
    int delete(Integer id);

}
