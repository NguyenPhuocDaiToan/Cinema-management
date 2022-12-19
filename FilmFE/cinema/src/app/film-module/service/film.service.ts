import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Film} from '../../model/film';
import {Observable} from 'rxjs';

const API_URL = `api/film`;

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  constructor(private http: HttpClient) { }

  getAPI(): Observable<Film[]> {
    return this.http.get<Film[]>(API_URL + '/films'); // test API
  }

  postAPI(film: Film): Observable<Film[]> {
    return this.http.post<Film[]>(API_URL + '/films', film);
  }

  getAPIbyId(id: number): Observable<Film> {
    return this.http.get<Film>(`${API_URL}/films/${id}`);
  }

  updateAPI(id: number, film: Film): Observable<Film> {
    console.log(film);
    return this.http.put<Film>(`${API_URL}/film/${id}`, film);
  }

  deleteAPIbyId(id: number): Observable<Film> {
    return this.http.delete<Film>(`${API_URL}/film/${id}`);
  }

  filterAPIByName(name: string): Observable<Film> {
    return this.http.get<Film>(`${API_URL}/film?name_like=${name}`);
  }
}

