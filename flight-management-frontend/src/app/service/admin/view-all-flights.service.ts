import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ViewAllFlightsService {
  constructor(private http: HttpClient) {}

  viewAllFlights() {
    let token = sessionStorage.getItem('token');

    let headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.get('http://localhost:9090/flight/viewAll', { headers });
  }
}
