package com.longlongago.utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//通知图片上传
public class UploadImage {
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //文件的扩展名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        // 新文件名
        String newFileName = "goaway" + res +"."+ extName;
        // upload文件夹位置,webapp下
        String rootPath = request.getSession().getServletContext().getRealPath("goawayUpload/");
        //获取协议名+域名+端口号+项目名 = http+"://"+localhost+":"+8080+/booksystem
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        System.out.println(rootPath);
        try {
            // 创建年月文件夹
            Calendar date = Calendar.getInstance();
            File dateDirs = new File("test"+File.separator+"image" + File.separator + (res));
            // 新文件
            File newFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
            // 判断目标文件所在目录是否存在
            if (!newFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                newFile.getParentFile().mkdirs();
            }
            // 将内存中的数据写入磁盘
            file.transferTo(newFile);
            // 完整的url
            String fileUrl = url +  "/goawayUpload/" + "test"+"/"+"image"+"/" +res+"/"+newFileName;
            System.out.println(fileUrl);
            System.out.println(fileUrl);
            map.put("flag", "上传成功");
            map.put("imageURL",fileUrl);
            map.put("title",newFileName);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", "上传失败");
            return map;
        }
    }
}
