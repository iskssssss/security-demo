//package cn.lz.demo.filter.auth.files.model;
//
//import cn.hutool.core.io.FileUtil;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import io.swagger.annotations.ApiModelProperty;
//
//import java.io.Serializable;
//
///**
// * TODO
// *
// * @author 孔胜
// * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
// * @date 2022/7/12 11:42
// */
//@TableName("files")
//public class SysFile implements Serializable {
//    static final long serialVersionUID = 42L;
//
//    public SysFile() {
//    }
//
//    public SysFile(String fileHash, String mergeSavePath, String fileName) {
//        this.setFileHash(fileHash);
//        this.setName(fileName);
//        this.setPath(mergeSavePath + fileName);
//        String suffix = FileUtil.getSuffix(fileName);
//        this.setSuffix(suffix);
//    }
//
//    /**
//     * 文件id
//     */
//    @TableId(type = IdType.ASSIGN_UUID)
//    @ApiModelProperty("文件id")
//    private String id;
//    /**
//     * 文件名称
//     */
//    @ApiModelProperty("文件名称")
//    private String name;
//    /**
//     * 文件MD5
//     */
//    @ApiModelProperty("文件MD5")
//    private String fileHash;
//    /**
//     * 文件路径
//     */
//    @ApiModelProperty("文件路径")
//    private String path;
//    /**
//     * 文件状态(1.上传中 2.待合并 3.上传取消 4.正常)
//     */
//    @ApiModelProperty("文件状态(1.上传中 2.待合并 3.上传取消 4.正常)")
//    private Integer status;
//    /**
//     * 文件大小
//     */
//    @ApiModelProperty("文件大小")
//    private Long size;
//    /**
//     * 文件类型
//     */
//    @ApiModelProperty("文件类型")
//    private Integer type;
//    /**
//     * 文件后缀
//     */
//    @ApiModelProperty("文件后缀")
//    private String suffix;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getFileHash() {
//        return fileHash;
//    }
//
//    public void setFileHash(String fileHash) {
//        this.fileHash = fileHash;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public Long getSize() {
//        return size;
//    }
//
//    public void setSize(Long size) {
//        this.size = size;
//    }
//
//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
//
//    public String getSuffix() {
//        return suffix;
//    }
//
//    public void setSuffix(String suffix) {
//        this.suffix = suffix;
//    }
//
//    @Override
//    public String toString() {
//        return "File{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", md5='" + fileHash + '\'' +
//                ", path='" + path + '\'' +
//                ", status='" + status + '\'' +
//                ", size='" + size + '\'' +
//                ", type='" + type + '\'' +
//                '}';
//    }
//}
