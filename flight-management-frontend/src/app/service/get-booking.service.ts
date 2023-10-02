import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class GetBookingService {
  constructor(private http: HttpClient) {}

  getBooking(userName: String) {
    let url = `http://localhost:9090/booking/getBookingByUserName/${userName}`;
    let token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });

    return this.http.get(url, { headers });
  }
}
