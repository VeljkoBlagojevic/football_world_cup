import { gql } from "@apollo/client";

export const GET_ALL_REPREZENTACIJE = gql`
  query {
    getAllReprezentacije {
      id
      naziv
      dvoslovniNaziv
      troslovniNaziv
      zastava
    }
  }
`;

export const GET_ALL_STADIONI = gql`
  query {
    getAllStadioni {
      id
      naziv
      kapacitet
      slika
    }
  }
`;
