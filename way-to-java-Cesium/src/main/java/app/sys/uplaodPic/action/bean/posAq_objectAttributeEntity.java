package app.sys.uplaodPic.action.bean;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class posAq_objectAttributeEntity {
    private Integer attid;
    private String value;
   /* List<posAq_attachmentEntity> aq_attachmentEntities=new ArrayList<posAq_attachmentEntity>();*/
    public posAq_objectAttributeEntity(Integer attid, String value){
        this.attid=attid;
        this.value=value;
        //this.aq_attachmentEntities=aq_attachmentEntities;
    }

   /* public List<posAq_attachmentEntity> getAq_attachmentEntities() {
        return aq_attachmentEntities;
    }

    public void setAq_attachmentEntities(List<posAq_attachmentEntity> aq_attachmentEntities) {
        this.aq_attachmentEntities = aq_attachmentEntities;
    }
*/
    public posAq_objectAttributeEntity(){};

    public Integer getAttid() {
        return attid;
    }

    public void setAttid(Integer attid) {
        this.attid = attid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
