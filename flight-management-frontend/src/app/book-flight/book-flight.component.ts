import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BookFlightService } from '../service/book-flight.service';

@Component({
  selector: 'app-book-flight',
  templateUrl: './book-flight.component.html',
  styleUrls: ['./book-flight.component.css'],
})
export class BookFlightComponent {
  flightId: number | undefined;
  source: string | undefined;
  destination: string | undefined;
  departure: string | undefined;
  arrival: string | undefined;
  price: number | undefined;
  userName: string | undefined;
  token: string | undefined;
  status: string | undefined;

  constructor(
    private router: Router,
    private bookFLightService: BookFlightService
  ) {}

  ngOnInit() {
    const flight = sessionStorage.getItem('flightToBeBooked');
    let userName = sessionStorage.getItem('userName');
    let token = sessionStorage.getItem('token');
    if (flight === null || userName === null || token === null) {
      this.router.navigate(['flights']);
    } else {
      const flightParsed = JSON.parse(flight);
      this.flightId = flightParsed.flightId;
      this.source = flightParsed.source;
      this.destination = flightParsed.destination;
      this.departure = flightParsed.departure;
      this.arrival = flightParsed.arrival;
      this.price = flightParsed.price;
      this.userName = userName;
      this.token = token;
    }
  }

  handleBooking(noOfPersons: string) {
    let persons = Number.parseInt(noOfPersons);
    if (this.flightId != null && this.userName != null && this.token != null) {
      this.bookFLightService
        .bookFlight(this.flightId, this.userName, persons, this.token)
        .subscribe(
          (response) => {
            this.status = `Your upcoming flight from ${this.source} to ${this.destination} has been booked successfully. Booking Id is ${response.bookingId}`;
          },
          (error) => {
            alert('Something went wrong. Please try again');
          }
        );
    }
  }
}
