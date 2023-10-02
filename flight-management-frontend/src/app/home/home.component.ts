import { Component } from '@angular/core';
import { LogoutService } from '../service/logout.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(private logoutService: LogoutService, private router: Router) {}

  ngOnInit() {
    if (sessionStorage.getItem('role') === 'ROLE_ADMIN') {
      this.router.navigate(['adminPanel']);
    }
  }

  userName = sessionStorage.getItem('userName');

  flightData = {};

  getFlightData(source: String, destination: String) {}

  handleLogout() {
    this.logoutService.logout();
    window.location.reload();
  }
}
