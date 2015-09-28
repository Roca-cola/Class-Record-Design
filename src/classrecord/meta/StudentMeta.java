package classrecord.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-09-28 09:07:54")
/** */
public final class StudentMeta extends org.slim3.datastore.ModelMeta<classrecord.model.Student> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.Student, java.util.Date> birthDate = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.Student, java.util.Date>(this, "birthDate", "birthDate", java.util.Date.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<classrecord.model.Student> course = new org.slim3.datastore.StringAttributeMeta<classrecord.model.Student>(this, "course", "course");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<classrecord.model.Student> firstName = new org.slim3.datastore.StringAttributeMeta<classrecord.model.Student>(this, "firstName", "firstName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.Student, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.Student, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<classrecord.model.Student> lastName = new org.slim3.datastore.StringAttributeMeta<classrecord.model.Student>(this, "lastName", "lastName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.Student, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.Student, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final StudentMeta slim3_singleton = new StudentMeta();

    /**
     * @return the singleton
     */
    public static StudentMeta get() {
       return slim3_singleton;
    }

    /** */
    public StudentMeta() {
        super("Student", classrecord.model.Student.class);
    }

    @Override
    public classrecord.model.Student entityToModel(com.google.appengine.api.datastore.Entity entity) {
        classrecord.model.Student model = new classrecord.model.Student();
        model.setBirthDate((java.util.Date) entity.getProperty("birthDate"));
        model.setCourse((java.lang.String) entity.getProperty("course"));
        model.setFirstName((java.lang.String) entity.getProperty("firstName"));
        model.setKey(entity.getKey());
        model.setLastName((java.lang.String) entity.getProperty("lastName"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        classrecord.model.Student m = (classrecord.model.Student) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("birthDate", m.getBirthDate());
        entity.setProperty("course", m.getCourse());
        entity.setProperty("firstName", m.getFirstName());
        entity.setProperty("lastName", m.getLastName());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        classrecord.model.Student m = (classrecord.model.Student) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        classrecord.model.Student m = (classrecord.model.Student) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        classrecord.model.Student m = (classrecord.model.Student) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        classrecord.model.Student m = (classrecord.model.Student) model;
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
        classrecord.model.Student m = (classrecord.model.Student) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getBirthDate() != null){
            writer.setNextPropertyName("birthDate");
            encoder0.encode(writer, m.getBirthDate());
        }
        if(m.getCourse() != null){
            writer.setNextPropertyName("course");
            encoder0.encode(writer, m.getCourse());
        }
        if(m.getFirstName() != null){
            writer.setNextPropertyName("firstName");
            encoder0.encode(writer, m.getFirstName());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getLastName() != null){
            writer.setNextPropertyName("lastName");
            encoder0.encode(writer, m.getLastName());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected classrecord.model.Student jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        classrecord.model.Student m = new classrecord.model.Student();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("birthDate");
        m.setBirthDate(decoder0.decode(reader, m.getBirthDate()));
        reader = rootReader.newObjectReader("course");
        m.setCourse(decoder0.decode(reader, m.getCourse()));
        reader = rootReader.newObjectReader("firstName");
        m.setFirstName(decoder0.decode(reader, m.getFirstName()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("lastName");
        m.setLastName(decoder0.decode(reader, m.getLastName()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}