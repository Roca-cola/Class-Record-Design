package classrecord.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2015-09-28 18:43:55")
/** */
public final class ClassRecordMeta extends org.slim3.datastore.ModelMeta<classrecord.model.ClassRecord> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> assignmentOne = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "assignmentOne", "assignmentOne", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> assignmentTwo = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "assignmentTwo", "assignmentTwo", double.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<classrecord.model.ClassRecord, org.slim3.datastore.ModelRef<classrecord.model.Class>, classrecord.model.Class> classReference = new org.slim3.datastore.ModelRefAttributeMeta<classrecord.model.ClassRecord, org.slim3.datastore.ModelRef<classrecord.model.Class>, classrecord.model.Class>(this, "classReference", "classReference", org.slim3.datastore.ModelRef.class, classrecord.model.Class.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> examOne = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "examOne", "examOne", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> examTwo = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "examTwo", "examTwo", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> quizOne = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "quizOne", "quizOne", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> quizThree = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "quizThree", "quizThree", double.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double> quizTwo = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Double>(this, "quizTwo", "quizTwo", double.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<classrecord.model.ClassRecord, org.slim3.datastore.ModelRef<classrecord.model.Student>, classrecord.model.Student> studentReference = new org.slim3.datastore.ModelRefAttributeMeta<classrecord.model.ClassRecord, org.slim3.datastore.ModelRef<classrecord.model.Student>, classrecord.model.Student>(this, "studentReference", "studentReference", org.slim3.datastore.ModelRef.class, classrecord.model.Student.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<classrecord.model.ClassRecord, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ClassRecordMeta slim3_singleton = new ClassRecordMeta();

    /**
     * @return the singleton
     */
    public static ClassRecordMeta get() {
       return slim3_singleton;
    }

    /** */
    public ClassRecordMeta() {
        super("ClassRecord", classrecord.model.ClassRecord.class);
    }

    @Override
    public classrecord.model.ClassRecord entityToModel(com.google.appengine.api.datastore.Entity entity) {
        classrecord.model.ClassRecord model = new classrecord.model.ClassRecord();
        model.setAssignmentOne(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("assignmentOne")));
        model.setAssignmentTwo(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("assignmentTwo")));
        if (model.getClassReference() == null) {
            throw new NullPointerException("The property(classReference) is null.");
        }
        model.getClassReference().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("classReference"));
        model.setExamOne(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("examOne")));
        model.setExamTwo(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("examTwo")));
        model.setKey(entity.getKey());
        model.setQuizOne(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("quizOne")));
        model.setQuizThree(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("quizThree")));
        model.setQuizTwo(doubleToPrimitiveDouble((java.lang.Double) entity.getProperty("quizTwo")));
        if (model.getStudentReference() == null) {
            throw new NullPointerException("The property(studentReference) is null.");
        }
        model.getStudentReference().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("studentReference"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("assignmentOne", m.getAssignmentOne());
        entity.setProperty("assignmentTwo", m.getAssignmentTwo());
        if (m.getClassReference() == null) {
            throw new NullPointerException("The property(classReference) must not be null.");
        }
        entity.setProperty("classReference", m.getClassReference().getKey());
        entity.setProperty("examOne", m.getExamOne());
        entity.setProperty("examTwo", m.getExamTwo());
        entity.setProperty("quizOne", m.getQuizOne());
        entity.setProperty("quizThree", m.getQuizThree());
        entity.setProperty("quizTwo", m.getQuizTwo());
        if (m.getStudentReference() == null) {
            throw new NullPointerException("The property(studentReference) must not be null.");
        }
        entity.setProperty("studentReference", m.getStudentReference().getKey());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
        if (m.getClassReference() == null) {
            throw new NullPointerException("The property(classReference) must not be null.");
        }
        m.getClassReference().assignKeyIfNecessary(ds);
        if (m.getStudentReference() == null) {
            throw new NullPointerException("The property(studentReference) must not be null.");
        }
        m.getStudentReference().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
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
        classrecord.model.ClassRecord m = (classrecord.model.ClassRecord) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        writer.setNextPropertyName("assignmentOne");
        encoder0.encode(writer, m.getAssignmentOne());
        writer.setNextPropertyName("assignmentTwo");
        encoder0.encode(writer, m.getAssignmentTwo());
        if(m.getClassReference() != null && m.getClassReference().getKey() != null){
            writer.setNextPropertyName("classReference");
            encoder0.encode(writer, m.getClassReference(), maxDepth, currentDepth);
        }
        writer.setNextPropertyName("examOne");
        encoder0.encode(writer, m.getExamOne());
        writer.setNextPropertyName("examTwo");
        encoder0.encode(writer, m.getExamTwo());
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        writer.setNextPropertyName("quizOne");
        encoder0.encode(writer, m.getQuizOne());
        writer.setNextPropertyName("quizThree");
        encoder0.encode(writer, m.getQuizThree());
        writer.setNextPropertyName("quizTwo");
        encoder0.encode(writer, m.getQuizTwo());
        if(m.getStudentReference() != null && m.getStudentReference().getKey() != null){
            writer.setNextPropertyName("studentReference");
            encoder0.encode(writer, m.getStudentReference(), maxDepth, currentDepth);
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected classrecord.model.ClassRecord jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        classrecord.model.ClassRecord m = new classrecord.model.ClassRecord();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("assignmentOne");
        m.setAssignmentOne(decoder0.decode(reader, m.getAssignmentOne()));
        reader = rootReader.newObjectReader("assignmentTwo");
        m.setAssignmentTwo(decoder0.decode(reader, m.getAssignmentTwo()));
        reader = rootReader.newObjectReader("classReference");
        decoder0.decode(reader, m.getClassReference(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("examOne");
        m.setExamOne(decoder0.decode(reader, m.getExamOne()));
        reader = rootReader.newObjectReader("examTwo");
        m.setExamTwo(decoder0.decode(reader, m.getExamTwo()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("quizOne");
        m.setQuizOne(decoder0.decode(reader, m.getQuizOne()));
        reader = rootReader.newObjectReader("quizThree");
        m.setQuizThree(decoder0.decode(reader, m.getQuizThree()));
        reader = rootReader.newObjectReader("quizTwo");
        m.setQuizTwo(decoder0.decode(reader, m.getQuizTwo()));
        reader = rootReader.newObjectReader("studentReference");
        decoder0.decode(reader, m.getStudentReference(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}