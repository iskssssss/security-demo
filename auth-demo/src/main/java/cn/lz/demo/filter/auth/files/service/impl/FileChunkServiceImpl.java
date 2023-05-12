//package cn.lz.demo.filter.auth.files.service.impl;
//
//import cn.lz.demo.filter.auth.files.mapper.IFileChunkMapper;
//import cn.lz.demo.filter.auth.files.model.FileChunk;
//import cn.lz.demo.filter.auth.files.service.IFileChunkService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//
///**
// * TODO
// *
// * @author 孔胜
// * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
// * @date 2022/7/12 11:56
// */
//@Service
//public class FileChunkServiceImpl
//        extends ServiceImpl<IFileChunkMapper, FileChunk>
//        implements IFileChunkService {
//
//    @Override
//    public Boolean checkChunk(String fileHash, Integer index) {
//        Long count = this.lambdaQuery()
//                .eq(FileChunk::getFileHash, fileHash)
//                .eq(FileChunk::getIndex, index)
//                .count();
//        return count > 0;
//    }
//}
