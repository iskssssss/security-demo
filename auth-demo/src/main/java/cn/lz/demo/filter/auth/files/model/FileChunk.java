//package cn.lz.demo.filter.auth.files.model;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//
//import java.io.Serializable;
//
///**
// * TODO
// *
// * @author 孔胜
// * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
// * @date 2022/7/12 11:44
// */
//@TableName("file_chunk")
//public class FileChunk implements Serializable {
//    static final long serialVersionUID = 42L;
//
//    /**
//     * 分片id
//     */
//    @TableId(type = IdType.ASSIGN_ID)
//    private String id;
//    /**
//     * 文件md5
//     */
//    private String fileHash;
//    /**
//     * 当前分片序号
//     */
//    private Integer index;
//    /**
//     * 总分片数量
//     */
//    private Integer total;
//    /**
//     * 当前分片大小
//     */
//    private Long currentSize;
//    /**
//     * 分片总大小
//     */
//    private Long size;
//    /**
//     * 分片名称
//     */
//    private String name;
//    /**
//     * 分片存放相对路径
//     */
//    private String path;
//
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
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
//    public Integer getIndex() {
//        return index;
//    }
//
//    public void setIndex(Integer index) {
//        this.index = index;
//    }
//
//    public Integer getTotal() {
//        return total;
//    }
//
//    public void setTotal(Integer total) {
//        this.total = total;
//    }
//
//    public Long getCurrentSize() {
//        return currentSize;
//    }
//
//    public void setCurrentSize(Long currentSize) {
//        this.currentSize = currentSize;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
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
//    @Override
//    public String toString() {
//        return "FileChunk{" +
//                "id='" + id + '\'' +
//                ", fileId='" + fileHash + '\'' +
//                ", index='" + index + '\'' +
//                ", total='" + total + '\'' +
//                ", currentSize='" + currentSize + '\'' +
//                ", size='" + size + '\'' +
//                ", chunkName='" + name + '\'' +
//                ", path='" + path + '\'' +
//                '}';
//    }
//}
