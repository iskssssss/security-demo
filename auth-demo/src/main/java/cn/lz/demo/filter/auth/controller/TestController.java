package cn.lz.demo.filter.auth.controller;

import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/6/28 14:03
 */
@RestController
@RequestMapping(value = "/api/test", method = {RequestMethod.PUT, RequestMethod.DELETE})
public class TestController {

    @RequestMapping({"/c1", "/c"})
    @PostMapping({"/a1", "/b1"})
    public void test1() {

    }

    @RequestMapping({"/c2", "/c"})
    @PostMapping({"/a2", "/b2"})
    @GetMapping({"/a2", "/b"})
    public void test2() {

    }
}
