import React, { useState } from "react";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = (event) => {
    event.preventDefault();
    const loginData = {email, password};
    fetch("http://localhost:8080/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(loginData),
    })
      .then( (res) => {
        if(res.ok) {
          return res.json();
        } else {
          alert("There is an unexpected error. Please try again later.");
        }
      });
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