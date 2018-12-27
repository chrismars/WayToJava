package app.sys.uplaodPic.vo;

import javax.persistence.*;

@Entity
@Table(name = "aq_objectattribute", schema = "canlian", catalog = "")
public class AqObjectattributeEntity {
    private String name;
    private String value;
    private Integer objId;
    private int oaId;
    private Integer valueType;
    private Integer attid;
    private Integer typeId;

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 50)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "objID", nullable = true)
    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oaID", nullable = false)
    public int getOaId() {
        return oaId;
    }

    public void setOaId(int oaId) {
        this.oaId = oaId;
    }

    @Basic
    @Column(name = "valueType", nullable = true)
    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    @Basic
    @Column(name = "attid", nullable = true)
    public Integer getAttid() {
        return attid;
    }

    public void setAttid(Integer attid) {
        this.attid = attid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AqObjectattributeEntity that = (AqObjectattributeEntity) o;

        if (oaId != that.oaId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (objId != null ? !objId.equals(that.objId) : that.objId != null) return false;
        if (valueType != null ? !valueType.equals(that.valueType) : that.valueType != null) return false;
        if (attid != null ? !attid.equals(that.attid) : that.attid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (objId != null ? objId.hashCode() : 0);
        result = 31 * result + oaId;
        result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
        result = 31 * result + (attid != null ? attid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "typeId", nullable = true)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
