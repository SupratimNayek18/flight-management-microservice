import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class SearchFlightsService {
  constructor(private http: HttpClient) {}

  searchUrl = 'http://localhost:9090/flight/search/';

  searchByIdUrl = 'http://localhost:9090/flight/viewByFlightId/';

  searchFlights(source: String, destination: String) {
    return this.http.get<any>(this.searchUrl + source + '/' + destination);
  }

  searchFlightById(id: number) {
    return this.http.get(this.searchByIdUrl + id);
  }
}
