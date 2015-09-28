package classrecord.dto;

public class StudentDTO {
    private String key;
    private String firstName;
    private String lastName;
    private String course;
    private String birthDate;
    
    
    /**
     * @param id
     * @param firstName
     * @param lastName
     * @param course
     * @param birthDate
     */
    public StudentDTO(String key, String firstName, String lastName, String course, String birthDate) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.birthDate = birthDate;
    }
    
    
    public StudentDTO() {
        // TODO Auto-generated constructor stub
    }


    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }
    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }
    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }
    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }


    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
}
