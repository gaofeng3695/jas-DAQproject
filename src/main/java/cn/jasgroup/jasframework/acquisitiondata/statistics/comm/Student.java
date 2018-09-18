package cn.jasgroup.jasframework.acquisitiondata.statistics.comm;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/15 18:03
 */
public class Student {

    private Integer id;
    private String groupId;
    private String name;

    public Student(Integer id, String groupId, String name) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
