package cn.lz.demo.filter.auth.files.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/7/12 15:26
 */
public class FileCheckVO implements Serializable {
    static final long serialVersionUID = 42L;

    @ApiModelProperty("已上传分片")
    private Set<Integer> completeChunk;

    @ApiModelProperty("是否是秒传文件")
    private Boolean skipUpload = false;

    @ApiModelProperty("是否需要合并")
    private Boolean needMerge = false;

    @ApiModelProperty("文件路径")
    private String filePath;

    public Set<Integer> getCompleteChunk() {
        if (completeChunk == null) {
            this.completeChunk = Collections.emptySet();
        }
        return completeChunk;
    }

    public void setCompleteChunk(Set<Integer> completeChunk) {
        this.completeChunk = completeChunk;
    }

    public Boolean getSkipUpload() {
        return skipUpload;
    }

    public void setSkipUpload(Boolean skipUpload) {
        this.skipUpload = skipUpload;
    }

    public Boolean getNeedMerge() {
        return needMerge;
    }

    public void setNeedMerge(Boolean needMerge) {
        this.needMerge = needMerge;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
