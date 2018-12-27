package app.sys.uplaodPic.action.bean;


import app.sys.uplaodPic.vo.AqAttachmentEntity;
import app.sys.uplaodPic.vo.AqObjdetailEntity;
import app.sys.uplaodPic.vo.AqObjectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chowjunye on 2018/3/6.
 */

public class Aq_ALL {
    private Integer comID;
    private Integer typID;
    private Integer stdID;
    private String name;
    private Integer admuID;

    /*private List<AqObjectEntity> aq_object=new ArrayList<AqObjectEntity>();*/

    private List<Aq_objectDetail> aq_objDetail=new ArrayList<Aq_objectDetail>();
    private List<posAq_attachmentEntity> aq_attachment=new ArrayList<posAq_attachmentEntity>();
    private List<posAq_objectAttributeEntity> aq_objectAttribute=new ArrayList<posAq_objectAttributeEntity>();

   /* public List<AqObjectEntity> getAq_object() {
        return aq_object;
    }

    public void setAq_object(List<AqObjectEntity> aq_object) {
        this.aq_object = aq_object;
    }*/

    public List<Aq_objectDetail> getAq_objDetail() {
        return aq_objDetail;
    }

    public void setAq_objDetail(List<Aq_objectDetail> aq_objdetail) {
        this.aq_objDetail = aq_objdetail;
    }

    public List<posAq_attachmentEntity> getAq_attachment() {
        return aq_attachment;
    }

    public void setAq_attachment(List<posAq_attachmentEntity> aq_attachments) {
        this.aq_attachment = aq_attachments;
    }

    public List<posAq_objectAttributeEntity> getAq_objectAttribute() {
        return aq_objectAttribute;
    }

    public void setAq_objectAttribute(List<posAq_objectAttributeEntity> aq_objectAttribute) {
        this.aq_objectAttribute = aq_objectAttribute;
    }

    public Integer getComID() {
        return comID;
    }

    public void setComID(Integer comID) {
        this.comID = comID;
    }

    public Integer getTypID() {
        return typID;
    }

    public void setTypID(Integer typID) {
        this.typID = typID;
    }

    public Integer getStdID() {
        return stdID;
    }

    public void setStdID(Integer stdID) {
        this.stdID = stdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAdmuID() {
        return admuID;
    }

    public void setAdmuID(Integer admuID) {
        this.admuID = admuID;
    }

    public Aq_ALL(Integer comID, Integer stdID, Integer typID, String name, Integer admuID, List<posAq_objectAttributeEntity> aq_objectAttribute, List<posAq_attachmentEntity> aq_attachments,List<Aq_objectDetail> aq_objdetails) {
        //this.aq_object = aq_object;
        this.comID=comID;
        this.stdID=stdID;
        this.typID=typID;
        this.name=name;
        this.admuID=admuID;
        this.aq_objectAttribute=aq_objectAttribute;
        this.aq_objDetail = aq_objdetails;
        this.aq_attachment = aq_attachments;
    }

    public Aq_ALL() {
    }
}
