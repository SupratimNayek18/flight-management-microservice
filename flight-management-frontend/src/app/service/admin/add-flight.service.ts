import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AddFlightService {
  constructor(private http: HttpClient) {}

  addFlight(data: any) {
    let addFlightUrl = 'http://localhost:9090/flight/add';
    let token = sessionStorage.getItem('token');
    let headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
    return this.http.post<any>(addFlightUrl, data, { headers });
  }
}
