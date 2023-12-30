import ActionTypes from "../actions/actionTypes";
import { AppState, initialAppState } from "../initialState";

export function rootReducer(
  state: AppState = initialAppState,
  action: any
): AppState {
  switch (action.type) {
    case ActionTypes.LOGIN:
      return {
        token: action.token,
        loggedInUser: action.loggedInUser,
      };
    case ActionTypes.LOGOUT:
      return {
        token: undefined,
        loggedInUser: undefined,
      };
    default:
      return state;
  }
}
