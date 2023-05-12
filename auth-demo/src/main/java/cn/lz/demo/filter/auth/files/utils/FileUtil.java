package cn.lz.demo.filter.auth.files.utils;

import cn.hutool.core.util.NumberUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/7/21 10:32
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    /**
     * 合并文件
     *
     * @param mergeFilePath 待合并文件所在目录
     * @param mergeSavePath 合并后文件存放目录
     * @param fileName      合并后文件名称
     * @return 合并结果
     */
    public static boolean mergeFile(String mergeFilePath, String mergeSavePath, String fileName) {
        try {
            Stream<Path> chunkFileList = Files.list(Paths.get(mergeFilePath));
            Path mergeSaveFile = Paths.get(mergeSavePath + "\\" + fileName);
            if (!Files.exists(mergeSaveFile)) {
                Files.createFile(mergeSaveFile);
            }
            chunkFileList.filter(item -> !Objects.equals(item.getFileName().toString(), fileName))
                    .sorted((o1, o2) -> {
                        String fileName1 = o1.getFileName().toString();
                        String fileName2 = o2.getFileName().toString();
                        int x = NumberUtil.parseInt(fileName1.split("-")[1]);
                        int y = NumberUtil.parseInt(fileName2.split("-")[1]);
                        return Integer.compare(x, y);
                    })
                    .forEach(item -> {
                        try {
                            byte[] bytes = Files.readAllBytes(item);
                            Files.write(mergeSaveFile, bytes, StandardOpenOption.APPEND);
                            Files.delete(item);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
