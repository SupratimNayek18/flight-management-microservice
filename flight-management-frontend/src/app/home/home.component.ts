import { Component } from '@angular/core';
import { LogoutService } from '../service/logout.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(private logoutService: LogoutService) {}

  userName = sessionStorage.getItem('userName');

  flightData = {};

  getFlightData(source: String, destination: String) {}

  handleLogout() {
    this.logoutService.logout();
  }
}
