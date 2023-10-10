import { Component } from '@angular/core';
import { SearchFlightsService } from '../service/search-flights.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-flights',
  templateUrl: './search-flights.component.html',
  styleUrls: ['./search-flights.component.css'],
})
export class SearchFlightsComponent {
  flights: any[] = [];

  constructor(
    private searchService: SearchFlightsService,
    private router: Router
  ) {
    if (sessionStorage.getItem('flights') != null) {
      let flightData = sessionStorage.getItem('flights');
      if (flightData != null) {
        this.flights = JSON.parse(flightData);
      }
    }
  }

  searchFlights(source: String, destination: String) {
    this.searchService.searchFlights(source, destination).subscribe(
      (response) => {
        sessionStorage.setItem('flights', JSON.stringify(response));
        window.location.reload();
      },
      (error) => {
        sessionStorage.removeItem('flights');
        window.location.reload();
        console.log(error);
      }
    );
  }

  bookFlightClick(flight: any) {
    if (sessionStorage.getItem('userName') == null) {
      this.router.navigate(['signin']);
    } else {
      console.log(flight);
      sessionStorage.setItem('flightToBeBooked', JSON.stringify(flight));
      this.router.navigate(['/bookFlight']);
    }
  }
}
