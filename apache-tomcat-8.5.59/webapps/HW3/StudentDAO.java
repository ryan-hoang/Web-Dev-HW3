package HW3;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";

    //  Database credentials
    static final String USER = "RHOANG";
    static final String PASS = "greejish";

    private Connection db;

    public StudentDAO() {

        db = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            db = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

  public void save(StudentBean student)
  {
      try {
          String sql = "INSERT IN TO SURVEYS (STUDENTID, USERNAME, NAME, STREET, CITY, STATE, ZIPCODE, PHONE, EMAIL, URL, DATE, LOCATIONCHECK, CAMPUSCHECK, ATMOSPHERECHECK, DORMCHECK, SPORTCHECK, STUDENTCHECK, FRIENDRADIO, TVRADIO, INTERNETRADIO, OTHERRADIO, GRADMONTH, GRADYEAR, HOWLIKELY, COMMENTS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          PreparedStatement st = db.prepareStatement(sql);
          st.setString(1,student.getStudentID());
          st.setString(2,student.getUsername());
          st.setString(3,student.getName());
          st.setString(4,student.getStreet());
          st.setString(5,student.getCity());
          st.setString(6,student.getState());
          st.setString(7,student.getZipcode());
          st.setString(8,student.getPhone());
          st.setString(9,student.getEmail());
          st.setString(10,student.getUrl());
          st.setString(11,student.getDate());
          st.setString(12,student.getLocationCheck() ? "yes" : "no");
          st.setString(13,student.getCampusCheck() ? "yes" : "no");
          st.setString(14,student.getAtmosphereCheck() ? "yes" : "no");
          st.setString(15,student.getDormCheck() ? "yes" : "no");
          st.setString(16,student.getSportCheck() ? "yes" : "no");
          st.setString(17,student.getStudentCheck() ? "yes" : "no");
          st.setString(18,student.getFriendRadio() ? "yes" : "no");
          st.setString(19,student.getTvRadio() ? "yes" : "no");
          st.setString(20,student.getInternetRadio() ? "yes" : "no");
          st.setString(21,student.getOtherRadio() ? "yes" : "no");
          st.setString(22,student.getGradMonth());
          st.setString(23,student.getGradYear());
          st.setString(24,student.getHowLikely());
          st.setString(25,student.getComments());

          st.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  
  public StudentBean pull(String StudentID)
  {
      StudentBean sb = new StudentBean();
      try
      {
        PreparedStatement ps = db.prepareStatement("SELECT * FROM SURVEYS WHERE STUDENTID = " + StudentID);
        ResultSet rs = ps.executeQuery();
        String studentID = "";
        String username = "";
        String name = "";
        String street = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String phone = "";
        String email = "";
        String url = "";
        String date = "";
        String locationCheck = "";
        String campusCheck = "";
        String atmosphereCheck = "";
        String dormCheck = "";
        String sportCheck = "";
        String studentCheck = "";
        String  friendRadio = "";
        String tvRadio = "";
        String internetRadio = "";
        String otherRadio = "";
        String gradMonth = "";
        String gradYear = "";
        String howLikely = "";
        String comments = "";

          while(rs.next())
          {
            studentID = rs.getString(1);
            username = rs.getString(2);
            name = rs.getString(3);
            street = rs.getString(4);
            city = rs.getString(5);
            state = rs.getString(6);
            zipcode = rs.getString(7);
            phone = rs.getString(8);
            email = rs.getString(9);
            url = rs.getString(10);
            date = rs.getString(11);
            locationCheck = rs.getString(12);
            campusCheck = rs.getString(13);
            atmosphereCheck = rs.getString(14);
            dormCheck = rs.getString(15);
            sportCheck = rs.getString(16);
            studentCheck = rs.getString(17);
            friendRadio = rs.getString(18);
            tvRadio = rs.getString(19);
            internetRadio = rs.getString(20);
            otherRadio = rs.getString(21);
            gradMonth = rs.getString(22);
            gradYear = rs.getString(23);
            howLikely = rs.getString(24);
            comments = rs.getString(25);
          }
          sb.setStudentID(studentID);
          sb.setUsername(username);
          sb.setName(name);
          sb.setStreet(street);
          sb.setCity(city);
          sb.setState(state);
          sb.setZipcode(zipcode);
          sb.setPhone(phone);
          sb.setEmail(email);
          sb.setUrl(url);
          sb.setDate(date);
          sb.setLocationCheck(locationCheck.equals("yes") ? true : false);
          sb.setCampusCheck(campusCheck.equals("yes") ? true : false);
          sb.setAtmosphereCheck(atmosphereCheck.equals("yes") ? true : false);
          sb.setDormCheck(dormCheck.equals("yes") ? true : false);
          sb.setSportCheck(sportCheck.equals("yes") ? true : false);
          sb.setStudentCheck(studentCheck.equals("yes") ? true : false);
          sb.setFriendRadio(friendRadio.equals("yes") ? true : false);
          sb.setTvRadio(tvRadio.equals("yes") ? true : false);
          sb.setInternetRadio(internetRadio.equals("yes") ? true : false);
          sb.setOtherRadio(otherRadio.equals("yes") ? true : false);
          sb.setGradMonth(gradMonth);
          sb.setGradYear(gradYear);
          sb.setHowLikely(howLikely);
          sb.setComments(comments);
      }
      catch (SQLException e) {
          e.printStackTrace();
      }
      return sb;
  }


    public ArrayList<StudentBean> pullAllBeans()
    {
        ArrayList<StudentBean> beans = new ArrayList<StudentBean>();
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT * FROM SURVEYS");
            ResultSet rs = ps.executeQuery();
            String studentID = "";
            String username = "";
            String name = "";
            String street = "";
            String city = "";
            String state = "";
            String zipcode = "";
            String phone = "";
            String email = "";
            String url = "";
            String date = "";
            boolean locationCheck = false;
            boolean campusCheck = false;
            boolean atmosphereCheck = false;
            boolean dormCheck = false;
            boolean sportCheck = false;
            boolean studentCheck = false;
            boolean friendRadio = false;
            boolean tvRadio = false;
            boolean internetRadio = false;
            boolean otherRadio = false;
            String gradMonth = "";
            String gradYear = "";
            String howLikely = "";
            String comments = "";

            while(rs.next()) {
                StudentBean sb = new StudentBean();
                studentID = rs.getString(1);
                username = rs.getString(2);
                name = rs.getString(3);
                street = rs.getString(4);
                city = rs.getString(5);
                state = rs.getString(6);
                zipcode = rs.getString(7);
                phone = rs.getString(8);
                email = rs.getString(9);
                url = rs.getString(10);
                date = rs.getString(11);
                locationCheck = rs.getBoolean(12);
                campusCheck = rs.getBoolean(13);
                atmosphereCheck = rs.getBoolean(14);
                dormCheck = rs.getBoolean(15);
                sportCheck = rs.getBoolean(16);
                studentCheck = rs.getBoolean(17);
                friendRadio = rs.getBoolean(18);
                tvRadio = rs.getBoolean(19);
                internetRadio = rs.getBoolean(20);
                otherRadio = rs.getBoolean(21);
                gradMonth = rs.getString(22);
                gradYear = rs.getString(23);
                howLikely = rs.getString(24);
                comments = rs.getString(25);

                sb.setStudentID(studentID);
                sb.setUsername(username);
                sb.setName(name);
                sb.setStreet(street);
                sb.setCity(city);
                sb.setState(state);
                sb.setZipcode(zipcode);
                sb.setPhone(phone);
                sb.setEmail(email);
                sb.setUrl(url);
                sb.setDate(date);
                sb.setLocationCheck(locationCheck);
                sb.setCampusCheck(campusCheck);
                sb.setAtmosphereCheck(atmosphereCheck);
                sb.setDormCheck(dormCheck);
                sb.setSportCheck(sportCheck);
                sb.setStudentCheck(studentCheck);
                sb.setFriendRadio(friendRadio);
                sb.setTvRadio(tvRadio);
                sb.setInternetRadio(internetRadio);
                sb.setOtherRadio(otherRadio);
                sb.setGradMonth(gradMonth);
                sb.setGradYear(gradYear);
                sb.setHowLikely(howLikely);
                sb.setComments(comments);

                beans.add(sb);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
  
}