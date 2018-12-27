package app.sys.uplaodPic.action.mobileEntity;

import app.sys.uplaodPic.vo.AqAttachmentEntity;

import java.util.ArrayList;
import java.util.List;

public class ObjDetailEntity {
    private int dtlId;
    private String name;
    private String memo;
    private Double x;
    private Double y;
    private Integer objId;
    /*private Geometry locationx;*/
    private String type;
    private List<AqAttachmentEntity> aq_attachments=new ArrayList<AqAttachmentEntity>();

    public  ObjDetailEntity(){};

    public ObjDetailEntity(int dtlId, String name, String memo, Double x, Double y, Integer objId, String type, List<AqAttachmentEntity> aq_attachments){
        this.dtlId=dtlId;
        this.name=name;
        this.memo=memo;
        this.x=x;
        this.y=y;
        this.objId=objId;
        this.type=type;
        this.aq_attachments=aq_attachments;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AqAttachmentEntity> getAq_attachments() {
        return aq_attachments;
    }

    public void setAq_attachments(List<AqAttachmentEntity> aq_attachments) {
        this.aq_attachments = aq_attachments;
    }
}
