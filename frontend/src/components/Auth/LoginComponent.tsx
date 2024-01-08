import { FC, useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Login.css";
import { useDispatch } from "react-redux";
import { login, logout } from "../../redux/actions/authActions";

const LoginComponent: FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  useEffect(() => {
    dispatch(logout());
  }, []);

  const loginMethod = async () => {
    const username = (document.getElementById("username") as HTMLInputElement)
      .value;
    const password = (document.getElementById("password") as HTMLInputElement)
      .value;

    // Step 1: Login and obtain JWT token
    axios
      .post("http://localhost:8080/api/v1/auth/login", {
        username,
        password,
      })
      .then((response) => {
        const jwtToken = response.data.token;

        axios
          .get("http://localhost:8080/api/v1/korisnici/trenutnoUlogovani", {
            headers: {
              Authorization: `Bearer ${jwtToken}`,
            },
          })
          .then((userResponse) => {
            const currentUser = userResponse.data;
            dispatch(login(jwtToken, currentUser));
          })
          .catch((error) => {
            console.error("Error fetching currently logged in user:", error);
            alert(error.response.data.body.detail);
          });
        navigate("/pocetna");
      })
      .catch((error) => {
        alert(error.response.data.body.detail);
      });
  };

  return (
    <div className="login-form">
      <div className="form-group">
        <input
          required
          type="text"
          className="form-control"
          placeholder="User Name"
          id="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
      </div>
      <div className="form-group">
        <input
          required
          type="password"
          className="form-control"
          placeholder="Password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <div className="btn-container">
        <button
          type="button"
          className="btn-login"
          id="login"
          onClick={loginMethod}
        >
          Login
        </button>
      </div>
    </div>
  );
};

export default LoginComponent;
