package app.sys.uplaodPic.action.bean;

public class posAq_objDetailEntity {
    private Integer typID;
    private String name;
    public posAq_objDetailEntity(){}
    public posAq_objDetailEntity(Integer typID,String name){
        this.typID=typID;
        this.name=name;
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
}
