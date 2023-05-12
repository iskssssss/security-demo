package cn.lz.demo.filter.auth.files.criteria;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/7/12 14:13
 */
public class ChunkUploadCriteria implements Serializable {
    static final long serialVersionUID = 42L;

    /**
     * md5
     */
    @ApiModelProperty("文件MD5")
    private String fileHash;

    /**
     * 当前分片序号
     */
    @ApiModelProperty("当前分片序号")
    private Integer index;

    /**
     * 总分片数量
     */
    @ApiModelProperty("总分片数量")
    private Integer total;

    /**
     * 当前分片大小
     */
    @ApiModelProperty("当前分片大小")
    private Long currentSize;

    /**
     * 文件总大小
     */
    @ApiModelProperty("文件总大小")
    private Long size;

    /**
     * 文件
     */
    @ApiModelProperty("文件")
    private MultipartFile chunkFile;

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(Long currentSize) {
        this.currentSize = currentSize;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public MultipartFile getChunkFile() {
        return chunkFile;
    }

    public void setChunkFile(MultipartFile chunkFile) {
        this.chunkFile = chunkFile;
    }
}
