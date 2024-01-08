import { gql } from "@apollo/client";

export const ZAKAZI_UTAKMICU = gql`
  mutation zakaziUtakmicu($utakmicaInput: UtakmicaInput!) {
    zakaziUtakmicu(utakmicaInput: $utakmicaInput) {
      id
    }
  }
`;
