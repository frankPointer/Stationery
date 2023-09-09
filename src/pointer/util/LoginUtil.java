package pointer.util;

import java.util.Map;

public class LoginUtil {
    public static boolean loginVerify(Integer id,String password) {
        Map<Integer, String> namePassword = EmpUtil.getNamePassword();
        if (namePassword.containsKey(id)) {
            String value = namePassword.get(id);
            return value.equals(password);
        } else {
            return false;
        }
    }

}
