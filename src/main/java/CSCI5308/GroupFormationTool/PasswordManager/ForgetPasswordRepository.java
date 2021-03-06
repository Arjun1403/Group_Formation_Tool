package CSCI5308.GroupFormationTool.PasswordManager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Database.ConnectionManager;

public class ForgetPasswordRepository implements IForgetPasswordRepository {
    @Override
    public String getEmailByBannerID(String bannerid) {
        String email = new String();
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetEmailByBannerID(?)}");
            st.setString(1, bannerid);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
                email = result.getString(1);
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    @Override
    public boolean updatePassword(String bannerid, String newPassword) throws Exception {
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL updatePassword(?,?)}");
            st.setString(1, bannerid);
            st.setString(2,newPassword);
            ResultSet result = st.executeQuery();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getPasswordPolicyNumber() {
        int passNumber=0;
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetPasswordPolicyNumber()}");
            ResultSet result = st.executeQuery();
            result.next();
            passNumber =result.getInt(1);
            st.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return passNumber;
    }

    @Override
    public List<String> getPasswordByBannerID(String bannerid, int passNumber) throws Exception {
        List<String> passwords = new ArrayList<String>();
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL GetPasswordByBannerID(?,?)}");
            st.setString(1,bannerid);
            st.setString(2, String.valueOf(passNumber));
            ResultSet result = st.executeQuery();
            while (result.next())
            {
                passwords.add(result.getString(1));
            }
            st.close();
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return passwords;
    }

    @Override
    public boolean insertToForgetPassword(String bannerid, String passKey) {
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL InsertPasswordKey(?,?)}");
            st.setString(1, bannerid);
            st.setString(2,passKey);
            ResultSet result = st.executeQuery();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String getBannerIDByPassKey(String passKey) throws Exception {
        String bannerID = "";
        try {
            Connection connection = ConnectionManager.instance().getDBConnection();
            CallableStatement st = connection.prepareCall("{CALL BannerIdByKey(?)}");
            st.setString(1, passKey);
            ResultSet result = st.executeQuery();
            if(result.next())
            {
                bannerID = result.getString(1);
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return  bannerID;
    }

}
