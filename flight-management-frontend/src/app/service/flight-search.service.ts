import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class FlightSearchService {
  constructor(private router: Router) {}

  getFlightData(source: String, destination: String) {}
}
