package cn.itcast.demo;

import org.csource.fastdfs.*;

/**
 * Test
 * hasee
 * 2018/11/22
 * 17:16
 *
 * @Version 1.0
 **/

public class Test {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        ClientGlobal.init("D:\\ideaproject\\fastDFSdemo\\src\\main\\resources\\fdfs_client.conf");
        //构建一个管理者客户端
        TrackerClient client=new TrackerClient();
        //连接管理者服务端
        TrackerServer trackerServer=client.getConnection();
        //声明储存服务端
        StorageServer storageServer=null;
        //获取储存服务器的客户端对象
        StorageClient storageClient=new StorageClient(trackerServer, storageServer);
        //上传文件
        String[] strings=storageClient.upload_file("C:\\Users\\hasee\\Documents\\壁纸图片\\5695ad624f6ad.jpg","jpg",null);
        //显示上传结果file_id
        for (String str : strings) {
            System.out.println(str);
        }

    }


}
