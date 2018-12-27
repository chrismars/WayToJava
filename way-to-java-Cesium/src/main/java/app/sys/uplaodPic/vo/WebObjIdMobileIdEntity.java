package app.sys.uplaodPic.vo;

import javax.persistence.*;

@Entity
@Table(name = "webObjId_mobileId", schema = "canlian", catalog = "")
public class WebObjIdMobileIdEntity {

    private int webObjId;
    private String mobileObjId;

    @Id
    @Column(name = "webObjId", nullable = false)
    public int getWebObjId() {
        return webObjId;
    }

    public void setWebObjId(int webObjId) {
        this.webObjId = webObjId;
    }

    @Basic
    @Column(name = "mobileObjId", nullable = true, length = 50)
    public String getMobileObjId() {
        return mobileObjId;
    }

    public void setMobileObjId(String mobileObjId) {
        this.mobileObjId = mobileObjId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebObjIdMobileIdEntity that = (WebObjIdMobileIdEntity) o;

        if (webObjId != that.webObjId) return false;
        if (mobileObjId != null ? !mobileObjId.equals(that.mobileObjId) : that.mobileObjId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = webObjId;
        result = 31 * result + (mobileObjId != null ? mobileObjId.hashCode() : 0);
        return result;
    }
}
