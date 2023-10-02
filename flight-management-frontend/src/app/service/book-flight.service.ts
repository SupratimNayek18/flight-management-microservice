import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BookFlightService {
  constructor(private http: HttpClient) {}

  bookFlight(
    flightId: number,
    userName: String,
    persons: number,
    token: string
  ) {
    const url = `http://localhost:9090/booking/bookFlight/${flightId}/${userName}/${persons}`;

    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.post<any>(url, null, { headers });
  }
}
