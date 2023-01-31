package com.szw.commonweal.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.szw.commonweal.entity.ResultInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.IOException;
import java.util.UUID;

public class UploadUtil {
    public static final  String ALI_DOMAIN = "https://shizewenlyz.oss-cn-hongkong.aliyuncs.com/";
    public static String fileNameTemporary=null;
    //地域节点
    private static final  String endpoint = "http://oss-cn-hongkong.aliyuncs.com";
    private static final  String accessKeyId = "LTAI5tS1KcnxDx41HznSD5ok";
    private static final  String accessKeySecret = "vPBssJ8lmrwE0sAQePe75UTJ5YbppE";
    private static final  String bucketName = "shizewenlyz";

    public static ResultInfo<String> uploadImage(MultipartFile file) throws IOException {
        ResultInfo<String> res = new ResultInfo<>();
        //生成文件名称
        String originalFilename = file.getOriginalFilename();//原图片的名字
        String ext = "."+ FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-", "");//uuid+ext组成新的文件名
        String fileName = uuid+ext;
        fileNameTemporary=fileName;
        //oss客户端对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(
          bucketName,
          fileName,
          file.getInputStream()
        );
        ossClient.shutdown();
        res.setData(ALI_DOMAIN+fileName);
        res.setCode(200);
        return  res;
    }


    public static String DeleteImage(String fileName){
        //截取_之后图片名称
        String DeleteImage = fileName.substring(ALI_DOMAIN.length());
        System.out.println("截取_之后:"+DeleteImage);
        String X="";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(bucketName, DeleteImage);
            X="删除照片成功";
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            X="删除照片失败";
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            X="删除照片失败";
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return X;
    }

}
