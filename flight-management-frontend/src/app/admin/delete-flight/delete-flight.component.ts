import { Component } from '@angular/core';
import { DeleteFlightService } from 'src/app/service/admin/delete-flight.service';

@Component({
  selector: 'app-delete-flight',
  templateUrl: './delete-flight.component.html',
  styleUrls: ['./delete-flight.component.css'],
})
export class DeleteFlightComponent {
  flightId = 0;
  constructor(private deleteFlightService: DeleteFlightService) {}

  handleDeleteFlight(data: any) {
    this.deleteFlightService.deleteFlight(this.flightId).subscribe(
      (response: string) => {
        alert(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
