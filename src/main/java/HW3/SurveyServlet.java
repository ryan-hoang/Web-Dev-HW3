package HW3;

import sun.misc.Request;

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



    String[] nums = request.getParameter("data").split(",");
    //print(response,nums.toString());
    ArrayList<Integer> data = new ArrayList<Integer>();
    for(String s : nums)
    {
      data.add(Integer.parseInt(s));
    }

    double mean = DataProcessor.mean(data);
    double standardDeviation = DataProcessor.standardDeviation(data);

    DataBean db = new DataBean();
    db.setMean(mean);
    db.setStDeviation(standardDeviation);

    HttpSession session = request.getSession();
    session.setAttribute("dataBean", db); //Save the data bean to the session object

    String address;
    if(mean >= 90)
    {
      address = "WinnerAcknowledgement.jsp";
    }
    else
    {
      address = "SimpleAcknowledgement.jsp";
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(address);
    dispatcher.forward(request,response);

  }

  private void print(HttpServletResponse response,String text) throws IOException {
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>\n" +"<body>" + text + "</body>" + "</html>");
  }
}