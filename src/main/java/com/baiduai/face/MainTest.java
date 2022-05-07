package com.baiduai.face;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author: 张在森
 * @date: 2022/5/4 21:46
 */
public class MainTest {


        //设置APPID/AK/SK
       public static  final String APP_ID = "24537173";
       public static  final String API_KEY = "8cG5InsAbXa2W27lI8fvAgxe";
       public static  final String SECRET_KEY = "BdxpWZsmcBTPurjq5p4BwvedUXGUsGDw";


    public static void main(String[] args) throws IOException {
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        // String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        // String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
        String image = imgBase64(new File("C:\\Users\\zaise\\Downloads\\103600ieakt7nuho6jebnu.jpg"));
        String imageType = "BASE64";

        // 人脸检测
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses");

        JSONObject res = client.detect(image, imageType, options);
        // System.out.println(res.toString(2));


        String image1 = imgBase64(new File("D:\\Desktop\\22.jpg"));
        String image2 = imgBase64(new File("D:\\Desktop\\33.jpg"));

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);

        JSONObject mms = client.match(requests);
        System.out.println(mms.toString(2));

    }


    public static String imgBase64(File file) throws IOException {

        FileInputStream in = new FileInputStream(file);

        byte[] data = new byte[in.available()];
        in.read(data);

        String s = Base64.getEncoder().encodeToString(data);
        return s;

    }


}
