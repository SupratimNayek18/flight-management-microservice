import { Component } from '@angular/core';
import { CheckInServiceService } from '../service/check-in-service.service';

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.css'],
})
export class CheckInComponent {
  bookingId: string | null;
  userName: string | null;
  seats: string | null;
  seatsArray: any[] | undefined = [];
  seatsSelected: string[] = [];
  noOfPersons: number = 0;
  status: string | undefined = undefined;

  constructor(private checkInService: CheckInServiceService) {
    this.bookingId = null;
    this.userName = null;
    this.seats = null;
  }

  ngOnInit() {
    this.userName = sessionStorage.getItem('userName');
    this.bookingId = sessionStorage.getItem('bookingId');
    this.seats = sessionStorage.getItem('seats');
    this.seatsArray = this.seats?.split(',');
    this.noOfPersons = parseInt(
      sessionStorage.getItem('noOfPersons') || '2',
      10
    );
  }

  addSeat(seat: string) {
    if (!this.seatsSelected.includes(seat)) {
      this.seatsSelected.push(seat);
    }
    console.log(this.seatsSelected);
  }

  toggleSeat(seat: string) {
    const index = this.seatsSelected.indexOf(seat);

    if (index === -1) {
      if (this.seatsSelected.length < this.noOfPersons) {
        this.seatsSelected.push(seat);
      }
    } else {
      this.seatsSelected.splice(index, 1);
    }
  }

  isSelected(seat: string): boolean {
    return this.seatsSelected.includes(seat);
  }

  handleCheckIn() {
    let token = sessionStorage.getItem('token');

    if (this.bookingId != null && token != null && this.userName != null) {
      this.checkInService
        .checkIn(this.bookingId, this.userName, token, this.seatsSelected)
        .subscribe(
          (response: any) => {
            this.status = `Successfully checked in. You have booked ${response.seatsBooked} seat/seats`;
          },
          (error) => {
            console.log(error);
          }
        );
    }
  }
}
