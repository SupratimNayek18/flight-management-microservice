import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CheckInServiceService {
  constructor(private http: HttpClient) {}

  checkIn(
    bookingId: string,
    userName: string,
    token: string,
    seatsSelected: string[]
  ) {
    let checkInUrl = `http://localhost:9090/checkIn/${bookingId}/${userName}`;
    let headers = new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
    return this.http.post(checkInUrl, seatsSelected, { headers });
  }
}
