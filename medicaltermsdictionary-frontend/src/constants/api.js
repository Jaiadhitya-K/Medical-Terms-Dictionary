export const BASE_URL = "http://localhost:8080/api/medicalterms";

export const ENDPOINTS = {
  ALL_TERMS: `${BASE_URL}`,
  SEARCH: (query) => `${BASE_URL}/search?query=${query}`,
  FAVORITES: `${BASE_URL}/favorites`,
  TERM_DETAILS: (term) => `${BASE_URL}/name/${term}`,
  RELATED_TERMS: (term) => `${BASE_URL}/${term}/related`,
  ADD_FAVORITE: (id) => `${BASE_URL}/${id}/favorites`,
  REMOVE_FAVORITE: (id) => `${BASE_URL}/${id}/favorites`,
};