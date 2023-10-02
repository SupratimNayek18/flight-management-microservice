import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DeleteFlightService {
  constructor(private http: HttpClient) {}

  deleteFlight(flightId: number) {
    let deleteUrl = `http://localhost:9090/flight/delete/${flightId}`;

    let token = sessionStorage.getItem('token');

    let headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.delete(deleteUrl, {
      headers,
      responseType: 'text',
    });
  }
}
