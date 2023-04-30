import { message } from "antd";
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router";

function SignUp() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [secondPassword, setSecondPassword] = useState("");

  const handleSignUp = (event) => {
    event.preventDefault();
    if (password !== secondPassword) {
      message.warning("Sorry, the password you entered doesn't match. Please try again.");
      return;
    }
    const signUpData = {username, email, password};
    axios.post("/user/signUp",signUpData).then(res=>{
      if(res.data.isOk){
        message.success("Sign up successfully!");
        sessionStorage.setItem("userId", res.data.userId);
        navigate("/profile");
      }else{
        message.error("there is an error in signUp request, please try again");
      }
    });
  };

  return (
    <div id="login">
      <h3>Energy Project</h3>
      <form onSubmit={handleSignUp}>
        <label htmlFor="username">Username:</label>
        <input type="text"
          id="username" name="username" value={username}
          onChange={ (event) => setUsername(event.target.value) }/>

        <label htmlFor="email">Email:</label>
        <input type="text" id="email" name="email" value={email}
          onChange={ (event) => setEmail(event.target.value) }/>

        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" value={password}
          onChange={ (event) => setPassword(event.target.value) }/>

        <label htmlFor="secondPassword">Password Confirmation:</label>
        <input type="password" id="secondPassword" name="secondPassword" value={secondPassword}
          onChange={ (event) => setSecondPassword(event.target.value) } />

        <input type="submit" value="Sign up"/>
      </form>
    </div>
  );
}

export default SignUp;