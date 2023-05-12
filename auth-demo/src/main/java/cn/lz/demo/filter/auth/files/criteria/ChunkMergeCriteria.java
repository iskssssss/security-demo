package cn.lz.demo.filter.auth.files.criteria;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/7/12 14:23
 */
public class ChunkMergeCriteria implements Serializable {
    static final long serialVersionUID = 42L;

    @ApiModelProperty("文件MD5")
    private String fileHash;

    @ApiModelProperty("文件名称")
    private String fileName;

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
