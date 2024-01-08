import { ChangeEvent, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Register.css";

interface RegisterRequest {
  ime: string;
  prezime: string;
  email: string;
  username: string;
  password: string;
}

const RegisterComponent = () => {
  const navigate = useNavigate();
  const [registerData, setRegisterData] = useState<RegisterRequest>({
    ime: "",
    prezime: "",
    email: "",
    username: "",
    password: "",
  });

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setRegisterData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const register = async () => {
    try {
      await axios.post(
        "http://localhost:8080/api/v1/auth/register",
        registerData
      );
      navigate("/login");
    } catch (error: any) {
      alert(error.response.data.body.detail);
    }
  };

  return (
    <div className="register-form">
      <div className="form-group">
        <input
          required
          type="text"
          className="form-control"
          placeholder="Username"
          name="username"
          value={registerData.username}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          required
          type="password"
          className="form-control"
          placeholder="Password"
          name="password"
          value={registerData.password}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          required
          type="text"
          className="form-control"
          placeholder="Email"
          name="email"
          value={registerData.email}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          required
          type="text"
          className="form-control"
          placeholder="First Name"
          name="ime"
          value={registerData.ime}
          onChange={handleInputChange}
        />
      </div>
      <div className="form-group">
        <input
          required
          type="text"
          className="form-control"
          placeholder="Last Name"
          name="prezime"
          value={registerData.prezime}
          onChange={handleInputChange}
        />
      </div>
      <div className="btn-container">
        <button
          type="button"
          className="btn-login"
          id="register"
          onClick={register}
        >
          Register
        </button>
      </div>
    </div>
  );
};

export default RegisterComponent;
