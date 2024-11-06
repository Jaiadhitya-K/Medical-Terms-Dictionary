import axios from 'axios';
import { ENDPOINTS } from '../constants/api';

export const fetchAllTerms = () => axios.get(ENDPOINTS.ALL_TERMS);
export const searchTerms = (query) => axios.get(ENDPOINTS.SEARCH(query));
export const fetchTermDetails = (term) => axios.get(ENDPOINTS.TERM_DETAILS(term));
export const fetchRelatedTerms = (term) => axios.get(ENDPOINTS.RELATED_TERMS(term));
export const fetchFavorites = () => axios.get(ENDPOINTS.FAVORITES);
export const addToFavorites = (id) => axios.post(ENDPOINTS.ADD_FAVORITE(id));
export const removeFromFavorites = (id) => axios.delete(ENDPOINTS.REMOVE_FAVORITE(id));
