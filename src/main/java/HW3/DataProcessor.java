package HW3;

import java.util.ArrayList;

public class DataProcessor
{
  public DataProcessor() {}
  
  public double mean(ArrayList<Integer> data)
  {
    double mean = 0.0;
    for(int i : data)
    {
      mean += i;
    }
     return mean/data.size();
  }
  
  public double standardDeviation(ArrayList<Integer> data)
  {
      double mean = mean(data);
      double temp = 0.0;
      for(int num : data)
      {
        temp += Math.pow(num - mean,2);
      }

      return Math.sqrt(temp/data.size());
  }
}