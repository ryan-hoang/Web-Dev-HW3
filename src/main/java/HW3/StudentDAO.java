package HW3;
import java.sql.*;

public class StudentDAO {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";

    //  Database credentials
    static final String USER = "RHOANG";
    static final String PASS = "greejish";

    private Connection db;

    public StudentDAO() {

        db = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

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
          st.setBoolean(12,student.getLocationCheck());
          st.setBoolean(13,student.getCampusCheck());
          st.setBoolean(14,student.getAtmosphereCheck());
          st.setBoolean(15,student.getDormCheck());
          st.setBoolean(16,student.getSportCheck());
          st.setBoolean(17,student.getStudentCheck());
          st.setBoolean(18,student.getFriendRadio());
          st.setBoolean(19,student.getTvRadio());
          st.setBoolean(20,student.getInternetRadio());
          st.setBoolean(21,student.getOtherRadio());
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
      }
      catch (SQLException e) {
          e.printStackTrace();
      }
      return sb;
  }
  
}