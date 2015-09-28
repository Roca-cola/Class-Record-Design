package classrecord.dto;

import java.text.DecimalFormat;

public class RecordsDTO {
    private String key;
    private StudentDTO student;
    private String classKey;
    
    private double quizOne;
    private double quizTwo;
    private double quizThree;
    private double assignmentOne;
    private double assignmentTwo;
    private double examOne;
    private double examTwo;
    
    private double grade;
    private String status;
    
    
    /**
     * @param grade - grade to set
     */
    public void setGrade (double grade){
        this.grade = grade;
    }
    
    /**
     * @return grade
     */
    public double getGrade(){
        return this.grade;
    }
    
    /**
     * @param Status
     */
    public void setStatus (String Status){
        this.status = status;
    }
    /**
     * @return status
     */
    public String getStatus() {
        return this.status;
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

    /**
     * @return the studentDTO
     */
    public StudentDTO getStudent() {
        return student;
    }
    /**
     * @param studentDTO the studentDTO to set
     */
    public void setStudent(StudentDTO studentDTO) {
        this.student = studentDTO;
    }
    /**
     * @return the classKey
     */
    public String getClassKey() {
        return classKey;
    }
    /**
     * @param classKey the classKey to set
     */
    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }
    
    public void calculateGrade () {
        double sum = this.getAssignmentOne() + this.getAssignmentTwo() + this.getExamOne() 
                + this.getExamTwo() + this.getQuizOne() + this.getQuizTwo() + this.getQuizThree();
        double quotient = sum / 700;
        DecimalFormat df2 = new DecimalFormat("###.#");
        if (quotient >= .5){
            double difference = quotient - .500;
            difference *= 100.0;
            double point  = difference / 25;
            this.grade = 3.0 + point;   
            String value = df2.format(this.grade);
            this.grade = Double.parseDouble(value);
            this.status = "PASSED";
        } else {
            double difference = 0.500 - quotient;
            difference *= 100.0;
            double point  = difference / 25;
            this.grade = 3.0 - point;
            String value = df2.format(this.grade);
            this.grade = Double.parseDouble(value);
            if (this.grade < 3.0)
                this.status = "FAILED";
            else
                this.status = "PASSED";
        }
    }    
}
