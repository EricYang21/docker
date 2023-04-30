import "../App.css";

function Contact_us() {
  return <div className="Contact_us">
    <div className="main content">
      <h2 className="center">
        Contacts:
      </h2>
      <div className="fr">
        <div className="blank support-item">
          <img height={120} src="https://i.postimg.cc/KzPG96xz/email.png" alt="" />
          <h3>Email</h3>
          <a href="mailto: coordinator@bristolenergynetwork.org">coordinator@bristolenergynetwork.org</a>
        </div>
        <div className="blank support-item">
          <img height={120} src="https://i.postimg.cc/9rsmSVD4/telephone-call.png" alt="" />
          <h3>Telephone</h3>
          <a href="tel: 07871 791 679">07871 791 679</a>
          <div></div>
        </div>
      </div>
      <h2 className="center">
        Follow BNE on Social Media:
      </h2>
      <div className="fr">
        <div className="blank support-item">
          <p>
            <a href="http://www.twitter.com/BristolEnergyNw">
              <img height={120} src="https://i.postimg.cc/YhdgmvD5/twitter.png" alt="" />
            </a>
          </p>
        </div>
        <div className="blank support-item">
          <p>
            <a href="http://www.facebook.com/BristolEnergyNetwork">
              <img height={120} src="https://i.postimg.cc/7bMSws3Y/facebook.png" alt="" />
            </a>
          </p>
        </div>
      </div>
    </div>
  </div>; 
}

export default Contact_us;
