package app.sys.uplaodPic.action.mobileEntity;

import java.awt.*;

public class AttachmentAndLocation {
    Integer attId;
    String name;
    String url;
    String type;
    Integer tableId;
    Integer dtlId;
    String name1;
    String memo;
    Integer objId;
    Point location;
    Integer type1;
    Integer oaId;

    public AttachmentAndLocation(){};

    public AttachmentAndLocation(Integer attId,String attachmentName,String url, String attachmentType,
                                 Integer tableId, Integer dtlId,String detailName,String memo,Integer objId,Point location, Integer detailType,Integer oaId){
        this.attId=attId;
        this.name=attachmentName;
        this.url=url;
        this.type=attachmentType;
        this.tableId=tableId;
        this.dtlId=dtlId;
        this.name1=detailName;
        this.memo=memo;
        this.objId=objId;
        this.location=location;
        this.type1=detailType;
        this.oaId=oaId;
    }

    public Integer getAttId() {
        return attId;
    }

    public void setAttId(Integer attId) {
        this.attId = attId;
    }

    public String getName() {
        return name;
    }

    public void setName(String attachmentName) {
        this.name = attachmentName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getDtlId() {
        return dtlId;
    }

    public void setDtlId(Integer dtlId) {
        this.dtlId = dtlId;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String detailName) {
        this.name1 = detailName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getType1() {
        return type1;
    }

    public void setType1(Integer type1) {
        this.type1 = type1;
    }

    public Integer getOaId() {
        return oaId;
    }

    public void setOaId(Integer oaId) {
        this.oaId = oaId;
    }
}
