package io.simi.widget;

/**
 * @author chimis@foxmail.com (CHIMIS 葛相池)
 */

public class ViewType {

    private Type type;
    private int position;
    private int layoutResId;

    public ViewType(Type type, int position, int layoutResId) {
        this.type = type;
        this.position = position;
        this.layoutResId = layoutResId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    public enum Type {
        DEFAULT, SINGLE, LOOP;
    }
}
