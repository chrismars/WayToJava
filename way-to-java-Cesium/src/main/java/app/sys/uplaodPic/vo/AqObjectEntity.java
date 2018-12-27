package app.sys.uplaodPic.vo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "aq_object", schema = "canlian", catalog = "")
public class AqObjectEntity {
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
    private Timestamp createTime;
    private Integer isPass;
    private Integer departId;
    private Integer checkId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "objID", nullable = false)
    public int getObjId() {
        return objId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    @Basic
    @Column(name = "comID", nullable = true)
    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    @Basic
    @Column(name = "typID", nullable = true)
    public Integer getTypId() {
        return typId;
    }

    public void setTypId(Integer typId) {
        this.typId = typId;
    }

    @Basic
    @Column(name = "stdID", nullable = true)
    public Integer getStdId() {
        return stdId;
    }

    public void setStdId(Integer stdId) {
        this.stdId = stdId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "geojType", nullable = true)
    public Short getGeojType() {
        return geojType;
    }

    public void setGeojType(Short geojType) {
        this.geojType = geojType;
    }

    @Basic
    @Column(name = "objNum", nullable = true)
    public Integer getObjNum() {
        return objNum;
    }

    public void setObjNum(Integer objNum) {
        this.objNum = objNum;
    }

    @Basic
    @Column(name = "ifLock", nullable = true)
    public Short getIfLock() {
        return ifLock;
    }

    public void setIfLock(Short ifLock) {
        this.ifLock = ifLock;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 500)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 300)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "admuID", nullable = true)
    public Integer getAdmuId() {
        return admuId;
    }

    public void setAdmuId(Integer admuId) {
        this.admuId = admuId;
    }

    @Basic
    @Column(name = "serviceID", nullable = true)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AqObjectEntity that = (AqObjectEntity) o;

        if (objId != that.objId) return false;
        if (comId != null ? !comId.equals(that.comId) : that.comId != null) return false;
        if (typId != null ? !typId.equals(that.typId) : that.typId != null) return false;
        if (stdId != null ? !stdId.equals(that.stdId) : that.stdId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (geojType != null ? !geojType.equals(that.geojType) : that.geojType != null) return false;
        if (objNum != null ? !objNum.equals(that.objNum) : that.objNum != null) return false;
        if (ifLock != null ? !ifLock.equals(that.ifLock) : that.ifLock != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (admuId != null ? !admuId.equals(that.admuId) : that.admuId != null) return false;
        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objId;
        result = 31 * result + (comId != null ? comId.hashCode() : 0);
        result = 31 * result + (typId != null ? typId.hashCode() : 0);
        result = 31 * result + (stdId != null ? stdId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (geojType != null ? geojType.hashCode() : 0);
        result = 31 * result + (objNum != null ? objNum.hashCode() : 0);
        result = 31 * result + (ifLock != null ? ifLock.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (admuId != null ? admuId.hashCode() : 0);
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "isPass", nullable = true)
    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    @Basic
    @Column(name = "depart_id", nullable = true)
    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }



    @Basic
    @Column(name = "checkID", nullable = true)
    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }
}
