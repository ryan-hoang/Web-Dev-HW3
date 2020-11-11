package HW3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/survey-servlet")
public class SurveyServlet extends HttpServlet
{
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    /*
    * get request from survey page
    * store form data from the survey to oracle db using StudentDAO.java
    * calculate mean and standard deviation using DataProcessor.java on the data from the data field.
    * Store the resulting DataBean into the session object.
    * If the mean is >=90 forwatd the request to WinnerAcknowledgement.jsp
    * else forward the request to SimpleAcknowledgement.jsp
    */
    String type = request.getParameter("type");
    StudentDAO dao = new StudentDAO();
    if(type != null) {
      StudentBean sb = new StudentBean();

      String studentid = request.getParameter("studentID");
      sb.setStudentID(studentid);

      String username = request.getParameter("username");
      sb.setUsername(username);

      String name = request.getParameter("name");
      sb.setName(name);

      String street = request.getParameter("street_address");
      sb.setStreet(street);

      String city = request.getParameter("city");
      sb.setCity(city);

      String state = request.getParameter("state");
      sb.setState(state);

      String zipcode = request.getParameter("zipcode");
      sb.setZipcode(zipcode);

      String telephone = request.getParameter("telephone");
      sb.setPhone(telephone);

      String email = request.getParameter("email");
      sb.setEmail(email);

      String url = request.getParameter("url");
      sb.setUrl(url);

      String date = request.getParameter("date");
      sb.setDate(date);

      boolean studentCheck = false;
      boolean locationCheck = false;
      boolean campusCheck = false;
      boolean atmosphereCheck = false;
      boolean dormsCheck = false;
      boolean sportsCheck = false;

      String[] checkboxes = request.getParameterValues("checkboxes");
      for (String n : checkboxes) {
        if (n.equals("students")) {
          studentCheck = true;
        } else if (n.equals("location")) {
          locationCheck = true;
        } else if (n.equals("campus")) {
          campusCheck = true;
        } else if (n.equals("atmosphere")) {
          atmosphereCheck = true;
        } else if (n.equals("dorms")) {
          dormsCheck = true;
        } else if (n.equals("sports")) {
          sportsCheck = true;
        }
      }

      sb.setStudentCheck(studentCheck);
      sb.setLocationCheck(locationCheck);
      sb.setCampusCheck(campusCheck);
      sb.setAtmosphereCheck(atmosphereCheck);
      sb.setDormCheck(dormsCheck);
      sb.setSportCheck(sportsCheck);

      boolean friendRadio = false;
      boolean televisionRadio = false;
      boolean internetRadio = false;
      boolean otherRadio = false;

      String radio = request.getParameter("radio");

      if (radio.equals("friends")) {
        friendRadio = true;
      } else if (radio.equals("television")) {
        televisionRadio = true;
      } else if (radio.equals("internet")) {
        internetRadio = true;
      } else if (radio.equals("other")) {
        otherRadio = true;
      }

      sb.setFriendRadio(friendRadio);
      sb.setTvRadio(televisionRadio);
      sb.setInternetRadio(internetRadio);
      sb.setOtherRadio(otherRadio);

      String gradMonth = request.getParameter("gradMonth");
      sb.setGradMonth(gradMonth);

      String gradYear = request.getParameter("grad_year");
      sb.setGradYear(gradYear);

      String howLikely = request.getParameter("recommend");
      sb.setHowLikely(howLikely);

      String comments = request.getParameter("comments");
      sb.setComments(comments);

      dao.save(sb);

      String[] nums = request.getParameter("data").split(",");
      //print(response,nums.toString());
      ArrayList<Integer> data = new ArrayList<Integer>();
      for (String s : nums) {
        data.add(Integer.parseInt(s));
      }

      double mean = DataProcessor.mean(data);
      double standardDeviation = DataProcessor.standardDeviation(data);

      DataBean db = new DataBean();
      db.setMean(mean);
      db.setStandardDeviation(standardDeviation);

      HttpSession session = request.getSession();
      session.setAttribute("dataBean", db); //Save the data bean to the session object

      String address;
      if (mean >= 90) {
        address = "WinnerAcknowledgement.jsp";
      } else {
        address = "SimpleAcknowledgement.jsp";
      }

      RequestDispatcher dispatcher = request.getRequestDispatcher(address);
      dispatcher.forward(request, response);
    }
    else
    {
      String id = request.getParameter("id");
      StudentBean sb = dao.pull(id);
      String address = "";

      if(sb == null)
      {
        address = "NoSuchStudent.jsp";
      }
      else
      {
        address = "Student.jsp";
      }

      HttpSession session = request.getSession();
      session.setAttribute("studentBean",sb);
      RequestDispatcher dispatcher = request.getRequestDispatcher(address);
      dispatcher.forward(request, response);
    }


  }

  private void print(HttpServletResponse response,String text) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>\n" +"<body>" + text + "</body>" + "</html>");
  }
}