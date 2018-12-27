package app.sys.uplaodPic.action.bean;

import java.util.ArrayList;
import java.util.List;

public class Aq_objectDetail {
    Integer typID;
    String name;

    private List<posAq_attachmentEntity> aq_attachment=new ArrayList<posAq_attachmentEntity>();
    private List<posAq_objectAttributeEntity> aq_detailAttribute=new ArrayList<posAq_objectAttributeEntity>();

    public Aq_objectDetail(){};

    public Aq_objectDetail(Integer typID,String name,List<posAq_objectAttributeEntity> aq_detailAttribute,List<posAq_attachmentEntity> aq_attachment){
        this.typID=typID;
        this.name=name;
        this.aq_attachment=aq_attachment;
        this.aq_detailAttribute=aq_detailAttribute;
    }

    public Integer getTypID() {
        return typID;
    }

    public void setTypID(Integer typID) {
        this.typID = typID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<posAq_attachmentEntity> getAq_attachment() {
        return aq_attachment;
    }

    public void setAq_attachment(List<posAq_attachmentEntity> aq_attachments) {
        this.aq_attachment = aq_attachments;
    }

    public List<posAq_objectAttributeEntity> getAq_detailAttribute() {
        return aq_detailAttribute;
    }

    public void setAq_detailAttribute(List<posAq_objectAttributeEntity> aq_detailAttribute) {
        this.aq_detailAttribute = aq_detailAttribute;
    }
}
