package app.sys.uplaodPic.action;

import app.common.uploadFile.GenericFileUploadAction;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CesiumAction extends GenericFileUploadAction {
    private Integer objId;

    @Autowired
    app.sys.uplaodPic.service.userService userService;


    public void getObjectMessage() {
        List objectMessage=userService.getCesiumObjectMessage();
        List message=new ArrayList();

        Integer currentSize=0;
        if(objectMessage!=null) {
            for (Object obj : objectMessage) {//需要for循环遍历取数据
                /*存储父类坐标*/
                Map<String,Object> map=new LinkedHashMap<String, Object>();//json传输载体
                Object[] objArray = (Object[]) obj;
                String name= (String) objArray[0];
                String address= (String) objArray[1];
                Timestamp creatTime= (Timestamp) objArray[2];
                String point= (String) objArray[3];
                String point1= String.valueOf(point);
                String x=point1.substring(point1.indexOf("(")+1, point1.indexOf(" "));
                String y=point1.substring(point1.indexOf(" ")+1,point1.indexOf(")"));
                Integer objId= (Integer) objArray[4];
                String url= (String) objArray[5];
                map.put("name",name);
                map.put("address",address);
                map.put("createTime",creatTime);
                map.put("x",x);
                map.put("y",y);
                map.put("url",url);
                map.put("objId",objId);
                message.add(map);
                currentSize+=1;
            }
            JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect);
            super.writeJson(message);
        }

    }

    public void getAttributeMessage() {
        List attributeMessage=userService.getCesiumAttributeMessage(objId);
        List message=new ArrayList();
        if(attributeMessage==null){
            super.writeJson(attributeMessage);
        }else{
            Integer currentSize=0;
            if(attributeMessage!=null) {
                for (Object obj : attributeMessage) {//需要for循环遍历取数据
                    //存储父类坐标
                    Map<String,Object> map=new LinkedHashMap<String, Object>();//json传输载体
                    Object[] objArray = (Object[]) obj;
                    String name= (String) objArray[0];
                    String address= (String) objArray[1];
                    Timestamp creatTime= (Timestamp) objArray[2];
                    String point= (String) objArray[3];
                    String point1= String.valueOf(point);
                    String x=point1.substring(point1.indexOf("(")+1, point1.indexOf(" "));
                    String y=point1.substring(point1.indexOf(" ")+1,point1.indexOf(")"));

                    //获取父类附件
                    Integer oaId= (Integer) objArray[4];
                    String url= (String) objArray[5];
                    map.put("name",name);
                    map.put("address",address);
                    map.put("createTime",creatTime);
                    map.put("x",x);
                    map.put("y",y);
                    map.put("url",url);
                    message.add(map);
                    currentSize+=1;
                }
                JSON.toJSONString(message, SerializerFeature.DisableCircularReferenceDetect);
                super.writeJson(message);
            }
        }

    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }
}
