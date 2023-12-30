import { Korisnik } from "../domain/Korisnik";

export interface AppState {
  token?: string;
  loggedInUser?: Korisnik;
}

export const initialAppState: AppState = {
  token: undefined,
  loggedInUser: undefined,
};
