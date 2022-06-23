package cn.lz.demo.filter.controller;

import cn.lz.demo.filter.model.R;
import cn.lz.security.LzCoreManager;
import cn.lz.security.annotation.DataEncodeSwitch;
import cn.lz.security.annotation.ExcludeInterface;
import cn.lz.security.context.LzContext;
import cn.lz.security.context.model.BaseResponse;
import cn.lz.security.utils.ServletUtil;
import cn.lz.tool.http.enums.MediaType;
import cn.lz.tool.io.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 放行相关接口
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/10/22 16:16
 */
@RestController
@ExcludeInterface
@RequestMapping("/exclude")
@Api(tags = "放行相关接口")
public class ExcludeController {

    @ApiOperation(value = "获取UUID1", notes = "获取UUID1")
    @GetMapping("/uuid1")
    public R<String> uuid1() {
        return R.success(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    @ExcludeInterface
    @ApiOperation(value = "获取UUID2", notes = "获取UUID2")
    @GetMapping("/uuid2")
    public R<String> uuid2() {
        return R.success(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    @ExcludeInterface(open = false)
    @ApiOperation(value = "获取UUID3", notes = "获取UUID3")
    @GetMapping("/uuid3")
    public R<String> uuid3() {
        return R.success(UUID.randomUUID().toString().replaceAll("-", ""));
    }

    @ApiOperation(value = "下载文件", notes = "获取UUID3")
    @GetMapping("/download")
    public void download(@RequestParam("filePath") String filePath) throws UnsupportedEncodingException {
        File file = new File(filePath);
        LzContext<?, ?> lzContext = LzCoreManager.getLzContext();
        BaseResponse<?> response = lzContext.getResponse();
        ServletUtil.download(response, file);
    }
}
