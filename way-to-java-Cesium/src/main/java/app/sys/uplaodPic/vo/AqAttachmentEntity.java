package app.sys.uplaodPic.vo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "aq_attachment", schema = "canlian", catalog = "")
public class AqAttachmentEntity {
    private int attId;
    private String name;
    private String url;
    private Integer type;
    private Integer tableId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attID", nullable = false)
    public int getAttId() {
        return attId;
    }

    public void setAttId(int attId) {
        this.attId = attId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 200)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "tableID", nullable = true)
    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AqAttachmentEntity that = (AqAttachmentEntity) o;

        if (attId != that.attId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tableId != null ? tableId.hashCode() : 0);
        return result;
    }
}
