package cn.heyuantao.main.event;


/**对数据库增删改查的所触发的事件
 * @author he_yu
 */

public enum CrudAction {

    LIST("LIST",1),
    CREATE("CREATE",2),
    GET("GET",3),
    PUT("PUT",4),
    DELETE("DELETE",5);

    private String value;
    private Integer index;

    CrudAction(String action, Integer index) {
        this.value = action;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
