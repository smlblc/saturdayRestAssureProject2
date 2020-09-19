import java.util.List;

public class PojoDoc {

    private String name;
    private String description;
    private String schoolId;
    private boolean active;
    private boolean required;
    private List<String> attachmentStages;

    public List<String> getAttachmentStages() {
        return attachmentStages;
    }

    public void setAttachmentStages(List<String> attachmentStages) {
        this.attachmentStages = attachmentStages;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
