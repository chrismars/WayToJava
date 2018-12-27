package app.sys.uplaodPic.action;

import app.common.uploadFile.GenericFileUploadAction;
import app.sys.uplaodPic.action.bean.*;
import app.sys.uplaodPic.action.mobileEntity.AllMessage;
import app.sys.uplaodPic.action.mobileEntity.AttachmentAndLocation;
import app.sys.uplaodPic.action.mobileEntity.ObjDetailEntity;
import app.sys.uplaodPic.action.mobileEntity.ObjectAttributeEntity;
import app.sys.uplaodPic.service.userService;
import app.sys.uplaodPic.vo.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

public class userAction extends GenericFileUploadAction {
    private int id;
    private String userName;
    private String admuId;
    private String picUrl;
    private String objId;
    private String jsonStr;
    private String attImageFolderPath= File.separator+"upload"+File.separator;//图片保存的目录的相对路
    private Map<String,Object> map=new LinkedHashMap<String, Object>();//json传输载体

    @Autowired
    userService userService;
    public void addMessage() throws Exception{
        String fileUploadResult=super.execute();
        if (fileUploadResult==SUCCESS){
//            jsonStr = JSON.toJSONString(jsonStr, SerializerFeature.DisableCircularReferenceDetect);
            JSONObject object = JSONObject.fromObject(jsonStr);

            /*首先获得aq_object将其转为json*/
           // JSONObject object = (JSONObject) jodata.get("aq_object");

            /*保存父类设施基本信息*/
            AqObjectEntity aqObjectEntity=new AqObjectEntity();
            if(object.size()>0){
                Date date=new Date();
                Timestamp time = new Timestamp(date.getTime());
                aqObjectEntity.setAddress((String) object.get("address"));
                aqObjectEntity.setAdmuId((Integer) object.get("admuID"));
                aqObjectEntity.setComId((Integer) object.get("comID"));
                aqObjectEntity.setMemo((String) object.get("memo"));
                aqObjectEntity.setName((String) object.get("name"));
                aqObjectEntity.setObjNum((Integer) object.get("objNum"));
                aqObjectEntity.setStdId((Integer) object.get("stdID"));
                aqObjectEntity.setTypId((Integer) object.get("typID"));
                aqObjectEntity.setGeojType((short) 1);
                aqObjectEntity.setIsPass((Integer)object.get("isPass"));
                aqObjectEntity.setServiceId((Integer) object.get("serviceID"));
                aqObjectEntity.setIfLock((short) 0);
                aqObjectEntity.setCreateTime(time);
                aqObjectEntity.setDepartId((Integer) object.get("depart_id"));
                aqObjectEntity.setCheckId((Integer) object.get("checkid"));
                userService.saveAqObject(aqObjectEntity);
            }
            addPublicMessage(object,aqObjectEntity);

            /*调用沙老师接口添加停车位*/
            addToMobileCar(object,aqObjectEntity);
            map.put("objId",aqObjectEntity.getObjId());
        }else if (fileUploadResult==ERROR){
            map.put("msg2","uploadFile failed");
        }

        super.writeJsonToMobile(map);
    }

    public void  addToMobileCar(JSONObject object,AqObjectEntity aqObjectEntity){
        /*判断采集的数据中是否有停车位*/
        JSONArray objectattributes = (JSONArray)object.get("aq_objectattributes");
        int a=0;
        for(int i=0;i<objectattributes.size();i++){
            JSONObject objectattribute= (JSONObject) objectattributes.get(i);
            Integer typeId= (Integer) objectattribute.get("typeId");
            //String typeName=new String("无障碍停车位".getBytes("GBK"),"UTF-8");
            if(objectattribute.get("typeId").equals(13)){
                a=1;
            }
        }
        if(a>0){
            /*解析封装数据*/
            JSONObject fparam= null;
            try {
                fparam = JSONObject.fromObject(makePosEntityFormat(object));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            /*调用接口*/
            String mobileObjId=interfaceUtil("http://47.93.237.6:8080/wheelchair/getData.jsp",fparam);
            if(mobileObjId!="failed"){
                WebObjIdMobileIdEntity webObjIdMobileIdEntity=new WebObjIdMobileIdEntity();
                webObjIdMobileIdEntity.setMobileObjId(mobileObjId);
                webObjIdMobileIdEntity.setWebObjId(aqObjectEntity.getObjId());
                userService.addWebAndMoblieObjId(webObjIdMobileIdEntity);
            }

        }
    }


    public Aq_ALL makePosEntityFormat(JSONObject object) throws UnsupportedEncodingException {
        int currentSize=0;
        Aq_ALL mAq_All=new Aq_ALL();
        if(object.size()>0){
            /*对象本身*/
            List<posAq_attachmentEntity> aq_attachments=new ArrayList<posAq_attachmentEntity>();
            List<posAq_objectAttributeEntity> aq_objectAttribute=new ArrayList<posAq_objectAttributeEntity>();
            posAq_objectAttributeEntity posAq_objectAttributeEntity=new posAq_objectAttributeEntity();
            posAq_objectAttributeEntity posAq_objectAttributeEntity1=new posAq_objectAttributeEntity();
            posAq_objectAttributeEntity posAq_objectAttributeEntity5=new posAq_objectAttributeEntity();
            posAq_objDetailEntity posAq_objDetailEntity=new posAq_objDetailEntity();
            mAq_All.setComID((Integer) object.get("comID"));
            mAq_All.setStdID((Integer) object.get("stdID"));
            mAq_All.setTypID(2);
            mAq_All.setName((String) object.get("name"));
            mAq_All.setAdmuID((Integer) object.get("admuID"));

            /*保存父类坐标及父类附件信息、objectDetail【0】代表单纯的父类坐标*/
            JSONArray objectDetail = (JSONArray)object.get("aq_objdetails");
            /*父类坐标信息*/

            /*填写aq_objectAttribute表*/
            List<posAq_attachmentEntity> aq_attachmentsEmpty=new ArrayList<posAq_attachmentEntity>();
            JSONObject aot = (JSONObject)(objectDetail.get(0));
            posAq_objectAttributeEntity.setAttid(12);
            posAq_objectAttributeEntity.setValue(String.valueOf( aot.get("x")));
            //posAq_objectAttributeEntity.setAq_attachmentEntities(aq_attachmentsEmpty);
            aq_objectAttribute.add(posAq_objectAttributeEntity);
            posAq_objectAttributeEntity1.setAttid(13);
            posAq_objectAttributeEntity1.setValue(String.valueOf(aot.get("y")));
            //posAq_objectAttributeEntity.setAq_attachmentEntities(aq_attachmentsEmpty);
            aq_objectAttribute.add(posAq_objectAttributeEntity1);
            posAq_objectAttributeEntity5.setAttid(16);
            posAq_objectAttributeEntity5.setValue((String) object.get("address"));
            //posAq_objectAttributeEntity.setAq_attachmentEntities(aq_attachmentsEmpty);
            aq_objectAttribute.add(posAq_objectAttributeEntity5);
            mAq_All.setAq_objectAttribute(aq_objectAttribute);

            String fileName=this.getTargetFileName();
            String[] filesNames=this.getMultipleFilesTargetFileName();
            //List<String> fileNameList=new ArrayList<String>();//第一种数组转list方法
            /*存储父类附件坐标信息及附件信息*/
            /*判断父类是否有附件*/

            /*填写aq_attachments表*/
            if(objectDetail.size()-1>0){
                /*if (filesNames!=null){

                }*/
                //List<NewsAttachmentEntity> prjAttachmentList=new ArrayList<NewsAttachmentEntity>();
                for (int j=1;j<objectDetail.size();j++){
                    //String imageName=it.next();
                    /*父类附件表坐标信息*//*附件表*/
                    JSONObject aqtt = (JSONObject) (objectDetail.get(j));

                    /*存储附件信息*/
                    JSONArray aqDetails = (JSONArray) (aqtt.get("aq_attachments"));
                    for(int z=0;z<aqDetails.size();z++){
                        JSONObject aqDetailAttach = (JSONObject) (aqDetails.get(z));
                        posAq_attachmentEntity posAq_attachmentEntity=new posAq_attachmentEntity();
                        String imageName="";
                        String url= (String) aqDetailAttach.get("url");
                        String separator=File.separator;
                        String urlType="";
                        if(url.substring(0,1).equals("/")){
                            urlType=url.substring(url.indexOf("/")+1,url.indexOf("/",url.indexOf("/")+1) );
                            imageName=url.substring(url.lastIndexOf("/")+1,url.length());
                        }else {
                            urlType=url.substring(url.indexOf("\\")+1,url.indexOf("\\",url.indexOf("\\")+1));
                            imageName=url.substring(url.lastIndexOf("\\")+1,url.length());
                            currentSize+=1;
                        }

                            /*if(urlType.equals("storage")==false){
                                imageName=url.substring(url.lastIndexOf(""))
                                aqAttachmentEntity.setUrl(url);
                                mobileUrl.add(url);
                            }else{
                                imageName=filesNames[currentSize];
                                currentSize+=1;
                            }*/
                        posAq_attachmentEntity.setName((String) aqDetailAttach.get("name")+currentSize);
                        posAq_attachmentEntity.setFile_name(imageName);
                        posAq_attachmentEntity.setType((Integer) aqDetailAttach.get("type"));
                        aq_attachments.add(posAq_attachmentEntity);
                            /*posAq_attachmentEntity posAq_attachmentEntity=new posAq_attachmentEntity();
                            String imageName=filesNames[currentSize];
                            JSONObject aqDetailAttach = (JSONObject) (aqDetails.get(z));
                            posAq_attachmentEntity.setName((String) aqDetailAttach.get("name")+currentSize);
                            posAq_attachmentEntity.setFile_name(imageName);
                            posAq_attachmentEntity.setType((Integer) aqDetailAttach.get("type"));
                            aq_attachments.add(posAq_attachmentEntity);
                            currentSize+=1;*/
                    }
                }

                mAq_All.setAq_attachment(aq_attachments);
            }


            /*填写aq_objectDetail表*/
            /*存储子类基本信息表*/
            List<Aq_objectDetail> aq_objectDetail=new ArrayList<Aq_objectDetail>();
            JSONArray objectattributes = (JSONArray)object.get("aq_objectattributes");
            for(int i=0;i<objectattributes.size();i++){
                List<posAq_attachmentEntity> aq_attachments1=new ArrayList<posAq_attachmentEntity>();
                List<posAq_objectAttributeEntity> aq_objectAttribute1=new ArrayList<posAq_objectAttributeEntity>();
                posAq_objectAttributeEntity posAq_objectAttributeEntity3=new posAq_objectAttributeEntity();
                posAq_objectAttributeEntity posAq_objectAttributeEntity4=new posAq_objectAttributeEntity();
                JSONObject objectattribute= (JSONObject) objectattributes.get(i);
                String typeName=new String("无障碍停车位".getBytes("GBK"),"UTF-8");


                /*if(objectattribute.get("typeId").equals(13)==true){

                }*/

                Aq_objectDetail aq_objectDetails=new Aq_objectDetail();
                aq_objectDetails.setTypID(201);
                aq_objectDetails.setName((String) objectattribute.get("name"));


                /*子类坐标信息*/
                JSONArray objectattributeDetails = (JSONArray)objectattribute.get("aq_objdetails");
                JSONObject objectattributeDetail = (JSONObject)(objectattributeDetails.get(0));
                posAq_objectAttributeEntity3.setAttid(12);
                posAq_objectAttributeEntity3.setValue(String.valueOf(objectattributeDetail.get("x")) );
                aq_objectAttribute1.add(posAq_objectAttributeEntity3);
                posAq_objectAttributeEntity4.setAttid(13);
                posAq_objectAttributeEntity4.setValue(String.valueOf(objectattributeDetail.get("y")));
                aq_objectAttribute1.add(posAq_objectAttributeEntity1);


                /*子类附件及其坐标信息*/
                if(objectattributeDetails.size()-1>0) {
                    if (filesNames != null) {
                        for(int j=1;j<objectattributeDetails.size();j++){
                            JSONObject attributeDetail = (JSONObject) (objectattributeDetails.get(j));
                            JSONArray attributeAttachs= (JSONArray) attributeDetail.get("aq_attachments");
                            for(int z=0;z<attributeAttachs.size();z++){
                                String imageName="";
                                posAq_attachmentEntity posAq_attachmentEntity=new posAq_attachmentEntity();
                                //String imageName=filesNames[currentSize];
                                JSONObject attributeAttach=attributeAttachs.getJSONObject(z);
                                String url= (String) attributeAttach.get("url");
                                String separator=File.separator;
                                String urlType="";
                                if(url.substring(0,1).equals("/")){
                                    urlType=url.substring(url.indexOf("/")+1,url.indexOf("/",url.indexOf("/")+1) );
                                    imageName=url.substring(url.lastIndexOf("/")+1,url.length());
                                }else {
                                    urlType=url.substring(url.indexOf("\\")+1,url.indexOf("\\",url.indexOf("\\")+1));
                                    imageName=url.substring(url.lastIndexOf("\\")+1,url.length());
                                    currentSize+=1;
                                }

                                posAq_attachmentEntity.setName((String) attributeAttach.get("name")+currentSize);
                                posAq_attachmentEntity.setFile_name(imageName);
                                posAq_attachmentEntity.setType((Integer) attributeAttach.get("type"));
                                posAq_attachmentEntity.setName((String) attributeAttach.get("name"));
                                aq_attachments1.add(posAq_attachmentEntity);
                            }
                        }
                    }
                }

                aq_objectDetails.setAq_attachment(aq_attachments1);
                aq_objectDetails.setAq_detailAttribute(aq_objectAttribute1);
                aq_objectDetail.add(aq_objectDetails);
            }

            mAq_All.setAq_objDetail(aq_objectDetail);
        }
        return mAq_All;
    }

    /*调用pos接口*/
    public String interfaceUtil(String path, JSONObject fparam) {
        String objId="failed";
        try {
            URL url = new URL("http://47.93.237.6:8080/wheelchair/getData.jsp");
            //打开和url之间的连接
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            conn.setDoInput(true);

            conn.setRequestMethod("POST");

            conn.setUseCaches(false);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());


            String content="fname=uploaddata&fparam="+fparam.toString();

            out.write(content.getBytes("utf-8"));

            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line="";
            String line1="";
            while ((line = reader.readLine()) != null){
                line1=line;
            }

//            line=reader.readLine();
//            System.out.println(line);
            JSONObject result = JSONObject.fromObject(line1);
            JSONObject jsonObject= (JSONObject) result.get("result");
            objId= (String) jsonObject.get("objid");

            System.out.println("12222");
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objId;
    }

    public String interfaceDeleteUtil(String path,JSONObject fparam) {
        try {
            URL url = new URL("http://47.93.237.6:8080/wheelchair/getData.jsp");
            //打开和url之间的连接
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            conn.setDoInput(true);

            conn.setRequestMethod("POST");

            conn.setUseCaches(false);

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());


            String content="fname=deleteObj&fparam="+fparam.toString();

            out.write(content.getBytes("utf-8"));

            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line="";
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            System.out.println("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "";
    }


    public void getAttachMessage(){
        List<AllMessage> Aq_object=new ArrayList<AllMessage>();
        List<AqObjectEntity> aqObjectEntityList=userService.getAqObjectEntity(Integer.valueOf(admuId));
        if(aqObjectEntityList!=null){
            for (int i = 0; i <aqObjectEntityList.size() ; i++) {
                AllMessage allMessage=new AllMessage();
                List<ObjDetailEntity> aq_objdetails=new ArrayList<ObjDetailEntity>();
                List<ObjectAttributeEntity> aq_objectattributes=new ArrayList<ObjectAttributeEntity>();
                /*获取父类基本信息*/
                AqObjectEntity aqObjectEntity=aqObjectEntityList.get(i);
                allMessage.setAdmuId(aqObjectEntity.getAdmuId());
                allMessage.setObjId(aqObjectEntity.getObjId());
                allMessage.setComId(aqObjectEntity.getComId());
                allMessage.setTypId(aqObjectEntity.getTypId());
                allMessage.setStdId(aqObjectEntity.getStdId());
                allMessage.setName(aqObjectEntity.getName());
                allMessage.setGeojType(aqObjectEntity.getGeojType());
                allMessage.setObjNum(aqObjectEntity.getObjNum());
                allMessage.setIfLock(aqObjectEntity.getIfLock());
                allMessage.setMemo(aqObjectEntity.getMemo());
                allMessage.setAddress(aqObjectEntity.getAddress());
                allMessage.setServiceId(aqObjectEntity.getServiceId());
                allMessage.setCreateTime(aqObjectEntity.getCreateTime());
                allMessage.setIsPass(aqObjectEntity.getIsPass());
                allMessage.setDepart_id(aqObjectEntity.getDepartId());
                allMessage.setCheckid(aqObjectEntity.getCheckId());

                /*获取父类坐标，附件及其坐标*/
                List aqObjdetailEntityList=userService.getAqObjdetailEntity(aqObjectEntityList.get(i).getObjId());
                if(aqObjdetailEntityList!=null){
                    for (Object obj : aqObjdetailEntityList) {//需要for循环遍历取数据
                        /*存储父类坐标*/
                        Object[] objArray = (Object[]) obj;
                        ObjDetailEntity objDetailEntity=new ObjDetailEntity();
                        objDetailEntity.setDtlId((Integer) objArray[0]);
                        objDetailEntity.setName((String) objArray[1]);
                        objDetailEntity.setMemo((String)objArray[2]);
                        objDetailEntity.setObjId((Integer) objArray[3]);
                        String point= (String) objArray[4];
                        String point1= String.valueOf(point);
                        String x=point1.substring(point1.indexOf("(")+1, point1.indexOf(" "));
                        String y=point1.substring(point1.indexOf(" ")+1,point1.indexOf(")"));
                        objDetailEntity.setX(Double.valueOf(x));
                        objDetailEntity.setY(Double.valueOf(y));
                        objDetailEntity.setType((String) objArray[5]);
                        List<AqAttachmentEntity> aqAttachmentEntityListEmpty=new ArrayList<AqAttachmentEntity>();
                        objDetailEntity.setAq_attachments(aqAttachmentEntityListEmpty);
                        aq_objdetails.add(objDetailEntity);
                        /*JSON.toJSONBytes(aq_objdetails, SerializerFeature.DisableCircularReferenceDetect);*/

                        /*根据objId获取父类附件*/
                        /*封装一个新的entity类存附件及其坐标*/
                        Integer objId=aqObjectEntity.getObjId();
                        List attachmentAndLocationList=userService.getAqAttachmentAndLocation(objId,1);
                        aq_objdetails=getAttachmentAndLocation(attachmentAndLocationList,aq_objdetails,allMessage);
                        allMessage.setAq_objdetails(aq_objdetails);
                        /*子类信息*/
                        List<AqObjectattributeEntity> aqObjectattributeEntities=userService.getAqObejctAttributeEntity(objId);
                        if(aqObjectattributeEntities!=null){
                            for (int k = 0; k <aqObjectattributeEntities.size() ; k++) {
                                List<ObjDetailEntity> aq_objdetails1=new ArrayList<ObjDetailEntity>();
                                ObjectAttributeEntity objectAttributeEntity=new ObjectAttributeEntity();
                                AqObjectattributeEntity aqObjectattributeEntity=aqObjectattributeEntities.get(k);
                                objectAttributeEntity.setAttid(aqObjectattributeEntity.getAttid());
                                objectAttributeEntity.setName(aqObjectattributeEntity.getName());
                                objectAttributeEntity.setOaId(aqObjectattributeEntity.getOaId());
                                objectAttributeEntity.setObjId(aqObjectattributeEntity.getObjId());
                                objectAttributeEntity.setValue(aqObjectattributeEntity.getValue());
                                objectAttributeEntity.setValueType(aqObjectattributeEntity.getValueType());
                                objectAttributeEntity.setTypeId(aqObjectattributeEntity.getTypeId());

                                /*获取子类坐标*/
                                List attributeDetail=userService.getAttributeAqObjdetailEntity(aqObjectattributeEntity.getOaId());
                                for (Object obj1 : attributeDetail) {//需要for循环遍历取数据
                                    /*存储父类坐标*/
                                    Object[] objArray1 = (Object[]) obj1;
                                    ObjDetailEntity objDetailEntity1 = new ObjDetailEntity();
                                    objDetailEntity1.setDtlId((Integer) objArray1[0]);
                                    objDetailEntity1.setName((String) objArray1[1]);
                                    objDetailEntity1.setMemo((String) objArray1[2]);
                                    objDetailEntity1.setObjId((Integer) objArray1[3]);
                                    String point3 = (String) objArray1[4];
                                    String point4 = String.valueOf(point);
                                    String x3 = point1.substring(point3.indexOf("(") + 1, point1.indexOf(" "));
                                    String y4 = point1.substring(point4.indexOf(" ") + 1, point1.indexOf(")"));
                                    objDetailEntity1.setX(Double.valueOf(x3));
                                    objDetailEntity1.setY(Double.valueOf(y4));
                                    objDetailEntity1.setType((String) objArray1[5]);
                                    List<AqAttachmentEntity> aqAttachmentEntityListEmpty1 = new ArrayList<AqAttachmentEntity>();
                                    objDetailEntity1.setAq_attachments(aqAttachmentEntityListEmpty1);
                                    aq_objdetails1.add(objDetailEntity1);
                                    //*获取子类附件和附件坐标*//*
                                    List attachmentAndLocationList1 = userService.getAttributeAqAttachmentAndLocation(aqObjectattributeEntity.getOaId());
                                    List<ObjDetailEntity> objDetailEntities1 = getAttachmentAndLocation(attachmentAndLocationList1, aq_objdetails1, allMessage);
                                    objectAttributeEntity.setAq_objdetails(objDetailEntities1);
                                    aq_objectattributes.add(objectAttributeEntity);
                                }
                            }
                            allMessage.setAq_objectattributes(aq_objectattributes);
                        }
                    }
                }
                Aq_object.add(allMessage);
            }
        }
        writeJson(Aq_object);

    }

    public List<ObjDetailEntity>  getAttachmentAndLocation(List<AttachmentAndLocation> attachmentAndLocationList,List<ObjDetailEntity> aq_objdetails,AllMessage allMessage){
        if(attachmentAndLocationList!=null){
            for (Object obj : attachmentAndLocationList) {//需要for循环遍历取数据
                /*存储附件及其坐标*/
                Object[] objArray = (Object[]) obj;
                List<AqAttachmentEntity> aq_attachments=new ArrayList<AqAttachmentEntity>();
                ObjDetailEntity objDetailEntity1=new ObjDetailEntity();
                AqAttachmentEntity aqAttachmentEntity=new AqAttachmentEntity();
                aqAttachmentEntity.setAttId((Integer) objArray[0]);
                aqAttachmentEntity.setName((String) objArray[1]);
                aqAttachmentEntity.setUrl((String) objArray[2]);
                aqAttachmentEntity.setType((Integer) objArray[3]);
                aqAttachmentEntity.setTableId((Integer) objArray[4]);
                aq_attachments.add(aqAttachmentEntity);
                objDetailEntity1.setDtlId((Integer) objArray[5]);
                objDetailEntity1.setName((String) objArray[6]);
                objDetailEntity1.setMemo((String) objArray[7]);
                objDetailEntity1.setObjId((Integer) objArray[8]);
                String point2= (String) objArray[9];
                String point3= String.valueOf(point2);
                String x1=point3.substring(point3.indexOf("(")+1, point3.indexOf(" "));
                String y1=point3.substring(point3.indexOf(" ")+1,point3.indexOf(")"));
                objDetailEntity1.setX(Double.valueOf(x1));
                objDetailEntity1.setY(Double.valueOf(y1));
                objDetailEntity1.setType((String) objArray[10]);
                objDetailEntity1.setAq_attachments(aq_attachments);
                aq_objdetails.add(objDetailEntity1);
                JSON.toJSONBytes(aq_objdetails, SerializerFeature.DisableCircularReferenceDetect);
            }

        }
        return aq_objdetails;
    }

    public void alterMessage(){
        JSONObject object = JSONObject.fromObject(jsonStr);
        Integer objId=(Integer) object.get("objID");
        AqObjectEntity aqObjectEntity=userService.alterAqObjectEntity((Integer) object.get("objID"));
        /*if(aqObjectEntity.getCheckId()==1){*/
        JSONObject urlList= (JSONObject) object.get("urlhashMap");
        JSONArray urls= (JSONArray) urlList.get("urlList");
        List urlCompany=new ArrayList();
        List mobileUrls=new ArrayList();
        for (int i = 0; i <urls.size() ; i++) {
            String urlType="";
            String url= (String) urls.get(i);
            if(url.substring(0,1).equals("/")){
                urlType=url.substring(url.indexOf("/")+1,url.indexOf("/",url.indexOf("/")+1) );
            }else {
                urlType=url.substring(url.indexOf("\\")+1,url.indexOf("\\",url.indexOf("\\")+1));
            }
            urlCompany.add(urlType);
            mobileUrls.add(url);
        }
        if(urlCompany.contains("storage")){
            /*如果有上传就判断是否上传成功，否则直接调用*/
            String fileUploadResult=super.execute();
            if (fileUploadResult==SUCCESS){
                alterObjectMessage(object,aqObjectEntity,mobileUrls);

                /*调用沙老师更改接口*/
                alterMobileMessage(object,aqObjectEntity,objId);
            }else if (fileUploadResult==ERROR){
                map.put("msg2","uploadFile failed");
            }
        }else{
            alterObjectMessage(object,aqObjectEntity,mobileUrls);
            /*调用沙老师更改接口*/
            alterMobileMessage(object,aqObjectEntity,objId);
        }

            writeJson(map);
       /* }else {
            map.put("msg2","已审核，无法更改");
        }*/
    }

    public void alterMobileMessage(JSONObject object,AqObjectEntity aqObjectEntity,Integer objId){
        /*沙老师接口*/
        /*首先根据objId获取沙老师端的objId*/
        String mobileObjId=userService.getMobileObjId(objId);
        JSONObject object1=new JSONObject();
        object1.put("objid",mobileObjId);
        interfaceDeleteUtil("http://47.93.237.6:8080/wheelchair/getData.jsp",object1);
        addToMobileCar(object,aqObjectEntity);
    }

    public void alterObjectMessage(JSONObject object,AqObjectEntity aqObjectEntity,List urls){
        //JSONObject object = JSONObject.fromObject(jsonStr);
        Date date=new Date();
        Timestamp time = new Timestamp(date.getTime());
        //AqObjectEntity aqObjectEntity=userService.alterAqObjectEntity((Integer) object.get("objID"));
        aqObjectEntity.setAddress((String) object.get("address"));
        aqObjectEntity.setAdmuId((Integer) object.get("admuID"));
        aqObjectEntity.setComId((Integer) object.get("comID"));
        aqObjectEntity.setMemo((String) object.get("memo"));
        aqObjectEntity.setName((String) object.get("name"));
        aqObjectEntity.setObjNum((Integer) object.get("objNum"));
        aqObjectEntity.setStdId((Integer) object.get("stdID"));
        aqObjectEntity.setTypId((Integer) object.get("typID"));
        aqObjectEntity.setGeojType((short) 1);
        aqObjectEntity.setIsPass((Integer)object.get("isPass"));
        aqObjectEntity.setServiceId((Integer) object.get("serviceID"));
        aqObjectEntity.setIfLock((short) 0);
        aqObjectEntity.setCheckId((Integer) object.get("checkid"));
        aqObjectEntity.setCreateTime(time);
        userService.saveAqObject(aqObjectEntity);


        /*先删掉服务器上的文件，再删除关联的两个表（objectdetail/attachment）*/
        //删除服务器上的文件
        List urlList=userService.getDeleteUrl((Integer) object.get("objID"));
        List deleteUrls=new ArrayList();
        if(urlList!=null){
            for (int i = 0; i <urlList.size() ; i++) {
                String url= (String) urlList.get(i);
                if(urls.contains(url)){
                }else {
                    deleteUrls.add(url);
                }
            }
        }


        if(deleteUrls!=null){
            deleteFile(deleteUrls);
        }

        //删除detail表
        List dtlIds=userService.getDtlId((Integer) object.get("objID"));
        if(dtlIds!=null){
            for (int i = 0; i <dtlIds.size() ; i++) {
                userService.deleteAqObjectDetailEntity((Integer) dtlIds.get(i));
            }
        }
        //删除attribute表
        List oaIds=userService.getOaId((Integer) object.get("objID"));
        if(oaIds!=null){
            for (int i = 0; i <oaIds.size() ; i++) {
                userService.deleteAqObjectattributeEntity((Integer) oaIds.get(i));
            }
        }

        addPublicMessage(object,aqObjectEntity);
        map.put("objId",aqObjectEntity.getObjId());
    }

    public void addPublicMessage(JSONObject object,AqObjectEntity aqObjectEntity){
        int currentSize=0;
        List mobileUrl=new ArrayList();
        /*保存父类坐标及父类附件信息、objectDetail【0】代表单纯的父类坐标*/
        JSONArray objectDetail = (JSONArray)object.get("aq_objdetails");
        /*父类坐标信息*/
        JSONObject aot = (JSONObject)(objectDetail.get(0));
        String x= String.valueOf(aot.get("x"));
        String y= String.valueOf(aot.get("y"));
        String location ="point("+x + " " + y+")";
        String row="(objId,memo,name,type,location)";
        String values="values("+aqObjectEntity.getObjId()+",'"+aot.get("memo")+"','"+aot.get("name")+"',"+aot.get("type")+",geomfromtext('"+location+"'))";
        String insertSql = "insert into  aq_objdetail" + row + " " + values + "";
        userService.insertAqObjdetail(insertSql);

        String fileName=this.getTargetFileName();
        String[] filesNames=this.getMultipleFilesTargetFileName();
        //List<String> fileNameList=new ArrayList<String>();//第一种数组转list方法
        /*存储父类附件坐标信息及附件信息*/
        /*判断父类是否有附件*/
        if(objectDetail.size()-1>0){
            for (int j=1;j<objectDetail.size();j++){
                //String imageName=it.next();
                /*父类附件表坐标信息*//*附件表*/
                JSONObject aqtt = (JSONObject) (objectDetail.get(j));
                String ax= String.valueOf(aqtt.get("x"));
                String ay= String.valueOf(aqtt.get("y"));
                String alocation ="point("+ax + " " + ay+")";
                String arow="(objId,memo,name,type,location)";
                String avalues="values("+aqObjectEntity.getObjId()+",'"+aqtt.get("memo")+"','"+aqtt.get("name")+"',"+aqtt.get("type")+",geomfromtext('"+alocation+"'))";
                String insertSqla= "insert into  aq_objdetail" + arow + " " + avalues + "";
                userService.insertAqObjdetail(insertSqla);

                /*获得dtlId*/
                Integer dtlId=userService.getTheLatestDtlId();

                /*存储附件信息*/
                JSONArray aqDetails = (JSONArray) (aqtt.get("aq_attachments"));
                for(int z=0;z<aqDetails.size();z++){
                    JSONObject aqDetailAttach = (JSONObject) (aqDetails.get(z));
                    AqAttachmentEntity aqAttachmentEntity = new AqAttachmentEntity();
                    aqAttachmentEntity.setName((String) aqDetailAttach.get("name"));
                    aqAttachmentEntity.setTableId(dtlId);
                    aqAttachmentEntity.setType(1);
                    String url= (String) aqDetailAttach.get("url");
                    String separator=File.separator;
                    String urlType="";
                    if(url.substring(0,1).equals("/")){
                        urlType=url.substring(url.indexOf("/")+1,url.indexOf("/",url.indexOf("/")+1) );
                    }else {
                        urlType=url.substring(url.indexOf("\\")+1,url.indexOf("\\",url.indexOf("\\")+1));
                    }

                    if(urlType.equals("storage")==false){
                        aqAttachmentEntity.setUrl(url);
                        mobileUrl.add(url);
                    }else{
                        String imageName=filesNames[currentSize];
                        currentSize+=1;
                        aqAttachmentEntity.setUrl(this.attImageFolderPath + imageName);
                    }
                    userService.saveAqAttachment(aqAttachmentEntity);
                }
            }
        }

        /*存储子类基本信息表*/
        JSONArray objectattributes = (JSONArray)object.get("aq_objectattributes");
        for(int i=0;i<objectattributes.size();i++){
            JSONObject objectattribute= (JSONObject) objectattributes.get(i);
            AqObjectattributeEntity aqObjectattributeEntity=new AqObjectattributeEntity();
            aqObjectattributeEntity.setName((String) objectattribute.get("name"));
            aqObjectattributeEntity.setValue((String) objectattribute.get("value"));
            aqObjectattributeEntity.setObjId(aqObjectEntity.getObjId());
            aqObjectattributeEntity.setValueType((Integer) objectattribute.get("valueType"));
            aqObjectattributeEntity.setTypeId((Integer) objectattribute.get("typeId"));
            userService.saveAqObjectattribute(aqObjectattributeEntity);

            /*子类坐标信息*/
            JSONArray objectattributeDetails = (JSONArray)objectattribute.get("aq_objdetails");
            JSONObject objectattributeDetail = (JSONObject)(objectattributeDetails.get(0));
            String objectattributex= String.valueOf(objectattributeDetail.get("x"));
            String objectattributey= String.valueOf(objectattributeDetail.get("y"));
            String objectattributelocation ="point("+objectattributex + " " + objectattributey+")";
            String objectattributerow="(objId,oaId,memo,name,type,location)";
            String objectattributevalues="values("+aqObjectEntity.getObjId()+","+aqObjectattributeEntity.getOaId()+",'"+objectattributeDetail.get("memo")+"','"+objectattributeDetail.get("name")+"',"+objectattributeDetail.get("type")+",geomfromtext('"+objectattributelocation+"'))";
            String objectattributeinsertSql = "insert into  aq_objdetail" + objectattributerow + " " + objectattributevalues + "";
            userService.insertAqObjdetail(objectattributeinsertSql);

            /*子类附件及其坐标信息*/
            if(objectattributeDetails.size()-1>0) {
                for(int j=1;j<objectattributeDetails.size();j++){
                    JSONObject attributeDetail = (JSONObject) (objectattributeDetails.get(j));
                    String ax= String.valueOf(attributeDetail.get("x"));
                    String ay= String.valueOf(attributeDetail.get("y"));
                    String alocation ="point("+ax + " " + ay+")";
                    String arow="(objId,oaId,memo,name,type,location)";
                    String avalues="values("+aqObjectEntity.getObjId()+","+aqObjectattributeEntity.getOaId()+",'"+attributeDetail.get("memo")+"','"+attributeDetail.get("name")+"',"+attributeDetail.get("type")+",geomfromtext('"+alocation+"'))";
                    String insertSqla= "insert into  aq_objdetail" + arow + " " + avalues + "";
                    userService.insertAqObjdetail(insertSqla);

                    /*获得dtlId*/
                    Integer dtlId=userService.getTheLatestDtlId();

                    JSONArray attributeAttachs= (JSONArray) attributeDetail.get("aq_attachments");
                    for(int z=0;z<attributeAttachs.size();z++){
                        JSONObject attributeAttach=attributeAttachs.getJSONObject(z);
                        AqAttachmentEntity aqAttachmentEntity = new AqAttachmentEntity();
                        aqAttachmentEntity.setName((String) attributeAttach.get("name"));
                        aqAttachmentEntity.setTableId(dtlId);
                        aqAttachmentEntity.setType(2);

                        String url= (String) attributeAttach.get("url");
                        String separator=File.separator;
                        String urlType="";
                        if(url.substring(0,1).equals("/")){
                            urlType=url.substring(url.indexOf("/")+1,url.indexOf("/",url.indexOf("/")+1) );
                        }else {
                            urlType=url.substring(url.indexOf("\\")+1,url.indexOf("\\",url.indexOf("\\")+1));
                        }

                        if(urlType.equals("storage")==false){
                            aqAttachmentEntity.setUrl(url);
                            mobileUrl.add(url);
                        }else{
                            String imageName=filesNames[currentSize];
                            aqAttachmentEntity.setUrl(this.attImageFolderPath + imageName);
                            currentSize+=1;
                        }
                        /*  aqAttachmentEntity.setUrl(this.attImageFolderPath + imageName);*/
                        userService.saveAqAttachment(aqAttachmentEntity);

                    }
                }
            }
        }

    }

    public void deleteMessage(){
        /*删除沙老师接口停车位*/
        String mobileObjId=userService.getMobileObjId(Integer.valueOf(objId));
        JSONObject object1=new JSONObject();
        object1.put("objid",mobileObjId);
        interfaceDeleteUtil("http://47.93.237.6:8080/wheelchair/getData.jsp",object1);

        List urlList=userService.getDeleteUrl(Integer.valueOf(objId));
        deleteFile(urlList);
        userService.deletAqObjectEntity(Integer.valueOf(objId));

        map.put("objId",objId);
        writeJson(map);
    }

    public void deleteFile(List urlList){
        if(urlList.size()>0){
            for (int i = 0; i <urlList.size() ; i++) {
                String  realPath  = ServletActionContext.getServletContext().getRealPath((String) urlList.get(i));
                File file = new File(realPath);
                // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
                if (file.exists() && file.isFile()) {
                    if (file.delete()) {
                        System.out.println("删除成功"+realPath);
                    } else {
                        System.out.println("删除单个文件失败！");
                    }
                } else {
                    System.out.println("文件不存在！");
                }
            }
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user) {
        this.userName = user;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public String getAdmuId() {
        return admuId;
    }

    public void setAdmuId(String admuId) {
        this.admuId = admuId;
    }

    public String getAttImageFolderPath() {
        return attImageFolderPath;
    }

    public void setAttImageFolderPath(String attImageFolderPath) {
        this.attImageFolderPath = attImageFolderPath;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
