package classrecord.utility;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import classrecord.dto.StudentDTO;
import classrecord.model.Student;
import classrecord.service.StudentService;


public class Validator {
    
    private List<String> errorList = new ArrayList<String>();       
    
    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    // returns true if student is above or equal to 18
    public static boolean isAgeValid (Date birthDate){        
        
        Calendar dob = Calendar.getInstance();  
        dob.setTime(birthDate);  
        Calendar today = Calendar.getInstance();  
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
          age--;  
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
            && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
          age--;  
        }
        if (age >= 18)
            return true;
        return false;           
    }
  

}
