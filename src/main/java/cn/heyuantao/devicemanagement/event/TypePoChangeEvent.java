package cn.heyuantao.devicemanagement.event;

import cn.heyuantao.devicemanagement.domain.Type;
import org.springframework.context.ApplicationEvent;

/**
 * 当类型数据发生变动时触发
 * @author he_yu
 */


public class TypePoChangeEvent extends ApplicationEvent {
    private CrudAction action;
    private Type instance;

    public TypePoChangeEvent(Object source, Type instance, CrudAction action) {
        super(source);
        this.instance = instance;
        this.action = action;
    }


    public CrudAction getAction() {
        return action;
    }

    public void setAction(CrudAction action) {
        this.action = action;
    }

    public Type getInstance() {
        return instance;
    }

    public void setInstance(Type instance) {
        this.instance = instance;
    }
}
