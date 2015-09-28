package classrecord.model;

import java.io.Serializable;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model(schemaVersion = 1)
public class ClassRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;

    @Attribute(version = true)
    private Long version;
    
    
    private ModelRef<Class> classReference = new ModelRef<Class>(Class.class);
    private ModelRef<Student> studentReference = new ModelRef<Student>(Student.class);
    
    private double quizOne;
    private double quizTwo;
    private double quizThree;
    private double assignmentOne;
    private double assignmentTwo;
    private double examOne;
    private double examTwo;
    
    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * @return the studentReference
     */
    public ModelRef<Student> getStudentReference() {
        return studentReference;
    }

    /**
     * @return the classReference
     */
    public ModelRef<Class> getClassReference() {
        return classReference;
    }

    

    /**
     * @return the quizOne
     */
    public double getQuizOne() {
        return quizOne;
    }

    /**
     * @param quizOne the quizOne to set
     */
    public void setQuizOne(double quizOne) {
        this.quizOne = quizOne;
    }

    /**
     * @return the quizTwo
     */
    public double getQuizTwo() {
        return quizTwo;
    }

    /**
     * @param quizTwo the quizTwo to set
     */
    public void setQuizTwo(double quizTwo) {
        this.quizTwo = quizTwo;
    }

    /**
     * @return the quizThree
     */
    public double getQuizThree() {
        return quizThree;
    }

    /**
     * @param quizThree the quizThree to set
     */
    public void setQuizThree(double quizThree) {
        this.quizThree = quizThree;
    }

    /**
     * @return the assignmentOne
     */
    public double getAssignmentOne() {
        return assignmentOne;
    }

    /**
     * @param assignmentOne the assignmentOne to set
     */
    public void setAssignmentOne(double assignmentOne) {
        this.assignmentOne = assignmentOne;
    }

    /**
     * @return the assignmentTwo
     */
    public double getAssignmentTwo() {
        return assignmentTwo;
    }

    /**
     * @param assignmentTwo the assignmentTwo to set
     */
    public void setAssignmentTwo(double assignmentTwo) {
        this.assignmentTwo = assignmentTwo;
    }

    /**
     * @return the examOne
     */
    public double getExamOne() {
        return examOne;
    }

    /**
     * @param examOne the examOne to set
     */
    public void setExamOne(double examOne) {
        this.examOne = examOne;
    }

    /**
     * @return the examTwo
     */
    public double getExamTwo() {
        return examTwo;
    }

    /**
     * @param examTwo the examTwo to set
     */
    public void setExamTwo(double examTwo) {
        this.examTwo = examTwo;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ClassRecord other = (ClassRecord) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
