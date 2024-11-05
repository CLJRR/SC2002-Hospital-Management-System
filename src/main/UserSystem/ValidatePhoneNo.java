package UserSystem;

public class ValidatePhoneNo {
    public boolean validatePhoneNo(String PhoneNo) {
        if (PhoneNo.charAt(0) == '8' || PhoneNo.charAt(0) == '9')
        {
            if (PhoneNo.length() == 8)
            {
                return true;
            }
            else
                return false;
        }  
        else
            return false;
    }
}
