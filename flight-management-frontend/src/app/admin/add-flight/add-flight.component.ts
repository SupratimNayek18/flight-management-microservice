import { Component } from '@angular/core';
import { AddFlightService } from 'src/app/service/admin/add-flight.service';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css'],
})
export class AddFlightComponent {
  formData = {
    flightId: 0,
    flightName: '',
    source: '',
    destination: '',
    date: '',
    price: 0,
    arrival: '',
    departure: '',
  };

  constructor(private addFlightService: AddFlightService) {}

  handleAddFlight(data: any) {
    this.addFlightService.addFlight(this.formData).subscribe(
      (response) => {
        alert('Flight Added Successfully');
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
