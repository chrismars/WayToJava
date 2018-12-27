package app.sys.uplaodPic.action.mobileEntity;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AllMessage {
    private int objId;
    private Integer comId;
    private Integer typId;
    private Integer stdId;
    private String name;
    private Short geojType;
    private Integer objNum;
    private Short ifLock;
    private String memo;
    private String address;
    private Integer admuId;
    private Integer serviceId;
    private java.sql.Timestamp createTime;
    private Integer isPass;
    private Integer depart_id;
    private Integer checkid;
    List<ObjDetailEntity> aq_objdetails=new ArrayList<ObjDetailEntity>();
    List<ObjectAttributeEntity> aq_objectattributes=new ArrayList<ObjectAttributeEntity>();

    public AllMessage(){};

    public AllMessage(int objId, Integer comId, Integer typId, Integer stdId, String name, Short geojType, Integer objNum,Integer depart_id,Integer checkid,
                      Short ifLock, String memo, String address, Integer admuId, Integer serviceId, java.sql.Timestamp createTime, Integer isPass, List<ObjDetailEntity> objDetailEntities, List<ObjectAttributeEntity> objectAttributeEntities){
        this.objId=objId;
        this.comId=comId;
        this.typId=typId;
        this.stdId=stdId;
        this.name=name;
        this.geojType=geojType;
        this.objNum=objNum;
        this.ifLock=ifLock;
        this.memo=memo;
        this.address=address;
        this.admuId=admuId;
        this.serviceId=serviceId;
        this.createTime=createTime;
        this.isPass=isPass;
        this.depart_id=depart_id;
        this.checkid=checkid;
        this.aq_objdetails=objDetailEntities;
        this.aq_objectattributes=objectAttributeEntities;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getTypId() {
        return typId;
    }

    public void setTypId(Integer typId) {
        this.typId = typId;
    }

    public Integer getStdId() {
        return stdId;
    }

    public void setStdId(Integer stdId) {
        this.stdId = stdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getGeojType() {
        return geojType;
    }

    public void setGeojType(Short geojType) {
        this.geojType = geojType;
    }

    public Integer getObjNum() {
        return objNum;
    }

    public void setObjNum(Integer objNum) {
        this.objNum = objNum;
    }

    public Short getIfLock() {
        return ifLock;
    }

    public void setIfLock(Short ifLock) {
        this.ifLock = ifLock;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAdmuId() {
        return admuId;
    }

    public void setAdmuId(Integer admuId) {
        this.admuId = admuId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public List<ObjDetailEntity> getAq_objdetails() {
        return aq_objdetails;
    }

    public void setAq_objdetails(List<ObjDetailEntity> aq_objdetails) {
        this.aq_objdetails = aq_objdetails;
    }

    public List<ObjectAttributeEntity> getAq_objectattributes() {
        return aq_objectattributes;
    }

    public void setAq_objectattributes(List<ObjectAttributeEntity> aq_objectattributes) {
        this.aq_objectattributes = aq_objectattributes;
    }

    public Integer getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(Integer depart_id) {
        this.depart_id = depart_id;
    }

    public Integer getCheckid() {
        return checkid;
    }

    public void setCheckid(Integer checkid) {
        this.checkid = checkid;
    }
}
