package com.zh.btp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.btp.entity.Score;
import com.zh.btp.mapper.ScoreMapper;
import com.zh.btp.service.ScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Override
    public IPage getScoreOrPr(Page<Score> page, Score score) {

        QueryWrapper<Score> QueryWrapper = new QueryWrapper<>();
//        判断要条件查询的数据是否为空
        if (!StringUtils.isEmpty(score.getSubject())) {
            QueryWrapper.like("subject", score.getSubject());
        }
        if (!StringUtils.isEmpty(score.getScore())) {
            QueryWrapper.like("score", score.getScore().toString());
        }

        QueryWrapper.orderByAsc("id");
        Page<Score> scorePage = baseMapper.selectPage(page,QueryWrapper);
        return scorePage;
    }

    @Override
    public List<Score> listOrTj(Score score) {
        QueryWrapper<Score> wrapper = new QueryWrapper<>();
        wrapper.eq("score",score.getScore());
        wrapper.eq("subject",score.getSubject());
        List<Score> scores = baseMapper.selectList(wrapper);
        System.out.println(scores);
        return scores;
    }

    @Override
    public int insert(Score score) {
        return baseMapper.insert(score);
    }

    @Override
    public int update(Score score) {
        return baseMapper.updateById(score);
    }

    @Override
    public int delete(Integer id) {
        return baseMapper.deleteById(id);
    }
}
