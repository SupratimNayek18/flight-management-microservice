import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ViewAllFlightsService } from 'src/app/service/admin/view-all-flights.service';

@Component({
  selector: 'app-view-all-flights',
  templateUrl: './view-all-flights.component.html',
  styleUrls: ['./view-all-flights.component.css'],
})
export class ViewAllFlightsComponent {
  flights: any[] = [];

  constructor(
    private router: Router,
    private viewAllFlightService: ViewAllFlightsService
  ) {
    viewAllFlightService.viewAllFlights().subscribe((response: any) => {
      this.flights = response;
    });
  }
}
