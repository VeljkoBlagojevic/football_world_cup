import { Korisnik } from "../../domain/Korisnik";
import ActionTypes from "./actionTypes";

export function login(token: string, loggedInUser: Korisnik) {
  return {
    type: ActionTypes.LOGIN,
    token,
    loggedInUser,
  };
}

export function logout() {
  return {
    type: ActionTypes.LOGOUT,
  };
}
