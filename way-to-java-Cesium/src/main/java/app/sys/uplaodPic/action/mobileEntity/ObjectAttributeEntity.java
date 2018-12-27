package app.sys.uplaodPic.action.mobileEntity;

import java.util.ArrayList;
import java.util.List;

public class ObjectAttributeEntity {
    private String name;
    private String value;
    private Integer objId;
    private int oaId;
    private Integer valueType;
    private Integer attid;
    private Integer typeId;
    List<ObjDetailEntity> aq_objdetails=new ArrayList<ObjDetailEntity>();

    public ObjectAttributeEntity(){};

    public ObjectAttributeEntity(String name, String value, Integer objId, int oaId, Integer valueType, Integer attid, List<ObjDetailEntity> aq_objdetails,Integer typeId){
        this.name=name;
        this.value=value;
        this.objId=objId;
        this.oaId=oaId;
        this.valueType=valueType;
        this.attid=attid;
        this.aq_objdetails=aq_objdetails;
        this.typeId=typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public int getOaId() {
        return oaId;
    }

    public void setOaId(int oaId) {
        this.oaId = oaId;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Integer getAttid() {
        return attid;
    }

    public void setAttid(Integer attid) {
        this.attid = attid;
    }

    public List<ObjDetailEntity> getAq_objdetails() {
        return aq_objdetails;
    }

    public void setAq_objdetails(List<ObjDetailEntity> aq_objdetails) {
        this.aq_objdetails = aq_objdetails;
    }
}
