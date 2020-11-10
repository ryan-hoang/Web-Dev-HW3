<!doctype html> 
<head><title>StudentJSP</title></head>
<body>
  <h5>StudentID: {studentBean.studentID}</h5>
  <h5>Username: {studentBean.username}</h5>
  <h5>Name: {studentBean.name}</h5>
  <h5>Street Address: {studentBean.street}</h5>
  <h5>City: {studentBean.city}</h5>
  <h5>State: {studentBean.state}</h5>
  <h5>Zipcode: {studentBean.zipcode}</h5>
  <h5>Phone: {studentBean.phone}</h5>
  <h5>Email: {studentBean.email}</h5>
  <h5>URL: {studentBean.url}</h5>
  <h5>Date: {studentBean.date}</h5>
  
  <h5>What did you like most about the campus? </h5>
  Students: {studentBean.studentCheck}<br>
  Location: {studentBean.locationCheck}<br>
  Campus: {studentBean.campusCheck}<br>
  Atmosphere: {studentBean.atmosphereCheck}<br>
  Dorms: {studentBean.dormCheck}<br>
  Sports: {studentBean.sportCheck}<br>

  
  <h5>How did you become interested in the university? </h5>
  Friends: {studentBean.friendRadio}<br>
  Television: {studentBean.tvRadio}<br>
  Internet: {studentBean.internetRadio}<br>
  Other: {studentBean.otherRadio}<br>
  
  <h5>Graduation month: {studentBean.gradMonth}</h5>
  <h5>Graduation year: {studentBean.gradYear}</h5>
  <h5>Comments: {studentBean.comments}</h5>
  
</body>
</html>