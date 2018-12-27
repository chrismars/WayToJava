package app.sys.uplaodPic.vo;

/*import org.geolatte.geom.Geometry;*/

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "aq_objdetail", schema = "canlian", catalog = "")
public class AqObjdetailEntity {
    private int dtlId;
    private String name;
    private String memo;
    private Integer objId;
    /*private Geometry locationx;*/
    private String type;
    private String location;
    private Integer oaId;

    @Id
    @Column(name = "dtlID", nullable = false)
    public int getDtlId() {
        return dtlId;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

   /* @Basic
    @Column(name = "locationx",nullable = false)
    public Geometry getLocationx() {
        return locationx;
    }

    public void setLocationx(Geometry locationx) {
        this.locationx = locationx;
    }
*/
    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "objID", nullable = true)
    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AqObjdetailEntity that = (AqObjdetailEntity) o;

        if (dtlId != that.dtlId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (objId != null ? !objId.equals(that.objId) : that.objId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dtlId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (objId != null ? objId.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "location", nullable = true)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "oaID", nullable = true)
    public Integer getOaId() {
        return oaId;
    }

    public void setOaId(Integer oaId) {
        this.oaId = oaId;
    }
}
