package com.pactera.esc.common.util;

import com.google.common.collect.Lists;
import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.util.CommonUtil;
import com.pactera.esc.core.util.DateUtil;
import com.pactera.esc.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 陸文
 * @date 2020/4/1010:07
 */
public class FileUploadUtils {

    public static String upload(HttpServletRequest request, HttpServletResponse response,
                                String filePath, String webPath)
            throws BaseException, IOException {
        // 防止乱码
        response.setContentType("application/json;charset=UTF-8");
        if (!(request instanceof MultipartHttpServletRequest)) {
            throw new BaseException(BaseErrorCode.FILE_NOT_EXIST.getCode(),
                BaseErrorCode.FILE_NOT_EXIST.getMessage(), "没有文件");
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        File dir = new File(filePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new BaseException(BaseErrorCode.UPLOAD_ERROR.getCode(),
                    BaseErrorCode.UPLOAD_ERROR.getMessage(), "上传失败");
            }
        }
        String fileName;
        List<String> files = Lists.newArrayList();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 上传文件名 //
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            if (StringUtils.isBlank(fileName)) {
                continue;
            }

            String extension = fileName.substring(fileName.lastIndexOf("."));

            fileName = DateUtil.timestamp() + CommonUtil.getRandomNum(4) + extension;

            FileCopyUtils.copy(mf.getBytes(), new File(filePath.concat(fileName)));

            files.add(webPath.concat("/").concat(fileName));
        }
        return files.stream().reduce((s1, s2) -> s1 + ";" + s2).orElse("");
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
