package cn.edu.njfu.simple.sql.metadata.model;

public class CrumbMetaObject<E extends MetaObject> {
    
    private E metaObject;
    private String path;
    
    public CrumbMetaObject() { }
    
    public CrumbMetaObject(E metaObject, String path) {
        this.metaObject = metaObject;
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public E getMetaObject() {
        return metaObject;
    }
    public void setMetaObject(E metaObject) {
        this.metaObject = metaObject;
    }
}
