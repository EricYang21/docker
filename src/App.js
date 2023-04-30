import "./App.css";
import { Routes, Route, useLocation } from "react-router-dom";
import {Link} from "react-router-dom";
import Home from "./pages/Home";
import List from "./pages/List";
import Login from "./pages/Login";
import SignUp from"./pages/Signup";
import Contact_us from "./pages/Contact_us";
import Privacy from "./pages/Privacy";
import Information from "./pages/Information";
import Comment from "./pages/Comment";
import Profile from "./pages/Profile";
import { useEffect } from "react";
import axios from "axios";
import login from "./assets/login.svg";

function App() {
  const location=useLocation();
  useEffect(()=>{
    console.log(location.pathname);
    const userId=sessionStorage.getItem("userId");
    if(userId){
      axios.post("/browserHistory",{userId:Number(userId),link:location.pathname});
    }
  },[location.pathname]);
  return (
    <div>
      <div className='App'>
        <header className="header fr content">
          <a href="https://bristolenergynetwork.org">
            <img src="https://i.postimg.cc/KzWjzhnQ/BEN-logo-art-L-web.jpg" alt="Bristol Energy Network" height={60} />
          </a>
          <div className='fr sign-btn'>
            <span>User Center&nbsp;</span>
            <Link to="/login">
              <img src={login} alt="login" className="login-icon"/>
            </Link>
            <Link to="/signup">
              <button className="header-btn sign-up">Sign up</button>
            </Link>
            <Link to="/login">
              <button className="header-btn sign-in">Sign in</button>
            </Link>
          </div>
        </header>
      </div>
      <div className="menu">
        <div className="fr content">
          <Link to="/">
            <span className="menu_item">
        Home
            </span>
          </Link>
          <Link to="/information">
            <span className="menu_item">
        About us
            </span>
          </Link>
          <Link to="/contact_us">
            <span className="menu_item">
        Contact us
            </span>
          </Link>
          <Link to="/privacy">
            <span className="menu_item">
        Privacy
            </span>
          </Link>
          <div className="blank"></div>
      
        </div>
      </div>
     
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/list" element={<List />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/login" element={<Login />}/>
        <Route path="/contact_us" element={<Contact_us />}/>
        <Route path="/privacy" element={<Privacy />}/>
        <Route path="/information" element={<Information />}/>
        <Route path="/comment/:id" element={<Comment />} />
        <Route path="/profile" element={<Profile />}/>
      </Routes>
      <div className='copyright'>
        <div className="content">
          <div className="fr">
            <img src="https://i.postimg.cc/KzWjzhnQ/BEN-logo-art-L-web.jpg" alt="" width={200} />
            <div className="fr blank">
              <div className="blank"></div>
              <div className="Contact Us" style={{padding:"0 12px"}}>
                <Link to="/contact_us">
                  <button className="header-btn sign-up">Contact us</button>
                </Link>
              </div>
              <div className="Privacy" style={{padding:"0 12px"}}>
                <Link to="/privacy">
                  <button className="header-btn sign-up">Privacy</button>
                </Link>
              </div>
              <div className="More Information" style={{padding:"0 12px"}}>
                <Link to="/more_information">
                  <button className="header-btn sign-up">More Information</button>
                </Link>
              </div>
            </div>
          </div>
          <br />
          <br />
          <input placeholder="search..."/><button>Search</button>
        </div>

      </div>
    </div>
  );
}

export default App;
