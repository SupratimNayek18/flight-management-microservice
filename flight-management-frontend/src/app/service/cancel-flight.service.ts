import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CancelFlightService {
  constructor(private http: HttpClient) {}

  cancelFlight(bookingId: string, userName: string) {
    let cancelUrl = `http://localhost:9090/booking/cancelFlight/${bookingId}/${userName}`;

    let token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.delete(cancelUrl, {
      headers,
      responseType: 'text',
    });
  }
}
