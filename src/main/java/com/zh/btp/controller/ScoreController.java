package com.zh.btp.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.btp.base.Result;
import com.zh.btp.base.StatusCode;
import com.zh.btp.entity.Score;
import com.zh.btp.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
@RestController
@RequestMapping("score")
@Slf4j
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @GetMapping("/list")
    public Result list(){
        List<Score> list = scoreService.list();
        Score byId = scoreService.getById(10);
        log.info("byId",byId);
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    @PostMapping("/getScore")
    public Result getScoreOrPr(@RequestParam("pageNo")Integer pageNo,
                               @RequestParam("pageSize")Integer pageSize,
                               @RequestBody Score score){
        Page<Score> page = new Page<>(pageNo,pageSize);
        IPage<Score> iPage = scoreService.getScoreOrPr(page, score);
        return new Result(true,StatusCode.OK,"查询成功",iPage);
    }

    @PostMapping("/listByTj")
    public Result listByTj(@RequestBody Score score){
        List<Score> iPage = scoreService.listOrTj(score);
        return new Result(true,StatusCode.OK,"查询成功",iPage);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Score score){
        scoreService.insert(score);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Score score){
        scoreService.update(score);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Score  id){
        scoreService.delete(id.getId());
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
