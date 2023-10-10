import { Component } from '@angular/core';
import { GetBookingService } from '../service/get-booking.service';
import { Router } from '@angular/router';
import { SearchFlightsService } from '../service/search-flights.service';
import { CancelFlightService } from '../service/cancel-flight.service';

@Component({
  selector: 'app-show-bookings',
  templateUrl: './show-bookings.component.html',
  styleUrls: ['./show-bookings.component.css'],
})
export class ShowBookingsComponent {
  bookingList: any[] = [];
  status = '';
  selectedCardIndex: number | null = null;

  constructor(
    private getBookingService: GetBookingService,
    private searchFlightService: SearchFlightsService,
    private router: Router,
    private cancelFlightService: CancelFlightService
  ) {}

  ngOnInit() {
    let userName = sessionStorage.getItem('userName');
    if (userName != null) {
      this.getBookingService.getBooking(userName).subscribe(
        (response: any) => {
          this.bookingList = response;
          this.bookingList.reverse();
        },
        (error) => {}
      );
    }
  }

  setStatus(index: number, id: number) {
    this.searchFlightService.searchFlightById(id).subscribe((response: any) => {
      this.status = `Flight Name: ${response.flightName}<br>
                      Source: ${response.source}<br>
                      Destination: ${response.destination}<br>
                      Date: ${response.date}<br>
                      Departure: ${response.departure}<br>
                      Arrival: ${response.arrival}`;
    });
    this.selectedCardIndex = this.selectedCardIndex === index ? null : index;
  }

  isCardSelected(index: number): boolean {
    // Check if the card is selected
    return this.selectedCardIndex === index;
  }

  checkInRoute(flightId: number, bookingId: string, noOfPersons: number) {
    this.searchFlightService
      .searchFlightById(flightId)
      .subscribe((response: any) => {
        sessionStorage.setItem('seats', response.seatNumbers);
        sessionStorage.setItem('bookingId', bookingId + '');
        sessionStorage.setItem('noOfPersons', noOfPersons + '');
        this.router.navigate(['/checkIn']);
      });
  }

  cancelFlight(bookingId: string) {
    let userName = sessionStorage.getItem('userName');
    if (userName != null) {
      var userResponse = confirm('Do you want to cancel this flight?');
      if (userResponse === true) {
        // User clicked "OK" (yes)
        this.cancelFlightService
          .cancelFlight(bookingId, userName)
          .subscribe((response) => {
            alert(`${response}`);
            window.location.reload();
          });
      }
    }
  }
}
