import "../App.css";

function Privacy() {
  return <div className="Privacy">
    <div className="page-header">
      <h1>Privacy Policy</h1>
    </div>
    <p>At a base level, the functionality of the website does not log or keep any information of the user. </p>
    <p>However to facilitate our login functionality, it is necessary to keep user information such as:</p>
    <ul>
      <li>
        Emails
      </li>
      <li>
        Usernames
      </li>
      <li>
        Passwords
      </li>
    </ul>
    <p>In order to maintain accounts for our users.</p>
    <p>And our account system also keeps user information such as:</p>
    <ul>
      <li>
        comments on contractors
      </li>
      <li>
        (Optional) personal information including:
        <ul>
          <li>Gender</li>
          <li>Date Of Birth</li>
        </ul>
      </li>
    </ul>
    <p>We are strongly committed to protecting our user data and will do everything in our power to minimalise the chance of loss or misuse of said data.</p>
    <a href="https://www.gov.uk/data-protection">Data protection in the UK</a>
  </div>; 
}

export default Privacy;