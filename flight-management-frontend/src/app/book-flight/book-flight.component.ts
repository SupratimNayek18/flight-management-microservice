import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-flight',
  templateUrl: './book-flight.component.html',
  styleUrls: ['./book-flight.component.css'],
})
export class BookFlightComponent {
  flightId: string | undefined;
  source: string | undefined;
  destination: string | undefined;
  departure: string | undefined;
  arrival: string | undefined;
  price: string | undefined;

  constructor(private router: Router) {}

  ngOnInit() {
    const flight = sessionStorage.getItem('flightToBeBooked');
    if (flight === null) {
      this.router.navigate(['flights']);
    } else {
      const flightParsed = JSON.parse(flight);
      this.flightId = flightParsed.flightId;
      this.source = flightParsed.source;
      this.destination = flightParsed.destination;
      this.departure = flightParsed.departure;
      this.arrival = flightParsed.arrival;
      this.price = flightParsed.price;
    }
  }
}
