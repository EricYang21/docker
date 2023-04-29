import { message } from "antd";
import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router";

function Login() {
  const navigate = useNavigate()
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (event) => {
    event.preventDefault();
    const loginData = {email, password};
    axios.post('/user/login',loginData).then(res=>{
      if(res.data.isOk){
        message.success("login successfully!");
        sessionStorage.setItem("userId", res.data.userId);
        navigate("/profile");
      }else{
        message.error("there is an error in login request, maybe you put a wrong password, or maybe you didn't sign up, or maybe your email is wrong, please try again");
      }
    })
  };

  return (
    <div id="login">
      <h3>Energy Project</h3>
      <form onSubmit={handleLogin}>
        <label htmlFor="email">Email:</label>
        <input type="text" id="email" name="email" value={email}
          onChange={ (event) => setEmail(event.target.value)} />

        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" value={password}
          onChange={ (event) => setPassword(event.target.value) } />

        <input type="submit" value="Login"/>
      </form>
    </div>
  );
}

export default Login;