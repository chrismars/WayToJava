package app.sys.uplaodPic.action.bean;

public class posAq_attachmentEntity {
    private String name;
    private String file_name;
    private Integer type;

    public posAq_attachmentEntity(){};

    public posAq_attachmentEntity(String name, String file_name, Integer type){
        this.name=name;
        this.file_name=file_name;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
