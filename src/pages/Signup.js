import React, { useState } from "react";

function SignUp() {

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [secondPassword, setSecondPassword] = useState("");

  const handleSignUp = (event) => {
    event.preventDefault();
    if (password !== secondPassword) {
      alert("Sorry, the password you entered doesn't match. Please try again.");
      return;
    }
    const signUpData = {username, email, password};
    fetch("http://localhost:8080/user/signUp", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(signUpData),
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