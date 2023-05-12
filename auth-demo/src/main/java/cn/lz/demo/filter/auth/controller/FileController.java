//package cn.lz.demo.filter.auth.controller;
//
//import cn.hutool.core.date.DateUtil;
//import cn.lz.demo.filter.auth.files.utils.FileUtil;
//import cn.lz.demo.filter.auth.files.criteria.ChunkMergeCriteria;
//import cn.lz.demo.filter.auth.files.criteria.ChunkUploadCriteria;
//import cn.lz.demo.filter.auth.files.model.FileChunk;
//import cn.lz.demo.filter.auth.files.model.SysFile;
//import cn.lz.demo.filter.auth.files.service.IFileChunkService;
//import cn.lz.demo.filter.auth.files.service.IFileService;
//import cn.lz.demo.filter.auth.files.vo.FileCheckVO;
//import cn.lz.demo.filter.auth.model.R;
//import cn.lz.security.annotation.ExcludeInterface;
//import cn.lz.tool.reflect.BeanUtil;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * TODO
// *
// * @author 孔胜
// * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
// * @date 2022/7/12 11:40
// */
//@ExcludeInterface
//@RestController
//@RequestMapping("/api/file")
//public class FileController {
//
//    @Value("${lz.file.save-path}")
//    private String fileSavePath;
//
//    @Resource
//    private IFileService iFileService;
//
//    @Resource
//    private IFileChunkService iFileChunkService;
//
//    @ApiOperation(value = "上传分片文件", notes = "上传分片文件")
//    @PostMapping("/upload/chunk")
//    public R<Boolean> uploadChunk(ChunkUploadCriteria criteria) {
//        MultipartFile chunkFile = criteria.getChunkFile();
//        try {
//            String fileHash = criteria.getFileHash();
//            Integer index = criteria.getIndex();
//            Boolean isUpload = iFileChunkService.checkChunk(fileHash, index);
//            if (isUpload) {
//                R.error("当前分片已上传");
//            }
//            String chunkName = fileHash + "-" + index;
//            String chunkPath = DateUtil.format(new Date(), "yyyy\\MM\\dd\\") + fileHash;
//            File file = createFile(fileSavePath + "\\" + chunkPath, chunkName);
//            FileUtil.writeFromStream(chunkFile.getInputStream(), file);
//            FileChunk fileChunk = BeanUtil.toBean(criteria, FileChunk.class);
//            fileChunk.setName(chunkName);
//            fileChunk.setPath(chunkPath);
//            iFileChunkService.save(fileChunk);
//            return R.success(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return R.error(e.getMessage());
//        }
//    }
//
//    @ApiOperation(value = "校验文件", notes = "校验文件")
//    @PostMapping("/check/{md5}")
//    public R<FileCheckVO> check(@PathVariable("md5") String md5) {
//        SysFile sysFile = iFileService.lambdaQuery().eq(SysFile::getFileHash, md5).last("limit 1").one();
//        FileCheckVO result = new FileCheckVO();
//        if (sysFile == null) {
//            List<FileChunk> fileChunkList = iFileChunkService.lambdaQuery()
//                    .eq(FileChunk::getFileHash, md5).orderByAsc(FileChunk::getIndex).list();
//            Set<Integer> completeChunk = fileChunkList.stream().map(FileChunk::getIndex).collect(Collectors.toSet());
//            result.setCompleteChunk(completeChunk);
//            result.setSkipUpload(false);
//            if (fileChunkList.isEmpty()) {
//                result.setNeedMerge(false);
//            } else {
//                FileChunk fileChunk = fileChunkList.get(0);
//                Integer total = fileChunk.getTotal();
//                result.setNeedMerge(total == fileChunkList.size());
//            }
//            return R.success(result);
//        }
//        result.setSkipUpload(true);
//        String path = sysFile.getPath();
//        result.setFilePath(path);
//        return R.success(result);
//    }
//
//    @ApiOperation(value = "合并文件", notes = "合并文件")
//    @PostMapping("/merge")
//    public R<SysFile> merge(@RequestBody ChunkMergeCriteria criteria) {
//        String fileHash = criteria.getFileHash();
//        String fileName = criteria.getFileName();
//        String chunkPath = DateUtil.format(new Date(), "yyyy\\MM\\dd\\") + fileHash;
//        String mergeFilePath = fileSavePath + "\\" + chunkPath;
//        boolean mergeResult = FileUtil.mergeFile(mergeFilePath, mergeFilePath, fileName);
//        if (!mergeResult) {
//            return R.error("分片合并失败！");
//        }
//        SysFile result = new SysFile(fileHash, chunkPath, fileName);
//        File file = FileUtil.file(mergeFilePath + "\\" + fileName);
//        result.setSize(file.length());
//        result.setStatus(4);
//        iFileService.save(result);
//        return R.success(result);
//    }
//
//    public File createFile(String path, String fileName) {
//        Path fileSavePath = Paths.get(path);
//        try {
//            if (!Files.isExecutable(fileSavePath)) {
//                Files.createDirectories(fileSavePath);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new File(path + "\\" + fileName);
//    }
//
//}
