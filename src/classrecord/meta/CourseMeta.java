package classrecord.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-09-28 18:43:55")
/** */
public final class CourseMeta extends org.slim3.datastore.ModelMeta<classrecord.model.Course> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.Course, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.Course, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<classrecord.model.Course> name = new org.slim3.datastore.StringAttributeMeta<classrecord.model.Course>(this, "name", "name");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.Course, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.Course, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final CourseMeta slim3_singleton = new CourseMeta();

    /**
     * @return the singleton
     */
    public static CourseMeta get() {
       return slim3_singleton;
    }

    /** */
    public CourseMeta() {
        super("Course", classrecord.model.Course.class);
    }

    @Override
    public classrecord.model.Course entityToModel(com.google.appengine.api.datastore.Entity entity) {
        classrecord.model.Course model = new classrecord.model.Course();
        model.setKey(entity.getKey());
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        classrecord.model.Course m = (classrecord.model.Course) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("name", m.getName());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        classrecord.model.Course m = (classrecord.model.Course) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        classrecord.model.Course m = (classrecord.model.Course) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        classrecord.model.Course m = (classrecord.model.Course) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        classrecord.model.Course m = (classrecord.model.Course) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        classrecord.model.Course m = (classrecord.model.Course) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected classrecord.model.Course jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        classrecord.model.Course m = new classrecord.model.Course();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}